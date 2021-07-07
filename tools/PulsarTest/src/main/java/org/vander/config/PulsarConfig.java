package org.vander.config;

import org.apache.pulsar.shade.com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class PulsarConfig {

    private int producerThreadNumber;
    private int consumerThreadNumber;
    private int topicNumberPerThread;
    private int clientIOThreadsNumber;

    private List<String> producerUrlList;
    private List<String> consumerUrl;
    private List<String> adminUrl;
    private String topicName = "my-topic-";

    private int size;   //byte
    private boolean enableContinueMsg;
    private int msgNumberPerTopic;
    private int createTopicInterval;

    private String statsFolderName;
    private boolean enableTopicStats;
    private boolean enableTopicStatsFileLog;

    public PulsarConfig(){
//        Read config from TestConfig json file
        try {
            Gson gson = new Gson();
            Reader jsonReader = Files.newBufferedReader(Paths.get("TestConfig.json"));
            Map<?, ?> jsonMap = gson.fromJson(jsonReader, Map.class);
            this.producerThreadNumber = Integer.parseInt((String) jsonMap.get("producerThreadNumber"));
            this.consumerThreadNumber = Integer.parseInt((String) jsonMap.get("consumerThreadNumber"));
            this.topicNumberPerThread = Integer.parseInt((String) jsonMap.get("topicNumberPerThread"));
            this.clientIOThreadsNumber = Integer.parseInt((String) jsonMap.get("clientIOThreadsNumber"));

            this.producerUrlList = (List<String>) jsonMap.get("producerUrl");
            this.consumerUrl = (List<String>) jsonMap.get("consumerUrl");
            this.adminUrl = (List<String>) jsonMap.get("adminUrl");

            this.size = Integer.parseInt((String) jsonMap.get("payload-size"));
            this.enableContinueMsg = Boolean.parseBoolean((String) jsonMap.get("enableContinueMsg"));
            this.msgNumberPerTopic = Integer.parseInt((String) jsonMap.get("msgNumberPerTopic"));
            this.createTopicInterval = Integer.parseInt((String) jsonMap.get("createTopicInterval"));

            this.statsFolderName = (String) jsonMap.get("statsFolderName");
            this.enableTopicStats = Boolean.parseBoolean((String) jsonMap.get("enableTopicStats"));
            this.enableTopicStatsFileLog = Boolean.parseBoolean((String) jsonMap.get("enableTopicStatsFileLog"));
        }catch (Exception e){
            e.printStackTrace();
        }

//        Check path available
        File folder = new File(statsFolderName);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
    }

    //    Getter Methods

    public int getClientIOThreadsNumber() {
        return clientIOThreadsNumber;
    }

    public boolean isEnableTopicStatsFileLog() {
        return enableTopicStatsFileLog;
    }

    public boolean isEnableContinueMsg() {
        return enableContinueMsg;
    }

    public int getMsgNumberPerTopic() {
        return msgNumberPerTopic;
    }

    public int getCreateTopicInterval() {
        return createTopicInterval;
    }

    public int getTopicNumberPerThread() {
        return topicNumberPerThread;
    }

    public String getStatsFolderName() {
        return statsFolderName;
    }

    public List<String> getAdminUrl() {
        return adminUrl;
    }

    public boolean isEnableTopicStats() {
        return enableTopicStats;
    }

    public int getProducerThreadNumber() {
        return producerThreadNumber;
    }

    public List<String> getProducerUrlList() {
        return producerUrlList;
    }

    public List<String> getConsumerUrl() {
        return consumerUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getConsumerThreadNumber() {
        return consumerThreadNumber;
    }

    public int getSize() {
        return size;
    }
}
