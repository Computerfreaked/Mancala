#!/bin/bash

readarray -t -d '.' versionParts <<< $(git describe --abbrev=0)
newVersion=${versionParts[0]}.$((${versionParts[1]} + 1)).${versionParts[2]}
echo -e "\033[0;32mThe new version number is "$newVersion
echo "CI commit branch: "$CI_COMMIT_BRANCH
echo "CI pipeline source :"$CI_PIPELINE_SOURCE