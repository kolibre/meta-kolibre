# Base this image on console-image
require recipes-images/angstrom/console-image.bb

IMAGE_FEATUES += "tools-testapps tools-debug"

# Include modules in rootfs
IMAGE_INSTALL += "alsa-utils kolibre-sample-client libkolibre-clientcore-dbg gdb"

export IMAGE_BASENAME = "kolibre-debug-image"
