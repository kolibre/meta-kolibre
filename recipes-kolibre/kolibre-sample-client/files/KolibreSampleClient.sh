#!/bin/sh -e

TAG="KolibreSampleClient.sh"
FILE="/usr/bin/${TAG}"

#echo 1024 > /sys/block/mmcblk0/queue/read_ahead_kb

SETTINGS_PATH=/usr/share/kolibre-sample-client/settings.ini

if [ -f /media/mmc1/settings.ini ]; then
    SETTINGS_PATH=/media/mmc1/settings.ini
fi
LOG_CONF=/usr/share/kolibre-sample-client/log4cxx.conf

# Read settings from settingsfile
SERVICE_URL=$(grep -e "^SERVICE_URL" ${SETTINGS_PATH} | cut -d "=" -f2)
USERNAME=$(grep -e "^USERNAME" ${SETTINGS_PATH} | cut -d "=" -f2)
PASSWORD=$(grep -e "^PASSWORD" ${SETTINGS_PATH} | cut -d "=" -f2)
LANGUAGE=$(grep -e "^LANGUAGE" ${SETTINGS_PATH} | cut -d "=" -f2)
LOG_SDCARD=$(grep -e"^LOG_SDCARD" ${SETTINGS_PATH} | cut -d "=" -f2)
LOG_LEVEL=$(grep -e "^LOG_LEVEL" ${SETTINGS_PATH} | cut -d "=" -f2)
INPUT_REG=$(grep -e "^INPUT_DEVICE" ${SETTINGS_PATH} | cut -d "=" -f2)
TMPFS=$(grep -e "^HOME" ${SETTINGS_PATH} | cut -d "=" -f2)
if [ "$TMPFS" = "/tmp" ]; then HOME=$TMPFS; fi
test -z "$SERVICE_URL" && abort "SERVICE_URL"
test -z "$USERNAME" && abort "USERNAME"
test -z "$PASSWORD" && abort "PASSWORD"
test -z "$LANGUAGE" && abort "LANGUAGE"

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

INPUT_DEV=""
if [ -n "$INPUT_REG" ]; then
    cd /dev/input
    for dev in $(ls e*) ; do
        if udevadm info /sys/class/input/$dev | grep -i $INPUT_REG; then
            INPUT_DEV="-i /dev/input/$dev"
            break
        fi
    done
    cd -
fi

/usr/bin/KolibreSampleClient $SERVICE_URL $USERNAME $PASSWORD $USERAGENT -l $LANGUAGE -c $LOG_CONF $INPUT_DEV -r
