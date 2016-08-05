DESCRIPTION = "Library for playback of audio content such as mp3, ogg or wav"
HOMEPAGE = "https://github.com/kolibre/libkolibre-player"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://use_seek_skip_flag.patch"

SRC_URI[md5sum] = "dfd8a9887ee8e21e55619056c874d901"
SRC_URI[sha256sum] = "e8c532282ea150c94cb17bec2cd64139ad8259b292a14ef30dc58f7f88eb32e5"

DEPENDS = "autoconf-archive boost log4cxx gstreamer gst-fluendo-mp3 gst-plugins-base gst-plugins-good gst-plugins-bad"
RDEPENDS_${PN} += "\
        glibc-gconv-iso8859-1 \
        glibc-gconv-cp1252 \
        gst-fluendo-mp3-flump3dec \
        gst-plugins-base-alsa \
        gst-plugins-base-audioconvert \
        gst-plugins-good-audiofx \
        gst-plugins-good-autodetect \
        gst-plugins-good-level \
        gst-plugins-good-souphttpsrc \
        gst-plugins-bad-soundtouch \
        "

EXTRA_OECONF = "--with-boost=${STAGING_LIBDIR}/.."

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools autotools-brokensep pkgconfig

