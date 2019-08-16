# Copyright (c) 2019 LG Electronics, Inc.

SHRT_VER = "${@oe.utils.trim_version('${PV}', 2)}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${SHRT_VER}:"

SRC_URI_append = " \
    file://0018-Support-raspberry-pi-3B.patch \
    file://0019-lan78xx-Resolve-issue-with-changing-MAC-address.patch \
    file://raspberrypi3-bplus.cfg \
"
