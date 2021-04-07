package org.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;

import javax.swing.*;
import java.util.Arrays;

public class ProducerRunnable implements Runnable{

    private DefaultMQProducer producer;

    private String url = null;
    private String topicName = null;
    private int topicNumber = 0;
    private int sendType = 0;
    private int sleepTime = 0;
    private int size = 0;
    
    public ProducerRunnable(String url, String topicName, int topicNumber,int sendType,int size,int sleepTime) {
        this.url = url;
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.sendType = sendType;
        this.sleepTime = sleepTime;
        this.size = size;

        this.producer = new DefaultMQProducer("producerGroup-"+ topicName);
        this.producer.setNamesrvAddr(url);
    }

    private static String createSpecificSizeString(int size){
        byte[] temp = new byte[size];
        Arrays.fill(temp, (byte)0);
        String temp_str = new String(temp);
        return temp_str;
    }    

    private void sendMessage(Message message,int sendType) throws Exception{
        switch (sendType) {
            case 0:
                producer.send(message);
                break;
            case 1:
                producer.send(message,new SendCallback(){
                    @Override
                    public void onSuccess(SendResult sendResult){}

                    @Override
                    public void onException(Throwable e){
                        e.printStackTrace();
                        System.err.println("---fail to send message---");
                    }
                });
                break;
            case 2:
                producer.sendOneway(message);
                break;
            default:
                System.err.println("---Unsupport send type---");
                break;
        }
    }

    private void startProducer(String topicName) throws Exception {   
        String tempStr = createSpecificSizeString(size);
        int count = 0;
        while (true) {
            for(int i = 0; i < topicNumber; i++){
                Message message = new Message(topicName+"-"+Integer.toString(i),"TagTest",tempStr.getBytes());
                sendMessage(message,sendType);
                count += 1;
                Thread.sleep(sleepTime);
            }
                
        }     
    }

    @Override
    public void run(){
        try {

            
            producer.setVipChannelEnabled(false);

            producer.start();           
            System.out.printf("%s Producer Started.%n", Thread.currentThread().getName());
            startProducer(topicName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}