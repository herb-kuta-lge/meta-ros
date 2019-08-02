# Copyright (c) 2019 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/actionlib:"
SRC_URI += " \
    file://0001-Change-boost-posix_time-milliseconds-init-to-int64_t.patch \
"
