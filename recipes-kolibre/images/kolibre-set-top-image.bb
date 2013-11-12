# Base this image on console-image
require recipes-images/angstrom/console-image.bb

IMAGE_FEATUES += "tools-testapps debug-tweaks tools-profile"
DISTRO_FEATURES += "pulseaudio keyboard"

# Include modules in rootfs
IMAGE_INSTALL += "alsa-utils kolibre-sample-client autoload-bcm2835 kolibre-sample-client-systemd"

export IMAGE_BASENAME = "kolibre-set-top-image"

#IMAGE_ROOTFS_EXTRA_SPACE = "524000"
