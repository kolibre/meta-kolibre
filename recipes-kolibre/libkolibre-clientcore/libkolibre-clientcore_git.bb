DESCRIPTION = "Library and sample client"
HOMEPAGE = "https://github.com/kolibre/libkolibre-clientcore"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

SRCREV = "35552932ff442f78c210394dc5ceeb9a760a5224"
PV = "1.0.0+git${SRCREV}"

PROVIDES =+ "kolibre-sample-client-bin"
PACKAGES =+ "kolibre-sample-client-bin"

SRC_URI = "git://github.com/kolibre/libkolibre-clientcore.git;protocol=git;branch=master"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://dont_send_book_position_info.patch;apply=yes;striplevel=1"
SRC_URI += "file://simplified-udisks2-message-parsing.patch;apply=yes;striplevel=1"

DEPENDS = "autoconf-archive boost log4cxx libkolibre-narrator libkolibre-player libkolibre-xmlreader libkolibre-amis libkolibre-daisyonline libkolibre-naviengine glib-2.0 dbus-glib"

EXTRA_OECONF = "--with-boost=${STAGING_LIBDIR}/.. --with-samples"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    autoreconf --force -i
}

do_install_append() {
    install -d ${D}${datadir}/${PN}/prompts/en
    install -d ${D}${datadir}/${PN}/prompts/sv
    install -d ${D}${datadir}/${PN}/prompts/fi
    install -m 644 ${S}/prompts/*.csv ${D}${datadir}/${PN}/prompts
    install -m 644 ${S}/prompts/en/*.csv ${D}${datadir}/${PN}/prompts/en
    install -m 644 ${S}/prompts/sv/*.csv ${D}${datadir}/${PN}/prompts/sv
    install -m 644 ${S}/prompts/fi/*.csv ${D}${datadir}/${PN}/prompts/fi
}

FILES_kolibre-sample-client-bin = "${bindir}/KolibreSampleClient"
