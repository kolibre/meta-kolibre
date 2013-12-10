DESCRIPTION = "Library for playback of audio content such as mp3, ogg or wav"
HOMEPAGE = "https://github.com/kolibre/libkolibre-player"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "5d61ce8831db3ace58047d05eb8223986e354748"
PV = "0.0.1+git${SRCREV}"

SRC_URI = "git://github.com/kolibre/libkolibre-player.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://use_seek_skip_flag.patch"
#SRC_URI += "file://force-setup-of-new-pipeline-on-seek-in-file.patch"


DEPENDS = "autoconf-archive boost log4cxx gstreamer gst-fluendo-mp3 gst-plugins-base gst-plugins-good gst-plugins-bad"
RDEPENDS_${PN} += "\
        glibc-gconv-iso8859-1 \
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

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}
