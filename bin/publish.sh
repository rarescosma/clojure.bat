#!/usr/bin/env bash

DOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

pushd $DOT/../docs

sed 's#<script src="http://localhost:35729/livereload.js?snipver=1" type="text/javascript"></script>##g' \
  uberdoc.html > clojure.html
scp clojure.html vps:~ && rm clojure.html
popd
