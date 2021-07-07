package org.vander;

import org.apache.pulsar.client.api.*;
import org.vander.config.PulsarConfig;

import java.util.concurrent.TimeUnit;

public class PulsarConnectionTest {
    public static void main(String[] args) {
        String brokerUrl = "pulsar://219.219.223.223:6650";
        String adminUrl = "http://219.219.223.223:8080";

        try {
            PulsarClient client = PulsarClient.builder()
                    .ioThreads(10)
                    .serviceUrl(brokerUrl)
                    .build();

            Producer<byte[]> producer = client.newProducer()
                    .topic("test-topic")
                    .create();

            int msgCount = 0;

            while (msgCount <= 10) {
                producer.newMessage()
                        .value(("test-topic-msg-" + msgCount).getBytes())
                        .sendAsync()
                        .thenAccept(msgId -> {
                        });
                msgCount--;
            }

            Consumer<byte[]> consumer = client.newConsumer()
                    .topic("test-topic")
                    .ackTimeout(30, TimeUnit.SECONDS)
                    .subscriptionName("test-topic")
                    .subscriptionType(SubscriptionType.Shared)
                    .subscribe();

            while (true) {
                Message<byte[]> msg = consumer.receive();
                try {
                    consumer.acknowledge(msg);
                } catch (Exception e) {
                    System.err.printf("Unable to consume message: %s", e.getMessage());
                    consumer.negativeAcknowledge(msg);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
