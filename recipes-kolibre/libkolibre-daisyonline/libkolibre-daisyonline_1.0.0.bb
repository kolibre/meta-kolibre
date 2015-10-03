DESCRIPTION = "Library for utilizing the daisy online protocol defined by the Daisy Consortium"
HOMEPAGE = "https://github.com/kolibre/libkolibre-daisyonline"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch"
SRC_URI += "file://link_with_axis2c_libs.patch"

SRC_URI[md5sum] = "edd69ab5400e62693439d4dd5c02f397"
SRC_URI[sha256sum] = "750097c144a210f21dd372f070fe5e9dfda3f627ced3f2222fee4968ea0b0aec"

DEPENDS = "autoconf-archive log4cxx axis2c"

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools autotools-brokensep pkgconfig

