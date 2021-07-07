package org.vander.consumer;

import org.apache.pulsar.client.api.*;
import org.vander.config.PulsarConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SharedConsumerRunnable implements Runnable {

    private PulsarClient client;
    private List<Consumer<byte[]>> consumerList = new ArrayList<Consumer<byte[]>>();

    private PulsarConfig config;
    private int threadIndex;

    public SharedConsumerRunnable(PulsarConfig config, int threadIndex) {
        this.config = config;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        try {
            String url = config.getConsumerUrl().get((int)(threadIndex % config.getConsumerUrl().size()));

            client = PulsarClient.builder()
                    .ioThreads(config.getClientIOThreadsNumber())
                    .serviceUrl(url)
                    .build();

            for (int topicIndex = 0; topicIndex < config.getTopicNumberPerThread(); topicIndex++) {
                consumerList.add(client.newConsumer()
                        .topic(config.getTopicName() + threadIndex + "-" + topicIndex)
//                        .keySharedPolicy(new KeySharedPolicy())
                        .ackTimeout(30, TimeUnit.SECONDS)
                        .subscriptionName(config.getTopicName())
                        .subscriptionType(SubscriptionType.Shared)
                        .subscribe());
            }

            while (true) {
                for (int topicIndex = 0; topicIndex < config.getTopicNumberPerThread(); topicIndex++) {
                    // Wait for a message
                    Message<byte[]> msg = consumerList.get(topicIndex).receive();
                    try {
                        consumerList.get(topicIndex).acknowledge(msg);
                    } catch (Exception e) {
                        System.err.printf("Unable to consume message: %s", e.getMessage());
                        consumerList.get(topicIndex).negativeAcknowledge(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
