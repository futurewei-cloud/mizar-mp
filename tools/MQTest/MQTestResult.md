## Test Environment

Five machines with ubuntu 18.04

- CPU: Intel Xeon Gold 6152(22 cores, 44 threads)

- Memory：32G * 4

## Test Result

### RocketMQ

#### 1.one broker

| topicNumber | CPU (%) | TPS Produce | TPS Consume | disk util(%) | delay(ms) |
| ----------- | ------- | ----------- | ----------- | ------------ | --------- |
| 0           | 2.11    | 0           | 0           | 0            | 0         |
| 100         | 625     | 76,245.38   | 76,233.48   | 82.84        | 10        |
| 1000        | 780     | 73,644.00   | 72,582.00   | 91.75        | 30        |
| 3000        | 780     | 74,737.85   | 61,315.64   | 93.01        | 101       |
| 5000        | 801     | 73,919.80   | 52,581.76   | 96.89        | 167       |
| 6000        | 789     | 71,059.79   | 44,962.30   | 92.72        | 197       |
| 7000        | 823     | 72,114.44   | 43,316.24   | 93.33        | 221       |

- **Exception occurs  when topic number becomes 8000**
- **The Number of threads used to produce and consume message is 100 and the payload is 1Kb** 
- **Average data over a period of one minute**

#### 2.three brokers

| topicNumber | TPS Producer | TPS Consumer | disk util(%) | delay(ms)     |
| ----------- | :----------- | ------------ | ------------ | ------------- |
| 100         | 30,985.00    | 30,845.00    | 97.78        | 29            |
| 1000        | 30,529.19    | 29,950.50    | 99.56        | 93            |
| 3000        | 30,477.55    | 27,195.28    | 99.92        | 230           |
| 5000        | 30,477.55    | 27,195.28    | 100          | 339           |
| 7000        | 30,902.93    | 11,646.71    | 100          | up to 170000  |
| 8000        | 31,491.40    | 9,426.19     | 99.99        | up to  140000 |
| 9000        | 31,994.80    | 7,031.48     | 100          | up to 350000  |

- **When the topic number comes to 10000, there is an exception**
- **The TPS is the average data of three brokers**



### Pulsar

#### 1. one broker

| topicNumber | TPS Produce | TPS Consume | disk util(%) |
| ----------- | ----------- | ----------- | ------------ |
| 4000        | 31977       | 31691       | >=97         |
| 6000        | 29,553.00   | 28,473.00   | >=97         |
| 8000        | 29680.9     | 29931.55    | >=97         |
| 10000       | 0           | 0           | 0            |

#### 2. three brokers

| topicNumber    | CPU (%) | TPS Produce(average) | TPS Consume(average) | disk util(%)   | Mem(G) |
| -------------- | ------- | -------------------- | -------------------- | -------------- | ------ |
| 1000(10*100)   | 780     | 64,603.00            | 50,615.29            | >=97 | 22     |
| 5000(10*500)   | 801     | 36134.42             | 32257.58             |  >=97 | 38     |
| 10000(10*1000) |         | 31378.69             | 32,078               |  >=97 | 46.7   |
| 10000(20*500)  |         | 59832.1              | 59,773               |  >=97 | 47.1   |
| 30000(100*300) |         | 45,669.31            | 46,779.04            |  >=97 | 59.2   |

- **Disks generally maintain a write rate of 89% and a read rate of 9%. Writing occupies most of the IO, which may cause timeout.**
- **Tps is 0 when topics are created in the early stage, and the time is about 6-8 minutes**

- **Delays are maintained at 100ms-300ms**
