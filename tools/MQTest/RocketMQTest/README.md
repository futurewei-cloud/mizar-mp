# RocketMQ Test

## 1. Preparation

Before starting, the following information should be prepared:

- Java 1.8 or above
- docker-compose 1.24.0 or above

Move to this directory and give scripts execution permission
```SHELL
cd ~/RocketMQTest
chmod +x ./scripts/*.sh
```

## 2. Dockers for RocketMQ
Docker files are from: 

[github.com/foxiswho/docker-rocketmq](github.com/foxiswho/docker-rocketmq)

- Start one nameserver, one broker and one console with:

```shell
./scripts/start.sh
```

- If only need to setup one broker on the server,use:
```SHELL
./scripts/start_broker.sh
```

- Move to the corresponding directory and use the following command to end dockers:

```
docker-compose down
```

- Open dashboard in browser:

```
localhost:8180
```



## 3. Producer and Consumer Client

- Firstly, compile and package the target files:

```shell
./scripts/build.sh
```

- Run producer client with parameters:

```sh
./scripts/run_producer.sh  $0 $1
```

- Run consumer client with parameters:

```shell
./scripts/run_consumer.sh $0 $1
```

where $0 stands for the thread numbers and $1 stands for the topic numbers.