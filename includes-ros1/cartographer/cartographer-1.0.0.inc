# Copyright (c) 2019 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/cartographer:"
SRC_URI += " \
    file://cmake.dont.add.isystem.patch \
"

DEPENDS += " \
    protobuf-native \
"
