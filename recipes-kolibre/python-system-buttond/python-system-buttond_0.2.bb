DESCRIPTION = "Python daemon for using system buttons"
SECTION = "devel/python"
LICENSE = "GPLv2+"
DEPENDS = "python"
RDEPENDS_${PN} += "python python-io python-syslog"
PR = "r0"

SRC_URI = "file://evdev.py"
SRC_URI += "file://COPYING"
SRC_URI += "file://system-buttond.service"

inherit systemd

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/evdev.py ${D}${bindir}/system-buttond.py

    # Install systemd service file
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/system-buttond.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${bindir}/system-buttond.py"
FILES_${PN} += "${systemd_unitdir}/system/system-buttond.service"
SYSTEMD_SERVICE_${PN} += "system-buttond.service"
