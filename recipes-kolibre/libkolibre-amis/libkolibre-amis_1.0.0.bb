DESCRIPTION = "Library for navigating and parsing daisy books"
HOMEPAGE = "https://github.com/kolibre/libkolibre-amis"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

SRC_URI[md5sum] = "647c454b0ec6dd326911f91a04c20edc"
SRC_URI[sha256sum] = "7e8b160ede1fd8916691db12633b6ce63f576fbd17bb25104a5ed336e66fe5fd"

DEPENDS = "autoconf-archive log4cxx libkolibre-xmlreader"

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
