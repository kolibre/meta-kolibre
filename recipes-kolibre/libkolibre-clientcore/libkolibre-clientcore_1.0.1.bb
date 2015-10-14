DESCRIPTION = "Library and sample client"
HOMEPAGE = "https://github.com/kolibre/libkolibre-clientcore"
AUTHOR = "Kolibre"
LICENSE = "LGPLv2.1"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

PR = "r0"

PROVIDES =+ "kolibre-sample-client-bin"
PACKAGES =+ "kolibre-sample-client-bin"

SRC_URI = "https://github.com/kolibre/${PN}/archive/${PN}-${PV}.tar.gz"
SRC_URI += "file://no_doxygen.patch;apply=yes;striplevel=1"
SRC_URI += "file://dont_send_book_position_info.patch;apply=yes;striplevel=1"
SRC_URI += "file://simplified-udisks2-message-parsing.patch;apply=yes;striplevel=1"
SRC_URI += "file://link-boost-libraries-into-library.patch;apply=yes;striplevel=1"

SRC_URI[md5sum] = "3bbe3b6bb991b9325202d264cf541400"
SRC_URI[sha256sum] = "bb0f9efd4bbfbc7179183a50c25f52c83758916437448ac432ae738a4dcf1632"

DEPENDS = "autoconf-archive boost log4cxx libkolibre-narrator libkolibre-player libkolibre-xmlreader libkolibre-amis libkolibre-daisyonline libkolibre-naviengine glib-2.0 dbus-glib"

EXTRA_OECONF = "--with-boost=${STAGING_LIBDIR}/.. --with-samples"

S = "${WORKDIR}/${PN}-${PN}-${PV}"

inherit autotools autotools-brokensep pkgconfig

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
