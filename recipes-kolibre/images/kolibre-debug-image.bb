#require kolibre-common.inc
require recipes-kolibre/images/kolibre-common.inc

IMAGE_FEATUES += "tools-debug tools-profile"

IMAGE_INSTALL += "libkolibre-clientcore-dbg gdb"

export IMAGE_BASENAME = "kolibre-debug-image"
