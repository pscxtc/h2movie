#!/bin/bash
ss -antl | grep 8000
if [ $? == 0 ]
then
# echo 0
    exit 0
else
# echo 1
    exit 1
fi