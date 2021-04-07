#!/usr/bin/env bash

#$0 for thread numbers,$1 for topic numbers
java -cp ./client/target/test-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.demo.ProducerThreadPool -n $0 -t $1