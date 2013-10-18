require recipes-multimedia/gstreamer/gst-plugins.inc

LICENSE = "MIT"

PR = "r0"
S = "${WORKDIR}/${PN}-${PV}"

HOMEPAGE = "http://www.fluendo.com"

DEPENDS = "gst-plugins-base"
PROVIDES = "gst-fluendo-mp3-flump3dec"

SRC_URI = "http://core.fluendo.com/gstreamer/src/${PN}/${PN}-${PV}.tar.bz2"
SRC_URI += "file://free-gst-query-object_0.10.23.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=259a43dd1c9854b71fc396f74699f4d2"

inherit autotools pkgconfig

EXTRA_OECONF += "--enable-cpu-tune-rpi"

SRC_URI[md5sum] = "325f751c21fc1780c0ff58027a738527"
SRC_URI[sha256sum] = "e1914c133f18cbe50ffe91e5f4a552fb1aff6ac2d5606e56ee68149510a65bef"

# Select the dynamic plugins we need
PACKAGES_DYNAMIC += "\
gst-plugin-flump3dec* \
"
