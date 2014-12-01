#!/bin/sh
#
# Call the API.
#
# author: Lev Himmelfarb
#

usage() {

	cat <<EOD 1>&2
Usage: $1 [-h uri] method rsrc [file]

    -h uri       Base URI of the REST API. Default is "http://localhost:8080/sample/api".
    method       HTTP method. Requried.
    rsrc         Resource URL relative to the API base. Required.
    file         File with the request body.

EOD
}

BASE_URL="http://localhost:8080/sample/api"
while getopts h: opt; do
	case $opt in
	h) BASE_URL="$OPTARG";;
	?) usage "$0"; exit 1;;
	esac
done

shift $(($OPTIND - 1))

if [ -z "$1" ]; then
	usage "$0"
	exit 1
fi
METHOD=`echo -n "$1" | tr '[:lower:]' '[:upper:]'`

RSRC="$2"
if [ -z "$RSRC" ]; then
	usage "$0"
	exit 1
fi

FILE="$3"

AUTH_TOKEN=""
if [ -f headers.txt ]; then
	AUTH_TOKEN=`grep -e '^Authentication-Info:' headers.txt | sed 's/.* nexttoken=\(.*\)\r/\1/'`
fi

AUTH_HEADER=""
if [ -n "$AUTH_TOKEN" ]; then
	AUTH_HEADER="Authorization: AuthToken $AUTH_TOKEN"
fi

if [ -n "$FILE" ]; then
	curl -v -X "$METHOD" -D headers.txt -H "$AUTH_HEADER" \
	-H "Content-Type: application/json" --data-binary @"$FILE" \
	"$BASE_URL$RSRC" \
	| python -mjson.tool
else
	curl -v -X "$METHOD" -D headers.txt -H "$AUTH_HEADER" "$BASE_URL$RSRC" \
	| python -mjson.tool
fi
