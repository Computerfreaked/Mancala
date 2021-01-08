#!/bin/bash

#if [[ $CI_COMMIT_BRANCH == "master" && $CI_PIPELINE_SOURCE == "merge_request_event" ]]; then
  readarray -t -d '.' versionParts <<< $(git describe --abbrev=0)
  newVersion=${versionParts[0]}.$((${versionParts[1]} + 1)).${versionParts[2]}
  echo -e "\033[0;32mThe new version number is "$newVersion"\033[0m"

  git config user.name "CICD Version tagging"
  git config user.email "bdvries@sogyo.nl"
  git tag -a $newVersion -m "New feature"
  git push origin --tags

  echo $(git remote -v)
#fi

mkdir target
latestVersion=$(git describe --always | tee target/currentVersion.txt)
mvn versions:set -DnewVersion=$latestVersion