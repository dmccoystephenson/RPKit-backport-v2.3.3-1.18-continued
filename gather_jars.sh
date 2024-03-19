#!/bin/bash

current_dir=$(pwd)
target_dir=$current_dir/gathered_jars

mkdir -p $target_dir

# gather jars with -all suffix in all subdirectories
for i in $(find . -name "*-all.jar"); do
    cp $i $target_dir
done