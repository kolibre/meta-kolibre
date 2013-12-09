DESCRIPTION = "ALSA Library additional plugins"
HOMEPAGE = "http://www.alsa-project.org"
BUGTRACKER = "https://bugtrack.alsa-project.org/alsa-bug/login_page.php"
SECTION = "libs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"
DEPENDS = "pulseaudio"
PR = "r0"

SRC_URI = "ftp://ftp.alsa-project.org/pub/plugins/alsa-plugins-${PV}.tar.bz2"

SRC_URI[md5sum] = "4facd408326ef5567a7d4ceb6589e6b0"
SRC_URI[sha256sum] = "03515134d2009db4dfb2769e0ab0e1fb517c8140ffdfd64a984be968e81c9f1f"

inherit autotools

PROVIDES += "libasound2-plugins"

PACKAGES =+ "libasound2-plugins libasound2-plugins-dev libasound2-plugins-dbg"
RDEPENDS_${PN} += "libasound2-plugins"

FILES_${PN} = ""
FILES_libasound2-plugins = "${libdir}/alsa-lib/lib*.so ${datadir}/alsa"
FILES_libasound2-plugins-dev = "${libdir}/alsa-lib/*.la"
FILES_libasound2-plugins-dbg = "${libdir}/alsa-lib/.debug"

INSANE_SKIP_libasound2-plugins += "dev-so"

ALLOW_EMPTY_alsa-plugins = "1"
ALLOW_EMPTY_libasound2-plugins = "1"
