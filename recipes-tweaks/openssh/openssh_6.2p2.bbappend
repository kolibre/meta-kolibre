do_install_append () {
	sed -i -e 's:^#PermitEmptyPasswords.*$:PermitEmptyPasswords yes:g' ${D}${sysconfdir}/ssh/sshd_config
}
