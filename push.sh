#!/bin/bash
git add .
if [[ $1 ]]; then
	git commit -m $1
else
	time=$(date "+%Y-%m-%d %H:%M:%S")
	git commit -m "${time}"
fi
git push origin master
echo "update success!"