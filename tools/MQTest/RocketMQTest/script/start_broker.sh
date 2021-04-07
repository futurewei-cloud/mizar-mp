#!/usr/bin/env bash

cd ./docker-rocketmq/rmq/rmq
rm -rf ./logs
rm -rf ./store


mkdir -p ./logs
mkdir -p ./store


chmod -R 777 ./logs
chmod -R 777 ./store


# start docker
docker-compose up -d


# show dockers of rocketmq
docker ps |grep rocketmq