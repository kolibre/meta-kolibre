DESCRIPTION = "Data package for kolibre sample client"
HOMEPAGE = "https://github.com/kolibre/libkolibre-clientcore"
LICENSE = "LGPLv2.1"
SECTION = "media"

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

PV = "1.0"

SRC_URI = "file://COPYING"

DEPENDS = "libkolibre-clientcore libkolibre-narrator-utils"

PACKAGES = "${PN}"

client = "kolibre-sample-client"

do_compile() {
    prompts=${PKG_CONFIG_SYSROOT_DIR}${datadir}/libkolibre-clientcore/prompts/prompts.csv
    messages=${PKG_CONFIG_SYSROOT_DIR}${datadir}/libkolibre-clientcore/prompts/messages.csv
    translations_en=${PKG_CONFIG_SYSROOT_DIR}${datadir}/libkolibre-clientcore/prompts/en/translations.csv
    translations_sv=${PKG_CONFIG_SYSROOT_DIR}${datadir}/libkolibre-clientcore/prompts/sv/translations.csv
    translations_fi=${PKG_CONFIG_SYSROOT_DIR}${datadir}/libkolibre-clientcore/prompts/fi/translations.csv
    output=${S}/messages.db

# build database with narrator-utils
    ${STAGING_BINDIR_NATIVE}/narrator-utils -p ${prompts} -m ${messages} -t ${translations_en} -l en -o ${output}
    ${STAGING_BINDIR_NATIVE}/narrator-utils -p ${prompts} -m ${messages} -t ${translations_sv} -l sv -o ${output} -a
    ${STAGING_BINDIR_NATIVE}/narrator-utils -p ${prompts} -m ${messages} -t ${translations_fi} -l fi -o ${output} -a
}

do_install() {
	install -m 0755 -d ${D}${datadir}/${client}
	install -m 0644 ${S}/messages.db ${D}${datadir}/${client}/messages.db
}

FILES_${PN} = "${datadir}/${client}/*"
