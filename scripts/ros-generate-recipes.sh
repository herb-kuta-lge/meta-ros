# /bin/sh (deliberately no !#)
#
# Usage: cd meta-ros
#        [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/ros-generate-recipes.sh ROS_DISTRO
#        (current branch is now superflore/DATETIME)
#            or
#        sh scripts/ros-generate-recipes.sh --version
#
#        - (Re-)Generate all the recipes for the ROS_DISTRO from the existing ROS_DISTRO/cache.yaml.
#
# This script will abort if Git detects any uncommitted modifications, eg, from a previous run that did not complete or untracked
# files (which would otherwise appear in files/ROS_DISTRO/superflore-change-summary.txt).
#
# Copyright (c) 2019 LG Electronics, Inc.

SCRIPT_NAME="ros-generate-recipes"
SCRIPT_VERSION="1.0.0"

usage() {
    echo "Usage: cd meta-ros"
    echo "       [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/$SCRIPT_NAME.sh ROS_DISTRO"
    echo "               or"
    echo "       sh scripts/$SCRIPT_NAME.sh --version"
    exit 1
}

if [ $1 = "--version" ]; then
    echo "$SCRIPT_NAME $SCRIPT_VERSION"
    exit
fi

[ $# -ne 1 ] && usage

[ -z "$SUPERFLORE_GEN_OE_RECIPES" ] && SUPERFLORE_GEN_OE_RECIPES=$(which superflore-gen-oe-recipes)
if [ -z "$SUPERFLORE_GEN_OE_RECIPES" ]; then
    echo "ABORT: superflore-gen-oe-recipes not found"
    exit
fi

ROS_DISTRO=$1
# ROS_VERSION and ROS_PYTHON_VERSION must be in the environment as they appear in "conditional" attributes.
case $ROS_DISTRO in
    "kinetic"|"melodic")
        export ROS_VERSION="1"
        export ROS_PYTHON_VERSION="2"
        ;;

    "crystal"|"dashing")
        export ROS_VERSION="2"
        export ROS_PYTHON_VERSION="3"
        ;;

    *)  echo "ABORT: Unrecognized ROS_DISTRO: $ROS_DISTRO"
        exit 1
        ;;
esac

if [ -n "$(git status --porcelain=v1)" ]; then
    echo "ABORT: Uncommitted modifications detected by Git -- perhaps invoke 'git reset --hard'?"
    exit 1
fi

if [ ! -f files/$ROS_DISTRO/cache.yaml ]; then
    echo "ABORT: files/$ROS_DISTRO/cache.yaml doesn't exist, please use ros-generate-cache.sh to create it"
    exit 1
fi

if [ ! -f files/$ROS_DISTRO/index-v4.yaml ]; then
    echo "ABORT: files/$ROS_DISTRO/index-v4.yaml doesn't exist, please use ros-generate-cache.sh to create it"
    exit 1
fi

ROS_DISTRO_RELEASE_DATE=`head -n 1 files/$ROS_DISTRO/cache.yaml | sed 's/.*cache.yaml \([^ ]*\) \([^ ]*\) \([^ ]*\)$/\1/g'`
ROS_DISTRO_COMMIT_DATETIME=`head -n 1 files/$ROS_DISTRO/cache.yaml | sed 's/.*cache.yaml \([^ ]*\) \([^ ]*\) \([^ ]*\)$/\2/g'`
ROS_DISTRO_COMMIT=`head -n 1 files/$ROS_DISTRO/cache.yaml | sed 's/.*cache.yaml \([^ ]*\) \([^ ]*\) \([^ ]*\)$/\3/g'`

if [ -z "$ROS_DISTRO_RELEASE_DATE" -o -z "$ROS_DISTRO_COMMIT" -o -z "$ROS_DISTRO_COMMIT_DATETIME" ] ; then
    echo "ABORT: files/$ROS_DISTRO/cache.yaml should start with # $ROS_DISTRO/cache.yaml and 3 space separated values ROS_DISTRO_RELEASE_DATE, ROS_DISTRO_COMMIT_DATETIME, ROS_DISTRO_COMMIT, please use ros-generate-cache.sh to re-create it"
    exit 1
fi

case $ROS_DISTRO_RELEASE_DATE in
    none|[2-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9])
        : OK
        ;;

    *)  echo "ABORT: ROS_DISTRO_RELEASE_DATE not YYYYMMDD or 'none': '$3'"
        exit 1
        ;;
esac

skip_keys_option=""
ros1_lisp_packages="euslisp geneus genlisp roslisp actionlib_lisp cl_tf cl_tf2 cl_transforms cl_transforms_stamped cl_urdf cl_utils roslisp_common roslisp_utilities rosemacs ros_emacs_utils roslisp_repl slime_ros slime_wrapper"
case $ROS_DISTRO in
    "kinetic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers grpc nanomsg octovis rosdoc_lite"
        skip_keys_option="$skip_keys_option $ros1_lisp_packages"
        ;;

    "melodic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers iirob_filters grpc nanomsg octovis rosdoc_lite"
        skip_keys_option="$skip_keys_option $ros1_lisp_packages"
        ;;

    *)  : Nothing is skipped for "crystal" and "dashing".
        ;;
esac

only_option=""
# XXX Eventually:
if [ $1 = "regen" ]; then
    shift 2
    [ $# -gt 0 ] && only_option="--only $*"
fi

for f in base python ruby; do
    ff=`grep "^yaml file://.*$f.yaml$" /etc/ros/rosdep/sources.list.d/20-default.list | sed 's#.*file://##g'`
    if ! diff -q $ff files/$ROS_DISTRO/rosdep/$f.yaml; then
        echo "ABORT: files/$ROS_DISTRO/rosdep/$f.yaml is different from $ff configured in /etc/ros/rosdep/sources.list.d/20-default.list, please use ros-generate-cache.sh to re-create it before this script calls rosdep update"
        exit 1
    fi
done

echo "Running rosdep update"
rosdep update || { echo "ABORT: 'rosdep update' failed"; exit 1; }
echo "rosdep update finished"

tmpdir=$(mktemp -t -d ros-generate-recipes-XXXXXXXX)
trap "rm -rf $tmpdir" 0

cp files/$ROS_DISTRO/index-v4.yaml $tmpdir/
sed -i -e "/$ROS_DISTRO-cache.yaml.gz/ s@: .*\$@: file://`pwd`/files/$ROS_DISTRO/cache.yaml@" $tmpdir/index-v4.yaml

# Tell superflore to use this index instead of the upstream one.
export ROSDISTRO_INDEX_URL="file://$tmpdir/index-v4.yaml"
SUPERFLORE_GENERATION_DATETIME="$ROS_DISTRO_COMMIT_DATETIME"

before_commit=$(git rev-list -1 HEAD)
$SUPERFLORE_GEN_OE_RECIPES --dry-run --ros-distro $ROS_DISTRO --output-repository-path . --upstream-branch HEAD \
                            $skip_keys_option $only_option

after_commit=$(git rev-list -1 HEAD)
if [ $after_commit != $before_commit ]; then
    # Identify how the files were generated so that they can be reused.
    git add files/$ROS_DISTRO/cache.yaml files/$ROS_DISTRO/cache.diffme

    generated="conf/ros-distro/include/$ROS_DISTRO/generated-ros-distro.inc"
    [ "$ROS_DISTRO_RELEASE_DATE" = "none" ] && ROS_DISTRO_RELEASE_DATE=""
    cat <<! >> $generated

# From the release announcement or the last field of the "release-ROS_DISTRO-YYYYMMDD" tag for the release in
# https://github.com/ros2/ros2/releases. Prior to the first release of a ROS_DISTRO, it is set to "".
ROS_DISTRO_RELEASE_DATE = "$ROS_DISTRO_RELEASE_DATE"

# The commit of rosdistro/$ROS_DISTRO/distribution.yaml from which the recipes were generated.
ROS_SUPERFLORE_GENERATION_COMMIT = "$ROS_DISTRO_COMMIT"
!
    git add $generated
    git commit --amend -q -C HEAD

    unset generated
fi
