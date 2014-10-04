FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://udisks-mount-signal"
SRC_URI += "file://udisks-glue.service"

PACKAGES += "${PN}-systemd"
RDEPENDS_${PN}-systemd = "udisks-glue"

inherit systemd

do_install_append() {
    # Install udisks2 emulator
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/udisks-mount-signal ${D}${bindir}
    sed -i -e 's:sync"$:sync \&\& udisks-mount-signal %device_file":' ${D}${sysconfdir}/udisks-glue.conf

    # Install systemd service file
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/udisks-glue.service ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "udisks-glue.service"
FILES_${PN}-systemd += "${systemd_unitdir}/system/udisks-glue.service"
