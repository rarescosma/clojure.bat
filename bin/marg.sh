#!/usr/bin/env bash

DOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

pushd $DOT/..
java -jar $DOT/marg.jar
popd

