DESCRIPTION = "Library for playing audio prompts"
HOMEPAGE = "https://github.com/kolibre/libkolibre-narrator"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://play_wait_from_file.patch;apply=yes;striplevel=1"

SRC_URI[md5sum] = "acfb00f406af665053e3d72b8ba924a1"
SRC_URI[sha256sum] = "1b1d301522d1780c0631751c5dd6a92b13600c6f2e93c9411bde53d39bc56fad"

DEPENDS = "autoconf-archive boost log4cxx libvorbis mpg123 portaudio-v19 soundtouch sqlite3"

EXTRA_OECONF = "--with-boost=${STAGING_LIBDIR}/.."

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}

do_install_append() {
    # install wait.ogg file to datadir
    install -d ${D}${datadir}/${PN}
    install -m 644 ${S}/utils/special/wait.ogg ${D}${datadir}/${PN}

    # install narrator-utils in staging area for use by other packages
    install -d ${STAGING_BINDIR_NATIVE}
    install -m 0755 ${D}${bindir}/narrator-utils ${STAGING_BINDIR_NATIVE}/

    # change paths to make script usable
    sed -i s,^prefix=.*,prefix=${STAGING_BINDIR}/../..${prefix}, ${STAGING_BINDIR_NATIVE}/narrator-utils
    sed -i s,^exec_prefix=.*,exec_prefix=${STAGING_BINDIR}/../..${exec_prefix}, ${STAGING_BINDIR_NATIVE}/narrator-utils
}

PROVIDES += "${PN}-utils"
PACKAGES += "${PN}-utils"

# We don't add any runtime dependencies to the utils package because we use it natively
#RDEPENDS_${PN}-utils += "espeak sox vorbis-tools"

FILES_${PN}-utils += "/usr/bin/narrator-utils \
        /usr/share/libkolibre/narrator/*.csv \
        /usr/share/libkolibre/narrator/*.db \
        /usr/lib/python*/* \
        "
