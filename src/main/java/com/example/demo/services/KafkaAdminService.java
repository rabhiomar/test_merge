package com.example.demo.services;

import com.example.demo.common.kafka_admin;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class KafkaAdminService {
    
    public void createTopic(String topic_name  ,int Partition, int replicationFactor) {
        kafka_admin.CreateTopic(topic_name,Partition,replicationFactor);
    }

    public void deleteTopic(String topic_name) {
        kafka_admin.DeleteTopic(topic_name);
    }

    public Set<String> listTopics() {
        return kafka_admin.listTopics();
    }
}
