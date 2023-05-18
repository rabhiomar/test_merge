package com.example.demo;

import com.example.demo.common.kafka_admin;
import com.example.demo.services.KafkaAdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class KafkaAdminServiceTests {

    @Mock
    private kafka_admin kafkaAdmin;

    @InjectMocks
    private KafkaAdminService kafkaAdminService;

    @Test
    public void testCreateTopic() {
        String topicName = "test-topic";
        int numPartitions = 3;
        short replicationFactor = 1;

        kafkaAdminService.createTopic(topicName, numPartitions, replicationFactor);

        verify(kafkaAdmin, times(1)).CreateTopic(topicName, numPartitions, replicationFactor);
    }

    @Test
    public void testDeleteTopic() {
        String topicName = "test-topic";

        kafkaAdminService.deleteTopic(topicName);

        verify(kafkaAdmin, times(1)).DeleteTopic(topicName);
    }

    @Test
    void listTopicsTest() {
        Set<String> expectedTopics = new HashSet<>();
        expectedTopics.add("topic1");
        expectedTopics.add("topic2");

        when(kafkaAdmin.listTopics()).thenReturn(expectedTopics);

        Set<String> actualTopics = kafkaAdminService.listTopics();

        assertEquals(expectedTopics, actualTopics);
    }
}
