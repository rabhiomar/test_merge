package com.example.demo;

import com.example.demo.Controller.KafkaAdminController;
import com.example.demo.services.KafkaAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class KafkaAdminControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KafkaAdminService kafkaAdminService;

    @Test
    public void testCreateTopicIntegration() throws Exception {
        String topicName = "test-topic";
        int numPartitions = 3;
        short replicationFactor = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("/topics/create")
                .param("topicName", topicName)
                .param("numPartitions", String.valueOf(numPartitions))
                .param("replicationFactor", String.valueOf(replicationFactor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // TODO: Assert the behavior or check the created topic in the KafkaAdminService
    }

    @Test
    public void testDeleteTopicIntegration() throws Exception {
        String topicName = "test-topic";

        kafkaAdminService.createTopic(topicName, 3, (short) 1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/topics/delete")
                .param("topicName", topicName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // TODO: Assert the behavior or check the deleted topic in the KafkaAdminService
    }

    @Test
    public void testListTopicsIntegration() throws Exception {
        // Perform any necessary setup or create topics in the KafkaAdminService

        mockMvc.perform(MockMvcRequestBuilders.get("/topics/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // TODO: Assert the behavior or check the retrieved topics in the response
    }
}
