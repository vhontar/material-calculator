#!/bin/bash

version=$(cat CHANGELOG.md | sed -r '/^\s*$/d' | awk -F '# ' '{print $2}')
echo "Extracted version name: $version"
envman add --key ANDROID_VERSION_NAME --value "$version"