package org.demo;

import java.util.List;  
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener. ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;



public class ConsumerRunnable implements Runnable{

    private DefaultMQPushConsumer consumer = null;

    private String url = null;
    private String topicName = null;
    private String tagName = null;
    private int topicNumber = 0;

    public ConsumerRunnable(String url, String topicName, String tagName, int topicNumber) {
        this.url = url;
        this.topicName = topicName;
        this.tagName = tagName;
        this.topicNumber = topicNumber;
        this.consumer = new DefaultMQPushConsumer("consumerGroup-"+topicName);
        this.consumer.setNamesrvAddr(url);

    }


    @Override
    public void run(){
        try {
            for(int i = 0; i < topicNumber; i++){
                consumer.subscribe(topicName+"-"+Integer.toString(i), tagName);
            }

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
            System.out.printf("%s Consumer Started.%n", Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}