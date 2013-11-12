# Filename: autoload-bcm2835.bb

SUMMARY = "Autoload snd-bcm2835 module on kernel start"
DESCRIPTION = "By default the module for the internal sound chip is not loaded on the raspberry pi. \
This is probably configured like this because it makes configuring of external usb soundcards difficult"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

PV = "0.1"
PR = "r0"

do_install(){
    install -d ${D}${sysconfdir}/modules-load.d/
    echo "snd-bcm2835" > ${D}${sysconfdir}/modules-load.d/snd-bcm2835.conf
}

FILES_${PN} = "${sysconfdir}/modules-load.d/snd-bcm2835.conf"
