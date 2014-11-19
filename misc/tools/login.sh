#!/bin/sh
#
# Login.
#
# author: Lev Himmelfarb
#

usage() {

	cat <<EOD 1>&2
Usage: $1 [-h uri] [-p password] user

    -h uri       Base URI of the REST API. Default is "http://localhost:8080/sample/api".
    -p password  Password to use to login. Default is "password".
    user         Username to use to login. Required.

EOD
}

BASE_URL="http://localhost:8080/sample/api"
USER=""
PASSWORD="password"

while getopts h:p: opt; do
	case $opt in
	h) BASE_URL="$OPTARG";;
	p) PASSWORD="$OPTARG";;
	?) usage "$0"; exit 1;;
	esac
done

USER="${@:$OPTIND:1}"
if [ -z "$USER" ]; then
	usage "$0"
	exit 1
fi

echo -n "$USER" > user.txt

curl -v -D headers.txt \
"$BASE_URL/login?username=$USER&password=$PASSWORD" \
| python -mjson.tool
