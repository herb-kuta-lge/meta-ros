# melodic/ros-distro.inc
# General configuration settings that are dependent on ROS_DISTRO.
#
# Copyright (c) 2019 LG Electronics, Inc.

ROS_DISTRO_CODENAME = "Melodic Morenia"

# The platform release that the metadata for this ROS distro targets. This determines the versions of the platform packages that
# OE must provide. It must be one of the values of ROS_DISTRO_RELEASE_PLATFORMS (which is set in generated-ros-distro.inc).
ROS_DISTRO_BASELINE_PLATFORM = "ubuntu-bionic"

# Set this here, as each ROS distro will must be tested separately with each OE-Core release.
LAYERSERIES_COMPAT_ros-layer = "thud"

# Override settings from generated-ros-distro.inc here.

# Since superflore doesn't know when it's generating a recipe that it will be for a build tool, it can't know that what's in
# ROS_EXEC_DEPEND are also build tools. Manually add them here.
ROS_SUPERFLORE_GENERATED_BUILDTOOLS += " \
"

# apr@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_apr = "apr"

# hddtemp@meta-oe
ROS_UNRESOLVED_PLATFORM_PKG_hddtemp = "hddtemp"

# urdfdom@meta-ros
ROS_UNRESOLVED_PLATFORM_PKG_liburdfdom-dev = "urdfdom"

# urdfdom-headers@meta-ros
ROS_UNRESOLVED_PLATFORM_PKG_liburdfdom-headers-dev = "urdfdom-headers"

# lz4@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_lz4 = "lz4"

# python@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_python = "python"

# python-catkin-pkg@meta-ros
ROS_UNRESOLVED_PLATFORM_PKG_python-catkin-pkg = "python-catkin-pkg"

# python-pycrypto@meta-python
ROS_UNRESOLVED_PLATFORM_PKG_python-crypto = "python-pycrypto"

# XXX TEMP
# python-gnupg@meta-ros 0.4.1
ROS_UNRESOLVED_PLATFORM_PKG_python-gnupg = ""

# XXX TEMP
# NB. "rosbag" has a false build dependency on this
# python-pil@meta-ros 5.1.0
ROS_UNRESOLVED_PLATFORM_PKG_python-imaging = "python-pil"

# python-lxml@meta-python
ROS_UNRESOLVED_PLATFORM_PKG_python-lxml = "python-lxml"

# python-nose@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_python-nose = "python-nose"

# python-nnumpyose@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_python-numpy = "python-numpy"

# opencv@meta-oe
ROS_UNRESOLVED_PLATFORM_PKG_python-opencv = "opencv"

# python-psutil@meta-python
ROS_UNRESOLVED_PLATFORM_PKG_python-psutil = "python-psutil"

# python-setuptools@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_python-pkg-resources = "python-setuptools"

# python-rosdep@meta-ros
ROS_UNRESOLVED_PLATFORM_PKG_python-rosdep = "python-rosdep"

# python-rospkg@meta-ros
ROS_UNRESOLVED_PLATFORM_PKG_python-rospkg = "python-rospkg"

# python-pyserial@meta-python
ROS_UNRESOLVED_PLATFORM_PKG_python-serial = "python-pyserial"

# sip@meta-oe
ROS_UNRESOLVED_PLATFORM_PKG_python-sip = "sip"

# util-linux@openembedded-core
ROS_UNRESOLVED_PLATFORM_PKG_uuid = "util-linux"

# wxpython@meta-oe (requires "opengl" in DISTRO_FEATURES)
ROS_UNRESOLVED_PLATFORM_PKG_python-wxtools = "wxpython"

# python-pyyaml@meta-python
ROS_UNRESOLVED_PLATFORM_PKG_python-yaml = "python-pyyaml"
