DESCRIPTION = "Library providing a engine and basic nodes for building a simple navigatable tree"
HOMEPAGE = "https://github.com/kolibre/libkolibre-naviengine"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

SRCREV = "41e84dcbe6da94d8fa6864404dfce12facc95fcc"
PV = "1.0.0+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-naviengine.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
