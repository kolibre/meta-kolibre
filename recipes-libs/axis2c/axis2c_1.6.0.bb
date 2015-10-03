DESCRIPTION = "Apache web services engine"
HOMEPAGE = "http://axis.apache.org/axis2/c/core"
AUTHOR = "Johan Abbors johan.abbors@pratsam.com"
LICENSE = "Apache-2.0"
SECTION = "libs"

SRCREV = "909716"

PV = "1.6.0+svn${SRCREV}"
PR = "r1"

S = "${WORKDIR}/c"

DEPENDS = "libxml2 curl openssl"
LIC_FILES_CHKSUM = "file://COPYING;md5=be650d9617f9f9d24bcaccf78a97b28b"

SRC_URI = " \
    svn://svn.apache.org/repos/asf/axis/axis2/c/core/branches/c/1.6.x/1.6.0;module=c;protocol=https \
    file://axis2c-1.6.0_ac_headers.patch;striplevel=3\
	file://axis2c-1.6.0_enable_ssl_transport.patch \
	file://axis2c-1.6.0_curl_compile_error.patch \
	file://axis2c-1.6.0_curl_ssl_verifypeer_0.patch;striplevel=3 \
	file://axis2c-1.6.0_curl_ssl_verifyhost_0.patch;striplevel=2 \
	file://axis2c-1.6.0_curl_useragent.patch \
	file://axis2c-1.6.0_curl_connect_timeout.patch \
	file://axis2c-1.6.0_respectConst_sslCTX.patch;striplevel=0 \
	file://axis2c-1.6.0_rm_install_data_hook.patch;striplevel=2 \
	file://axis2c-1.6.0_timeout_and_low_speed_limit.patch \
"

SRC_URI[md5sum] = "67663a8744fa5d6f06c7feb22a94035e"
SRC_URI[sha256sum] = "70ba4cb9d4a0551b9f56a84e90239f0826a5a19c38ef9f5523b40a02ef18a00c"

EXTRA_OECONF = " \
                --enable-guththila=no \
                --enable-libxml2 \
                --enable-libcurl \
                --enable-openssl"

TARGET_CXXFLAGS += "-Wno-error=unused-but-set-variable -Wno-error=uninitialized -Wno-error=maybe-uninitialized"
TARGET_CFLAGS += "-Wno-error=unused-but-set-variable -Wno-error=uninitialized -Wno-error=maybe-uninitialized"

inherit autotools autotools-brokensep pkgconfig

PACKAGES = "${PN}-dbg ${PN} ${PN}-bin ${PN}-dev ${PN}-staticdev ${PN}-doc"

FILES_${PN} += "${prefix}/axis2.xml \
        ${prefix}/modules/addressing/lib*${SOLIBS} \
        ${prefix}/modules/addressing/module.xml \
        ${prefix}/modules/logging/lib*${SOLIBS} \
        ${prefix}/modules/logging/module.xml \
        ${prefix}/modules/addressing/lib*.so \
        ${prefix}/modules/logging/lib*.so \
        ${libdir}/*.so \
        "

FILES_${PN}-dev += "${bindir}/tools/wsdl2c/WSDL2C.sh \
        ${prefix}/modules/addressing/lib*.la \
        ${prefix}/modules/logging/lib*.la \
        "

FILES_${PN}-staticdev += " \
        ${prefix}/modules/addressing/lib*.a \
        ${prefix}/modules/logging/lib*.a \
        "

FILES_${PN}-dbg += "${libdir}/bin/.debug \
        ${bindir}/tools/md5/.debug \
        ${bindir}/tools/tcpmon/.debug \
        ${prefix}/modules/addressing/.debug \
        ${prefix}/modules/logging/.debug \
        "

FILES_${PN}-bin += "${bindir}/axis2_http_server \
        ${bindir}/tools/md5/md5 \
        ${bindir}/tools/tcpmon/tcpmon \
        "

FILES_${PN}-doc = "${prefix}/*"

INSANE_SKIP_${PN} += "dev-so"
