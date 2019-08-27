# Backported from meta-raspberrypi Yocto 2.7 Warrior
# 004a1ef firmware: Rename firmware inc file to raspberrypi-firmware.inc

DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=4a4d169737c0786fb9482bb6d30401d1"

inherit deploy nopackages

# Backported from meta-raspberrypi Yocto 2.7 Warrior
# bb6195b raspberrypi-firmware.inc: Update to 20190718
# 35db3d2 raspberrypi-firmware: Update to 20190709
# 004a1ef firmware: Rename firmware inc file to raspberrypi-firmware.inc

RPIFW_DATE ?= "20190718"
SRCREV ?= "d36bde339b067bf13b610fd9741d6351c1dafc38"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "273fe147e3edc92ce38a0ff6bf49e3b9"
SRC_URI[sha256sum] = "f266df66ce92e7726e894c0c870f0d8e7257434f102ba77bd60640970b689aa3"

PV = "${RPIFW_DATE}"

# Use latest version needed for 4.19.66 vc4-fkms

RPIFW_DATE = "20190718b"
SRCREV = "7163480fff007dc98978899b556dcf06f8a462c8"
SRC_URI[md5sum] = "b10044aa637ed170eef2ca6c1dcc8aba"
SRC_URI[sha256sum] = "012bc542157d03d19c52dfb2ff9e828905d1991a8b33420f1a2e3730040c167f"

INHIBIT_DEFAULT_DEPS = "1"

DEPENDS = "rpi-config"

COMPATIBLE_MACHINE = "^rpi$"

S = "${RPIFW_S}/boot"

PR = "r3"

do_deploy() {
    install -d ${DEPLOYDIR}/${PN}

    for i in ${S}/*.elf ; do
        cp $i ${DEPLOYDIR}/${PN}
    done
    for i in ${S}/*.dat ; do
        cp $i ${DEPLOYDIR}/${PN}
    done
    for i in ${S}/*.bin ; do
        cp $i ${DEPLOYDIR}/${PN}
    done

    # Add stamp in deploy directory
    touch ${DEPLOYDIR}/${PN}/${PN}-${PV}.stamp
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${PN}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
