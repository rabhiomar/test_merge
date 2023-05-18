package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.KafkaAdminService;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/topics")
public class KafkaAdminController {

    @Autowired
    private KafkaAdminService kafkaAdminService;

    @PostMapping("/create")
    public ResponseEntity<String> createTopic(@RequestParam String topicName,
                                             @RequestParam int numPartitions,
                                             @RequestParam short replicationFactor) throws ExecutionException, InterruptedException {
        kafkaAdminService.createTopic(topicName, numPartitions, replicationFactor);
        return ResponseEntity.status(HttpStatus.OK).body("Topic created successfully.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTopic(@RequestParam String topicName) throws ExecutionException, InterruptedException {
        kafkaAdminService.deleteTopic(topicName);
        
        return ResponseEntity.status(HttpStatus.OK).body("Topic deleted successfully.");

     //   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    
    @GetMapping("/list")
    public ResponseEntity<Set<String>> listTopics() throws ExecutionException, InterruptedException {
    	Set<String> topics = kafkaAdminService.listTopics();
    	 return ResponseEntity.status(HttpStatus.OK).body(topics);
       // return ResponseEntity.ok(topics);
    }
  
}
