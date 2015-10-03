DESCRIPTION = "Library providing a engine and basic nodes for building a simple navigatable tree"
HOMEPAGE = "https://github.com/kolibre/libkolibre-naviengine"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"

SRC_URI[md5sum] = "7b367c3e584ce5e5ea0ffe886a28485b"
SRC_URI[sha256sum] = "a30494365ee7c298b1200fb806ac36b3482dfd5620e4753fa6d18b98a7cc2b06"

DEPENDS = "autoconf-archive"

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools autotools-brokensep pkgconfig

