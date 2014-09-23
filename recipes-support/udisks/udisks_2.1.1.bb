DESCRIPTION = "A storage daemon that implements well-defined D-Bus interfaces that can be used to query and manipulate storage devices."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=dd79f6dbbffdbc8e86b086a8f0c0ef43"

DEPENDS = "udev dbus-glib glib-2.0"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

SRC_URI = "http://udisks.freedesktop.org/releases/${P}.tar.bz2 \
           file://optional-depends.patch"

SRC_URI[md5sum] = "80e03f312542b800cf3162254b202725"
SRC_URI[sha256sum] = "013b09ff38aa256b89c01525771f4565cb088724d5c8c79b32e9f811e88086fb"

inherit autotools systemd

PACKAGECONFIG ??= "atasmart polkit acl ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd-login', '', d)}"
PACKAGECONFIG[atasmart] = "--enable-libatasmart,--disable-libatasmart,libatasmart"
PACKAGECONFIG[polkit] = "--enable-polkit,--disable-polkit,polkit"
PACKAGECONFIG[acl] = "--enable-acl,--disable-acl,acl"
PACKAGECONFIG[systemd-login] = "--enable-libsystemd-login,--disable-libsystemd-login,systemd"

EXTRA_OECONF = "--disable-man"

PACKAGES =+ "${PN}-bash-completion"

FILES_${PN} += "${libdir}/polkit-1/extensions/*.so \
                ${datadir}/dbus-1/ \
                ${datadir}/polkit-1 \
                ${base_libdir}/udev/* \
                ${libdir}/udisks2/* \
"
FILES_${PN}-dbg += "${base_libdir}/udev/.debug \
		${libdir}/udisks2/.debug"
FILES_${PN}-bash-completion = "${datadir}/bash-completion"

SYSTEMD_SERVICE_${PN} = "udisks2.service"
SYSTEMD_AUTO_ENABLE = "disable"
