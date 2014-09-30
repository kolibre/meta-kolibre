DESCRIPTION = "libConfuse is a configuration file parser library"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://README;md5=4883145883ae2d1bb2d003f204e5e744"

PR = "r0"

SRC_URI = "http://savannah.nongnu.org/download/confuse/confuse-2.7.tar.gz"

SRC_URI[md5sum] = "45932fdeeccbb9ef4228f1c1a25e9c8f"
SRC_URI[sha256sum] = "e32574fd837e950778dac7ade40787dd2259ef8e28acd6ede6847ca895c88778"

inherit autotools pkgconfig gettext
