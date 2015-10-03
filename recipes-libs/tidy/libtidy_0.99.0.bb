DESCRIPTION = "LibTidy"
SECTION = "libs"
PRIORITY = "optional"
HOMEPAGE = "http://sourceforge.net/projects/tidy/"
LICENSE = "MIT"
PR = "r1"
PROVIDES = "libtidy"
SRCDATE = "20091223"

LIC_FILES_CHKSUM = "file://include/tidy.h;beginline=32;endline=63;md5=098a020dd0e486e04c84df6aef663a88"

SRC_URI = "cvs://anonymous@tidy.cvs.sourceforge.net/cvsroot/tidy;module=tidy;method=pserver;cvsdate=${SRCDATE} \
           file://0001-Deploy-automake-files-at-the-right-place.patch \
"

S = "${WORKDIR}/tidy"

inherit autotools autotools-brokensep pkgconfig

do_configure_prepend() {
	touch ${S}/NEWS
	touch ${S}/README
	touch ${S}/AUTHORS
	touch ${S}/ChangeLog
}

