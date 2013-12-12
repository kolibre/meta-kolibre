#require kolibre-common.inc
require recipes-kolibre/images/kolibre-common.inc

IMAGE_FEATUES += "tools-testapps"

IMAGE_INSTALL += "kolibre-sample-client-systemd python-system-buttond"

export IMAGE_BASENAME = "kolibre-set-top-image"
