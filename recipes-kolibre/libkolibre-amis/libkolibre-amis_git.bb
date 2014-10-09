DESCRIPTION = "Library for navigating and parsing daisy books"
HOMEPAGE = "https://github.com/kolibre/libkolibre-amis"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "8c20a944e28af347726304062bf165815f7e6c47"
PV = "1.0.0+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-amis.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive log4cxx libkolibre-xmlreader"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
