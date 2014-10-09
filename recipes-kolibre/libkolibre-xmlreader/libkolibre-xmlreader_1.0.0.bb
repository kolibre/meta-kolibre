DESCRIPTION = "Library for fetching and parsing XML data"
HOMEPAGE = "https://github.com/kolibre/libkolibre-xmlreader"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://libtidy_check.patch;apply=yes;striplevel=1"
SRC_URI += "file://libxml2_cflags.patch;apply=yes;striplevel=1"

SRC_URI[md5sum] = "822fed15afa83cc54bf9b06e276fcd3b"
SRC_URI[sha256sum] = "3dfb87ee21cd3bfeb94a4199bcbf088509d0dcd15d9ccad3320d8ced0f0cc804"

DEPENDS = "autoconf-archive log4cxx curl libxml2 libtidy"

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
