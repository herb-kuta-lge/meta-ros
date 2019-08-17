# Copyright (c) 2019 LG Electronics, Inc.

DESCRIPTION = "A small image containing just the contents of the ros-core metapackage."

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image
inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

IMAGE_ROOTFS_SIZE = "8192"

IMAGE_INSTALL_append = " ros-core"
