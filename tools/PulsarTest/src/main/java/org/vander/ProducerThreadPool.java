package org.vander;

import org.vander.config.PulsarConfig;
import org.vander.producer.SharedProducerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerThreadPool {
    public static void main(String[] args) {
        PulsarConfig config = new PulsarConfig();

        ExecutorService pool = Executors.newFixedThreadPool(config.getProducerThreadNumber());
        for (int threadIndex = 0; threadIndex < config.getProducerThreadNumber(); threadIndex++) {
            pool.submit(new SharedProducerRunnable(config, threadIndex));
        }
        pool.shutdown();
    }
}
