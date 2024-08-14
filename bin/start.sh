#!/bin/bash

script_dir=$(dirname "$(realpath "$0")")

echo "Current path of this script: $script_dir"

CLASSPATH=$script_dir/../lib

echo "Classpath:"$CLASSPATH

java -cp $CLASSPATH/*: tech.taole.notebook.app.Main
