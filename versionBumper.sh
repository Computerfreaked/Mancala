#!/bin/bash

if [[ $CI_COMMIT_BRANCH == "master" ]]; then
  readarray -t -d '.' versionParts <<< $(git describe --abbrev=0)
  newVersion=${versionParts[0]}.$((${versionParts[1]} + 1)).0
  echo -e "\033[0;32mThe new version number is "$newVersion"\033[0m"

  git config user.name "bdvries"
  git config user.email "bdvries@sogyo.nl"
  git tag -a $newVersion -m "New feature" --force
  git remote remove origin
  git remote add origin http://my-ci-token:$my_ci_token@git.sogyo.nl/bdvries/mancala-java.git
  git push -o ci.skip origin --tags --force
fi

mkdir target
latestVersion=$(git describe --always | tee target/currentVersion.txt)
mvn versions:set -DnewVersion=$latestVersion