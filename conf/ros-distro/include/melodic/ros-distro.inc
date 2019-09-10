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

ROS_UNRESOLVED_PLATFORM_PKG_joystick = "joystick"
ROS_UNRESOLVED_PLATFORM_PKG_libblas-dev = "openblas"
ROS_UNRESOLVED_PLATFORM_PKG_liblapack-dev = "lapack"
ROS_UNRESOLVED_PLATFORM_PKG_suitesparse = "suitesparse"

# "lapack" needs FORTRAN support (copied from conf/local.conf.sample.extended)
# Enabling FORTRAN
# Note this is not officially supported and is just illustrated here to
# show an example of how it can be done
# You'll also need your fortran recipe to depend on libgfortran
FORTRAN_forcevariable = ",fortran"
RUNTIMETARGET_append_pn-gcc-runtime = " libquadmath"
