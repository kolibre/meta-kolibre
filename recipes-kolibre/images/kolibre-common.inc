# Base this image on console-image
require recipes-images/angstrom/console-image.bb

IMAGE_FEATUES += "tools-testapps"
DISTRO_FEATURES += "keyboard"

# Include modules in rootfs
IMAGE_INSTALL += " \
    alsa-utils \
    autoload-bcm2835 \
    kolibre-sample-client \
"
