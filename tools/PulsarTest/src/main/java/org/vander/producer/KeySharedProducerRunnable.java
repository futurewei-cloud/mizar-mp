package org.vander.producer;

import org.apache.pulsar.client.api.BatcherBuilder;
import org.apache.pulsar.client.api.HashingScheme;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.vander.config.PulsarConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeySharedProducerRunnable implements  Runnable{

    private PulsarClient client;
    private List<Producer<byte[]>> producerList = new ArrayList<Producer<byte[]>>();

    private PulsarConfig config;
    private int threadIndex;

    private String payload;

    public KeySharedProducerRunnable(PulsarConfig config, int threadIndex) {
        this.config = config;
        this.threadIndex = threadIndex;
        this.payload = createSpecificSizeString(config.getSize());
    }

    private static String createSpecificSizeString(int size){
        byte[] temp = new byte[size];
        Arrays.fill(temp, (byte)0);
        String temp_str = new String(temp);
        return temp_str;
    }

    @Override
    public void run() {
        try {
            String url = config.getProducerUrlList().get((int)(threadIndex % config.getProducerUrlList().size()));

            client = PulsarClient.builder()
                    .ioThreads(config.getClientIOThreadsNumber())
                    .serviceUrl(url)
                    .build();

            for (int topicIndex = 0; topicIndex < config.getTopicNumberPerThread(); topicIndex++) {
                producerList.add(client.newProducer()
                        .batcherBuilder(BatcherBuilder.KEY_BASED)
                        .hashingScheme(HashingScheme.Murmur3_32Hash)
                        .topic(config.getTopicName() + threadIndex + "-" + topicIndex)
                        .create());
                Thread.sleep(config.getCreateTopicInterval());
            }
            int msgCount = 0;
            while (config.isEnableContinueMsg() || msgCount <= config.getMsgNumberPerTopic()) {
                for (int topicIndex = 0; topicIndex < config.getTopicNumberPerThread(); topicIndex++) {
                    producerList.get(topicIndex).newMessage()
                            .value(payload.getBytes())
                            .sendAsync()
                            .thenAccept(msgId -> {
                            });
                }
                msgCount++;
            }

            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
