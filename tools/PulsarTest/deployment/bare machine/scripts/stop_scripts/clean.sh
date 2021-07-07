#clean data for server 1
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/logs'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/bookkeeper'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/zookeeper/version-2'

#clean data for server 2
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/logs'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/bookkeeper'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/zookeeper/version-2'

#clean data for server 3
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/logs'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/bookkeeper'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/data/zookeeper/version-2'