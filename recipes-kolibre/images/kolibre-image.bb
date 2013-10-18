# Base this image on console-image
require recipes-images/angstrom/console-image.bb

IMAGE_FEATUES += "tools-testapps debug-tweaks tools-profile"
DISTRO_FEATURES += "pulseaudio keyboard"

# Include modules in rootfs
#IMAGE_INSTALL += "libkolibre-clientcore"
IMAGE_INSTALL += "alsa-utils kolibre-sample-client alsa-states"

export IMAGE_BASENAME = "kolibre-image"
