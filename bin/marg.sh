#!/usr/bin/env bash

DOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

pushd $DOT/..
java -jar /src/marginalia/target/marginalia-0.9.0-standalone.jar
popd

