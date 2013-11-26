require recipes-multimedia/gstreamer/gst-plugins.inc

LICENSE = "MIT"

PR = "r0"
S = "${WORKDIR}/${PN}-${PV}"

HOMEPAGE = "http://www.fluendo.com"

DEPENDS = "gst-plugins-base"
PROVIDES = "gst-fluendo-mp3-flump3dec"

SRC_URI = "http://core.fluendo.com/gstreamer/src/${PN}/${PN}-${PV}.tar.bz2"
#SRC_URI += "file://free-gst-query-object_0.10.23.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=259a43dd1c9854b71fc396f74699f4d2"

inherit autotools pkgconfig

#EXTRA_OECONF += "--enable-cpu-tune-rpi"

SRC_URI[md5sum] = "026e222666cea80ee3db1fda87ec424e"
SRC_URI[sha256sum] = "a0e8afb3f2ff9c757b1e831239925ea4d89f83d813ba3a4cff250cf975ab9fd0"

# Select the dynamic plugins we need
PACKAGES_DYNAMIC += "\
gst-plugin-flump3dec* \
"
