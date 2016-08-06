#!/bin/sh -e

TAG="KolibreSampleClient.sh"
FILE="/usr/bin/${TAG}"

#echo 1024 > /sys/block/mmcblk0/queue/read_ahead_kb

# Copy wifi settings from mmc to system
# The right place would be in a own systemd script but I'm not a
# systemd expert nor do I have time to read up how to do it properly.
if [ -f /media/mmc1/wifi.config ]; then
    cp /media/mmc1/wifi.config /var/lib/connman
fi

SETTINGS_PATH=/usr/share/kolibre-sample-client/settings.ini

if [ -f /media/mmc1/settings.ini ]; then
    SETTINGS_PATH=/media/mmc1/settings.ini
fi
LOG_CONF=/usr/share/kolibre-sample-client/log4cxx.conf

# Read settings from settingsfile
LANGUAGE=$(grep -e "^LANGUAGE" ${SETTINGS_PATH} | cut -d "=" -f2)
LOG_SDCARD=$(grep -e"^LOG_SDCARD" ${SETTINGS_PATH} | cut -d "=" -f2)
LOG_LEVEL=$(grep -e "^LOG_LEVEL" ${SETTINGS_PATH} | cut -d "=" -f2)
INPUT_REG=$(grep -e "^INPUT_DEVICE" ${SETTINGS_PATH} | cut -d "=" -f2)
TMPFS=$(grep -e "^HOME" ${SETTINGS_PATH} | cut -d "=" -f2)
if [ "$TMPFS" = "/tmp" ]; then HOME=$TMPFS; fi
if [ -z "$LANGUAGE" ]; then LANGUAGE=en; fi

if [ -z "$HOME" ] || [ "$HOME" -eq "/" ]; then
    HOME="/home/root"
fi
export BOOKMARK_DIR=${HOME}/.KolibreSampleClient/bookmarks
export KOLIBRE_DATA_PATH=${HOME}/.KolibreSampleClient

test -d ${BOOKMARK_DIR} || mkdir -p ${BOOKMARK_DIR}
test -d ${KOLIBRE_DATA_PATH} || mkdir -p ${KOLIBRE_DATA_PATH}

VERSION=$(opkg info kolibre-sample-client | grep Version | cut -d ':' -f2 | cut -d '+' -f1 | tr -d ' ')
USERAGENT="kolibre-sample-client/$VERSION"

PROMPTS_DB=/usr/share/kolibre-sample-client/messages.db
# make a copy of PROMPTS_DB in KOLIBRE_DATA_DIR if one does not exist
if [ -f ${KOLIBRE_DATA_PATH}/messages.db ]; then 
    logger -t ${TAG} -p syslog.info -s "Messages db exists in ${KOLIBRE_DATA_PATH}"
else
    cp ${PROMPTS_DB} ${KOLIBRE_DATA_PATH}
    logger -t ${TAG} -p syslog.info -s "Messages db copied to ${KOLIBRE_DATA_PATH}"
fi

# Change log output to sdcard
if [ -n "${LOG_SDCARD}" ] && [ "${LOG_SDCARD}" = "true" ]; then
    sed -e "s/^log4j.rootLogger=.*$/log4j.rootLogger=DEBUG, S, R/g" -i ${LOG_CONF}
else
    sed -e "s/^log4j.rootLogger=.*$/log4j.rootLogger=DEBUG, S/g" -i ${LOG_CONF}
fi

# Change log level in log configuration
if [ -n "${LOG_LEVEL}" ]; then
    change=0
    case "${LOG_LEVEL}" in
        "TRACE")
            change=1
            ;;
        "DEBUG")
            change=1
            ;;
        "INFO")
            change=1
            ;;
        "WARN")
            change=1
            ;;
        "ERROR")
            change=1
            ;;
        "FATAL")
            change=1
            ;;
        *)
            logger -t ${TAG} -p syslog.warning -s "log level '${LOG_LEVEL}' is not supported"
            ;;
    esac
    if [ ${change} -eq 1 ]; then
        sed "s/rootLogger=[A-Z]\+/rootLogger=${LOG_LEVEL}/" -i ${LOG_CONF}
    fi
fi

INPUT_DEFAULT=""
INPUT_DEV=""
# Input devices takes longer to setup on older RaspberryPi models,
# thus we will wait at most 10 seconds for devices to become ready
cd /dev/input
max_iterations=10
iterations=0
while true; do
    logger -t ${TAG} -p syslog.info -s "Searching for input devices"
    count=$(find /dev/input/event* | wc -l)
    if [ $count -gt 0 ]; then
        for dev in $(ls e*) ; do
            if [ -z "$INPUT_DEFAULT" ];
            then
                if udevadm info /sys/class/input/$dev | grep -i "ID_INPUT_KEYBOARD=1"; then
                    INPUT_DEFAULT="-d /dev/input/$dev"
                fi
            fi
            if [ -n "$INPUT_REG" ]; then
                if udevadm info /sys/class/input/$dev | grep -i "$INPUT_REG"; then
                    logger -t ${TAG} -p syslog.info -s "Input device $dev is matching search criteria '$INPUT_REG'"
                    INPUT_DEV="-d /dev/input/$dev"
                    break
                fi
            fi
        done
    fi
    if [ -n "$INPUT_DEV" ]; then
        break
    fi
    iterations=$((iterations + 1))
    if [ $iterations == $max_iterations ]; then
        logger -t ${TAG} -p syslog.warning -s "Giving up search for input devices after 10 attempts"
        break
    fi
    sleep 1
done
cd -
if [ -z "$INPUT_DEV" ] && [ -n "$INPUT_DEFAULT" ]; then
    INPUT_DEV=$INPUT_DEFAULT
fi

/usr/bin/KolibreSampleClient -i $SETTINGS_PATH -c $LOG_CONF -l $LANGUAGE -a $USERAGENT $INPUT_DEV
