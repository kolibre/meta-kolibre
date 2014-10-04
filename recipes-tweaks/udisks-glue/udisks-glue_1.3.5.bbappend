FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://udisks-mount-signal"

do_install_append() {
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/udisks-mount-signal ${D}${bindir}
    sed -i -e 's:sync"$:sync \&\& udisks-mount-signal %device_file":' ${D}${sysconfdir}/udisks-glue.conf
}
