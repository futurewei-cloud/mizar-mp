ssh -oPort=6502 cm@47.92.80.237 './apache-pulsar-2.6.0/bin/pulsar-daemon stop broker'
ssh -oPort=6503 cm@47.92.80.237 './apache-pulsar-2.6.0/bin/pulsar-daemon stop broker'
ssh -oPort=6504 cm@47.92.80.237 './apache-pulsar-2.6.0/bin/pulsar-daemon stop broker'