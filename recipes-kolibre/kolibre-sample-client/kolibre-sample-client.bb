DESCRIPTION = "Startup script for KolibreSampleClient"
LICENSE = "LGPLv2.1"
SECTION = "console"

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

PV = "0.1"

SRC_URI = "file://COPYING"

PACKAGES = "${PN} ${PN}-systemd"
RDEPENDS_${PN} += "kolibre-sample-client-bin kolibre-sample-client-data"

S = "${FILE_DIRNAME}/files"

inherit systemd

do_install() {
    # Install client startup script
    install -d ${D}${bindir}
    install -m 0755 ${S}/KolibreSampleClient.sh ${D}${bindir}

    # Install log configuration file
    install -d ${D}${datadir}/${PN}
    install -m 644 ${S}/log4cxx.conf ${D}${datadir}/${PN}

    # Install settings file
    install -m 644 ${S}/settings.ini ${D}${datadir}/${PN}

    # Install systemd service file
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/kolibre.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/media-mmc1.mount ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "kolibre.service"
FILES_${PN}-systemd += "${systemd_unitdir}/system/kolibre.service"
FILES_${PN}-systemd += "${systemd_unitdir}/system/media-mmc1.mount"
