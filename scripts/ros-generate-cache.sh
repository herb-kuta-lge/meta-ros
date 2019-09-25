# /bin/sh (deliberately no !#)
#
# Usage: cd meta-ros
#        sh scripts/ros-generate-cache.sh ROS_DISTRO YYYYMMDD PATH-TO-LOCAL-ROSDISTRO ROSDISTRO-COMMIT
#            or
#        sh scripts/ros-generate-cache.sh --version
#
#        Generate cache.yaml file for the YYYYMMDD release of ROS_DISTRO from the specified commit of a local
#        ros/rosdistro.git . If running prior to first release of ROS_DISTRO, specify "none" for YYYYMMDD. NB. The release
#        date might not match the commit timestamp of ROSDISTRO-COMMIT; however, the commit timestamp is used for the
#        DATETIME when forming the name of the created branch.
#
#        It will also copy the files from PATH-TO-LOCAL-ROSDISTRO/rosdep to the meta-ros and asks you to
#        adjust /etc/ros/rosdep/sources.list.d/20-default.list before running rosdep update if it doesn't
#        point to these files already.
#
# Copyright (c) 2019 LG Electronics, Inc.

SCRIPT_NAME="ros-generate-cache"
SCRIPT_VERSION="1.0.0"

usage() {
    echo "Usage: cd meta-ros"
    echo "Usage: sh scripts/$SCRIPT_NAME.sh ROS_DISTRO YYYYMMDD PATH-TO-LOCAL-ROSDISTRO ROSDISTRO-COMMIT"
    echo "               or"
    echo "       sh scripts/$SCRIPT_NAME.sh --version"
    exit 1
}

if [ $1 = "--version" ]; then
    echo "$SCRIPT_NAME $SCRIPT_VERSION"
    exit
fi

[ $# -ne 4 ] && usage

ROS_DISTRO=$1
ROS_DISTRO_RELEASE_DATE=$2
ROS_DISTRO_CHECKOUT=$3
ROS_DISTRO_COMMIT=$4

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

case $ROS_DISTRO_RELEASE_DATE in
    none|[2-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9])
        : OK
        ;;

    *)  echo "ABORT: ROS_DISTRO_RELEASE_DATE not YYYYMMDD or 'none': '$3'"
        exit 1
        ;;
esac

if [ ! -d $ROS_DISTRO_CHECKOUT ]; then
    echo "ABORT: '$ROS_DISTRO_CHECKOUT' not found"
    exit 1
fi

if [ -n "$(git status --porcelain=v1)" ]; then
    echo "ABORT: Uncommitted modifications detected by Git -- perhaps invoke 'git reset --hard'?"
    exit 1
fi

cd $ROS_DISTRO_CHECKOUT
path_to_rosdistro=$PWD
ROS_DISTRO_COMMIT=$(git rev-list -1 $ROS_DISTRO_COMMIT) || { echo "ABORT: commit '$ROS_DISTRO_COMMIT' doesn't exist in '$ROS_DISTRO_CHECKOUT'"; exit 1; }

if [ -n "$(git status --porcelain=v1)" ]; then
    echo "ABORT: Uncommitted modifications detected by Git -- perhaps invoke 'git reset --hard'?"
    exit 1
fi

commit_timestamp=$(git log -1 --date=iso-strict --format=%cd $ROS_DISTRO_COMMIT)
# Associate the cache update with the commit date of <ROSDISTRO-COMMIT>.
export ROS_DISTRO_COMMIT_DATETIME=$(date +%Y%m%d%H%M%S --utc -d $commit_timestamp)
unset commit_timestamp
cd - > /dev/null

for f in base python ruby; do
    if ! grep -q "^yaml file://$path_to_rosdistro/rosdep/$f.yaml$" /etc/ros/rosdep/sources.list.d/20-default.list; then
        echo "ABORT: /etc/ros/rosdep/sources.list.d/20-default.list doesn't point to $f.yaml in $path_to_rosdistro"
        exit 1
    fi
done

tmpdir=$(mktemp -t -d ros-generate-cache-XXXXXXXX)
trap "rm -rf $tmpdir" 0

# Create a directory tree under $tmpdir with the contents of ros/rosdistro.git at commit $ROS_DISTRO_COMMIT.
cd $path_to_rosdistro
git archive $ROS_DISTRO_COMMIT | tar -C $tmpdir -xf -
cd - > /dev/null

# Create $tmpdir/$ROS_DISTRO-cache.yaml.gz .
cd $tmpdir

# XXX Fix up a package that's been renamed. Only needed if generating from a commit prior to 2019-09-05.
false && \
sed -i -e 's/micro-xrce-dds-agent:/microxrcedds_agent:/' \
       -e 's@https://github.com/micro-ROS/Micro-XRCE-DDS-Agent-release.git@https://github.com/micro-ROS/microxrcedds_agent-release.git@' \
       $ROS_DISTRO/distribution.yaml

rosdistro_build_cache --preclean --ignore-local $tmpdir/index-v4.yaml $ROS_DISTRO

cd - > /dev/null

rosdep update || { echo "ABORT: 'rosdep update' failed"; exit 1; }

generated="files/$ROS_DISTRO/"
mkdir -p $generated/rosdep
gzip -d $tmpdir/$ROS_DISTRO-cache.yaml.gz -c > $generated/cache.yaml
cp $tmpdir/index-v4.yaml $generated/
cp $path_to_rosdistro/rosdep/base.yaml $path_to_rosdistro/rosdep/python.yaml $path_to_rosdistro/rosdep/ruby.yaml $generated/rosdep/

sed -i -e "1 i# $ROS_DISTRO\/cache.yaml $ROS_DISTRO_RELEASE_DATE $ROS_DISTRO_COMMIT_DATETIME $ROS_DISTRO_COMMIT" files/$ROS_DISTRO/cache.yaml

git add $generated
git commit -a -m "{$ROS_DISTRO} Update cache.yaml and rosdep files from $ROS_DISTRO_RELEASE_DATE as of $ROS_DISTRO_COMMIT_DATETIME"
