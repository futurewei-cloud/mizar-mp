#!/usr/bin/env bash

cd ./docker-rocketmq/rmq
rm -rf ./rmqs/logs
rm -rf ./rmqs/store
rm -rf ./rmq/logs
rm -rf ./rmq/store

mkdir -p ./rmqs/logs
mkdir -p ./rmqs/store
mkdir -p ./rmq/logs
mkdir -p ./rmq/store

chmod -R 777 ./rmqs/logs
chmod -R 777 ./rmqs/store
chmod -R 777 ./rmq/logs
chmod -R 777 ./rmq/store


# start dockers
docker-compose up -d


# show dockers of rocketmq
docker ps |grep rocketmq