DESCRIPTION = "LibTidy"
SECTION = "libs"
PRIORITY = "optional"
HOMEPAGE = "http://sourceforge.net/projects/tidy/"
LICENSE = "MIT"
DEPENDS = "eglibc"
PR = "r1"
PROVIDES = "libtidy"
SRCDATE = "20091223"

LIC_FILES_CHKSUM = "file://include/tidy.h;beginline=32;endline=63;md5=098a020dd0e486e04c84df6aef663a88"

SRC_URI = "cvs://anonymous@tidy.cvs.sourceforge.net/cvsroot/tidy;module=tidy;method=pserver;cvsdate=${SRCDATE}"

S = "${WORKDIR}/tidy"

inherit autotools pkgconfig

do_configure_prepend() {
	   . ${S}/build/gnuauto/setup.sh
}

