DESCRIPTION = "udisks-glue is a tool that can associate udisks events to user-defined actions"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6619964298af71cb422421a52eed4893"

DEPENDS = "dbus-glib glib-2.0 confuse"
RDEPENDS_${PN} = "udisks"

PR = "r0"

SRC_URI = "https://github.com/fernandotcl/udisks-glue/archive/release-1.3.5.tar.gz"

SRC_URI[md5sum] = "282f3d33600fcb21a2c2b28f88d805d1"
SRC_URI[sha256sum] = "317d25bf249278dc8f6a5dcf18f760512427c772b9afe3cfe34e6e1baa258176"

S = "${WORKDIR}/${PN}-release-${PV}"

inherit autotools pkgconfig

do_install_append() {
    # Install conf file
    install -d ${D}${sysconfdir}
    install -m 644 ${FILE_DIRNAME}/files/udisks-glue.conf ${D}${sysconfdir}
}
