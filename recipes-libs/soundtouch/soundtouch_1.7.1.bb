DESCRIPTION = "Library for changing the Tempo, Pitch and Playback Rates of audio streams or audio files."
SECTION = "libs"
DEPENDS = ""
LICENSE = "LGPLv2.1"
HOMEPAGE = "http://www.surina.net/soundtouch/"

PR = "r0"

SRC_URI = "http://www.surina.net/soundtouch/soundtouch-1.7.1.tar.gz"

SRC_URI[md5sum] = "957500b90593cd6c7d8adc62a64a1851"
SRC_URI[sha256sum] = "385eafa438a9d31ddf84b8d2f713097a3f1fc93d7abdb2fc54c484b777ee0267"

LIC_FILES_CHKSUM = "file://COPYING.TXT;md5=6ba0bdf1d64a1db7eef645db592dc030"

EXTRA_OECONF = "--enable-shared"

S = "${WORKDIR}/soundtouch"

inherit autotools pkgconfig
