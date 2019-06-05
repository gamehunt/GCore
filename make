#!/usr/bin/env bash
if [ "$1" != "noInc" ]; then
 echo "Increment enabled"
./gradlew clean incrementBuildNumber build
else
 echo "Increment disabled"
./gradlew clean build
fi