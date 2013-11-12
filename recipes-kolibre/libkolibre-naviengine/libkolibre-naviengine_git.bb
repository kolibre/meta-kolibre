DESCRIPTION = "Library providing a engine and basic nodes for building a simple navigatable tree"
HOMEPAGE = "https://github.com/kolibre/libkolibre-naviengine"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

SRCREV = "5ff353723a7d904693820a71fbc82aedbde627c1"
PV = "0.0.1+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-naviengine.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
