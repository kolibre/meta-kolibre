DESCRIPTION = "Library for fetching and parsing XML data"
HOMEPAGE = "https://github.com/kolibre/libkolibre-xmlreader"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "d44abd3e8d60af54a5411f79e6bbdb1d86ed758b"
PV = "0.0.1+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-xmlreader.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://libtidy_check.patch;apply=yes;striplevel=1"
SRC_URI += "file://libxml2_cflags.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive log4cxx curl libxml2 libtidy"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
