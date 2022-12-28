#!/bin/bash

PARSED_VERSION=$(cat CHANGELOG.md | sed -r '/^\s*$/d' | awk -F '# ' '{print $2}')
echo "Extracted version name: $PARSED_VERSION"
envman add --key LATEST_VERSION_NAME --value "$PARSED_VERSION"