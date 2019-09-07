# Copyright (c) 2019 LG Electronics, Inc.

ROS_BUILD_DEPENDS_remove = "suitesparse"
ROS_EXPORT_DEPENDS_remove = "suitesparse"
ROS_EXEC_DEPENDS_remove = "suitesparse"

ROS_BUILD_DEPENDS += "suitesparse-cxsparse suitesparse-cholmod"
ROS_EXPORT_DEPENDS += "suitesparse-cxsparse suitesparse-cholmod"
ROS_EXEC_DEPENDS += "suitesparse-cxsparse suitesparse-cholmod"
