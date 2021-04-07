package org.demo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.cli.*;


public class ConsumerThreadPool {
    private Config config;

    public ConsumerThreadPool(String[] args){
        this.config = new Config();
        this.getOptions(args);
    }

    private void getOptions(String[] args){
        Options options = new Options();
        options.addOption("t",true,"topic number");
        options.addOption("n",true,"thread number");
        options.addOption("u",true,"url of namesrv");


        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options,args);
            config.topicNumber = Integer.parseInt(cmd.getOptionValue("t","100"));
            config.threadNumber = Integer.parseInt(cmd.getOptionValue("n","100"));
            config.url = cmd.getOptionValue("u","localhost:9876");
        }catch (ParseException e){
            e.printStackTrace();
            System.err.println("-- fail to get options --");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Consumer",options,true);
        }

    }
    public static void main(String[] args) {
        ConsumerThreadPool ctp = new ConsumerThreadPool(args);
        int threadNumber = ctp.config.threadNumber;
        int topicNumberPerThread = ctp.config.topicNumber/ctp.config.threadNumber;
        String url = ctp.config.url;

        String topicName = "topic";
        String tagName = "*";

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int threadIndex = 0; threadIndex < threadNumber; threadIndex++) {
            pool.submit(new ConsumerRunnable(url, topicName + Integer.toString(threadIndex), tagName, topicNumberPerThread));
        }
        pool.shutdown();
    }

    private class Config{
        int topicNumber;
        int threadNumber;
        String url;
    }
}

