DESCRIPTION = "Library and sample client"
HOMEPAGE = "https://github.com/kolibre/libkolibre-clientcore"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

SRCREV = "d38bf4e6d1da457ef51cf69524930d5b479146c2"
PV = "0.0.1+git${SRCREV}"

PROVIDES += "kolibre-sample-client"
CLIENT = "kolibre-sample-client"
PACKAGES =+ "${CLIENT}"

SRC_URI = "git://github.com/kolibre/libkolibre-clientcore.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://make_fixes.patch;apply=yes;striplevel=1"
SRC_URI += "file://dont_send_book_position_info.patch;apply=yes;striplevel=1"
SRC_URI += "file://kolibre.service"
SRC_URI += "file://media-mmc1.mount"

DEPENDS = "autoconf-archive log4cxx libkolibre-narrator libkolibre-player libkolibre-xmlreader libkolibre-amis libkolibre-daisyonline libkolibre-naviengine"
RDEPENDS_${CLIENT} += "kolibre-sample-client-data"

EXTRA_OECONF = "--with-boost=${STAGING_LIBDIR}/.. --with-samples"

S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd

do_configure_prepend() {
    autoreconf --force -i
}

do_install_append() {
    install -d ${D}${datadir}/${PN}/prompts/en
    install -d ${D}${datadir}/${PN}/prompts/sv
    install -d ${D}${datadir}/${PN}/prompts/fi
    install -m 644 ${S}/prompts/*.csv ${D}${datadir}/${PN}/prompts
    install -m 644 ${S}/prompts/en/*.csv ${D}${datadir}/${PN}/prompts/en
    install -m 644 ${S}/prompts/sv/*.csv ${D}${datadir}/${PN}/prompts/sv
    install -m 644 ${S}/prompts/fi/*.csv ${D}${datadir}/${PN}/prompts/fi

    # Install client startup script
    install -d ${D}${bindir}
    install -m 0755 ${FILE_DIRNAME}/KolibreSampleClient.sh ${D}${bindir}

    # Install log configuration file
    install -d ${D}${datadir}/${CLIENT}
    install -m 644 ${FILE_DIRNAME}/log4cxx.conf ${D}${datadir}/${CLIENT}

    # Install settings file
    install -m 644 ${FILE_DIRNAME}/settings.ini ${D}${datadir}/${CLIENT}

    # Install systemd service file
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/kolibre.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/media-mmc1.mount ${D}${systemd_unitdir}/system
}

FILES_${CLIENT} += "\
        ${bindir}/KolibreSampleClient \
        ${bindir}/KolibreSampleClient.sh \
        ${datadir}/${CLIENT}/log4cxx.conf \
        ${datadir}/${CLIENT}/settings.ini \
        "


PACKAGES =+ "${CLIENT}-systemd"
SYSTEMD_PACKAGES = "${CLIENT}-systemd"
SYSTEMD_SERVICE_${CLIENT}-systemd = "kolibre.service"
FILES_${CLIENT}-systemd += "${systemd_unitdir}/system/kolibre.service"
FILES_${CLIENT}-systemd += "${systemd_unitdir}/system/media-mmc1.mount"
