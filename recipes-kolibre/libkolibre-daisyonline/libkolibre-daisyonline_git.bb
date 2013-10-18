DESCRIPTION = "Library for utilizing the daisy online protocol defined by the Daisy Consortium"
HOMEPAGE = "https://github.com/kolibre/libkolibre-daisyonline"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "266c31a2d9130e707012c4ef94474dbce988d1e5"
PV = "0.0.1+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-daisyonline.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch"
#SRC_URI += "file://axis2c_cflags.patch"
SRC_URI += "file://link_with_axis2c_libs.patch"

DEPENDS = "autoconf-archive log4cxx axis2c"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
