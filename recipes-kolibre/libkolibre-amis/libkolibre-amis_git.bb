DESCRIPTION = "Library for navigating and parsing daisy books"
HOMEPAGE = "https://github.com/kolibre/libkolibre-amis"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "e3d331971db7bb3c5376e7ec5ba085aee7492b05"
PV = "0.0.1+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-amis.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive log4cxx libkolibre-xmlreader"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
