package org.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.cli.*;



public class ProducerThreadPool {
    private Config config;

    public ProducerThreadPool(String[] args){
        this.config = new Config();
        this.getOptions(args);
    }

    private void getOptions(String[] args){
        Options options = new Options();
        options.addOption("t",true,"topic number");
        options.addOption("n",true,"thread number");
        options.addOption("y",true,"send message type");
        options.addOption("u",true,"url of namesrv");
        options.addOption("st",true,"sleep time");
        options.addOption("sm",true,"size of message");

        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options,args);
            config.topicNumber = Integer.parseInt(cmd.getOptionValue("t","100"));
            config.threadNumber = Integer.parseInt(cmd.getOptionValue("n","100"));
            config.sendType = Integer.parseInt(cmd.getOptionValue("y","0"));
            config.url = cmd.getOptionValue("u","localhost:9876");
            config.sleepTime = Integer.parseInt(cmd.getOptionValue("st","0"));
            config.messageSize = Integer.parseInt(cmd.getOptionValue("sm","1024"));
        }catch (ParseException e){
            e.printStackTrace();
            System.err.println("--- fail to get options ---");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Producer",options,true);
        }

    }

    public static void main(String[] args) {
        ProducerThreadPool ptp = new ProducerThreadPool(args);
        int threadNumber = ptp.config.threadNumber;
        int topicNumberPerThread = ptp.config.topicNumber/ptp.config.threadNumber;
        int sendType = ptp.config.sendType;
        String url = ptp.config.url;
        int messageSize = ptp.config.messageSize;

        String topicName = "topic";
        int sleepTime = ptp.config.sleepTime;

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int threadIndex = 0; threadIndex < threadNumber; threadIndex++) {
            pool.submit(new ProducerRunnable(url, topicName + Integer.toString(threadIndex),topicNumberPerThread,sendType,messageSize,sleepTime));
        }
        pool.shutdown();
    }

    private class Config{
        int topicNumber;
        int threadNumber;
        int sendType = 0;
        String url;
        int sleepTime = 0;
        int messageSize = 1024;
    }
}
