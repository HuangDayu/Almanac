#!/bin/bash
git checkout --orphan blog_new
git branch -D master
git checkout --orphan master
git add .
time=$(date "+%Y-%m-%d %H:%M:%S")
git commit -m "${time}"
git push -f origin master
echo "Clear success!"