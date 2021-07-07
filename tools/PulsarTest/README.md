## Key-shared Mode 

Following the [official introduction](https://pulsar.apache.org/docs/en/concepts-messaging/#key_shared).
(How to assign a key to the consumer is not mentioned on the official website.)

When a producer pushes a message to Pulsar broker in key-based mode, Pulsar broker uses the following function to calculate the hash for the key:
```
Murmur3_32Hash.getInstance().makeHash(key) % 65536
```
and determines which consumer the key would be forwarded to.

### Configuration producer
```java
producer = client.newProducer(Schema.STRING)
        .topic("topic-1")
        .batcherBuilder(BatcherBuilder.KEY_BASED)
        .hashingScheme(HashingScheme.Murmur3_32Hash)
        .create();

producer.newMessage()
                .key("key")
                .value("Key-test").send();
```

### Auto split hash consumer

```java
client.newConsumer()
        .subscriptionMode(SubscriptionMode.Durable)
        .topic("topic-1")
        .subscriptionName("key-subscription-hashed")
        .subscriptionType(SubscriptionType.Key_Shared)
        .keySharedPolicy(KeySharedPolicy.autoSplitHashRange())
        .subscribe();
```

### Consistent auto split hash consumer

The Sticky hash can be calculated manually.

```java

int hashcode = Murmur3_32Hash.getInstance().makeHash("key") % 65536;

client.newConsumer()
        .subscriptionMode(SubscriptionMode.Durable)
        .topic("topic-1")
        .subscriptionName("key-subscription-hashed")
        .subscriptionType(SubscriptionType.Key_Shared)
        .keySharedPolicy(KeySharedPolicy.stickyHashRange().ranges(Range.of(hashcode, hashcode)))
        .subscribe();
```

## Deployment

### Deploy with Docker

Start cluster with 3 brokers and 3 bookies.

```shell script
cd deployment/docker
docker-compose up -d
```

Shutdown the cluster
```shell script
cd deployment/docker
./down.sh
```

### Deploy a cluster on bare metal

Following the [official instruction](https://pulsar.apache.org/docs/en/deploy-bare-metal/).

**Attention**

Different the official instruction, an additonal setting need to change in `bookkeeper.conf`
```
prometheusStatsHttpPort=8100
```

#### 1. Use configure files in `conf` floder to repalce the default configure

File change list

+ zookkeeper.conf
+ bookkeeper.conf
+ broker.conf
+ pulsar-env. sh (Change JVM memory, not necessary)
+ bkenv. sh (Change JVM memory, not necessary)

#### 2. Deplot zookeeper cluster

```shell script
mkdir -pv data/zookeeper
echo 1 > data/zookeeper/myid
```

```shell script
bin/pulsar-daemon start zookeeper
```

#### 3. Initialize cluster metadata

```shell script
bin/pulsar initialize-cluster-metadata --cluster pulsar-cluster-1 --zookeeper host1:2181 --configuration-store host1:2181 --web-service-url http://host1:8080,host2:8080,host3:8080 --web-service-url-tls https://host1:8443,host2:8443,host3:8443 --broker-service-url pulsar://host1:6650,host2:6650,host3:6650 --broker-service-url-tls pulsar+ssl://host1:6651,host2:6651,host3:6651
```

#### 4. Start a bookkeeper cluster

```shell script
bin/bookkeeper shell metaformat
```

```shell script
bin/pulsar-daemon start bookie
```

#### 5. Start a broker cluster

```shell script
bin/pulsar-daemon start broker
```

Then, use the following command to check availability.

```shell script
bin/pulsar-admin brokers list pulsar-cluster
```

### Usage

#### Build the project

```shell script
mvn clean package
```

#### Use `TestConfig.json` to config this project

When conf is change, do not need to rebuild the project

| Parameter Name | Meaning |
| ------------- | :---------- |
| producerThreadNumber | Number of producers |
| consumerThreadNumber | Number of consumers (need to be equial with number of producers) |
| topicNumberPerThread | Number of topic per producer/comsumer (the total topic number = producerThreadNumber * topicNumberPerThread) |
| payload-size | Byte (default 1024, 1 Kb) |
| statsFolderName | path to store stats results |
| enableTopicStats | whether or not record the stats of each topic (*Attention*: each topic will create a record file if it's enable) |

#### Start the project

Start Producer

```shell script
java -cp ./target/PulsarTest-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ProducerThreadPool
```

Start Consumer

```shell script
java -cp ./target/PulsarTest-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ConsumerThreadPool
```

Make a stat

```shell script
java -cp ./target/PulsarTest-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.PulsarStatsAdmin
```

### TODO

+ Support the scenarios where there are more producers than consumers.
+ Support continues stats for Apache Pulsar Cluster