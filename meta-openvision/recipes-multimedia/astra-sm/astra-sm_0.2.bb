SUMMARY = "Astra (Advanced Streamer) SlonikMod"
DESCRIPTION = "Astra (Advanced Streamer) is a professional software to organize \
	Digital TV Service for TV operators and broadcasters, internet service providers, hotels, etc."
SECTION = "multimedia"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libaio"

SRC_URI = "git://github.com/OpenVisionE2/astra-sm.git \
	file://version.patch \
	file://undef_dvb_net.patch \
	file://astra-sm \
	file://astra.conf \
	"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig gettext

do_install_append() {
	install -m 0755 ${S}/tests/t2mi_decap ${D}${bindir}/t2mi_decap
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/astra-sm ${D}/etc/init.d/
	install -m 0644 ${WORKDIR}/astra.conf ${D}/etc/astra/
}

FILES_${PN} += "/etc/init.d/"
FILES_${PN}-dev += "${datadir}"

CONFFILES_${PN} = "${sysconfdir}/astra/astra.conf"

INITSCRIPT_NAME = "astra-sm"
INITSCRIPT_PARAMS = "defaults"

inherit update-rc.d
