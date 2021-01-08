#!/bin/bash

if [[ $CI_COMMIT_BRANCH == "master" && $CI_PIPELINE_SOURCE == "merge_request_event" ]]; then
  readarray -t -d '.' versionParts <<< $(git describe --abbrev=0)
  newVersion=${versionParts[0]}.$((${versionParts[1]} + 1)).${versionParts[2]}
  echo -e "\033[0;32mThe new version number is "$newVersion
fi

mkdir target
latestVersion=$(git describe --always | tee target/currentVersion.txt)
mvn versions:set -DnewVersion=$latestVersion