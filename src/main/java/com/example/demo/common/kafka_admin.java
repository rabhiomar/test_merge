package com.example.demo.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class kafka_admin {
    private static final Logger LOGGER = LoggerFactory.getLogger(kafka_admin.class);

	public static void CreateTopic(String topic_name  ,int Partition, int replicationFactor) {
        
        /* Crée un objet Properties pour stocker les propriétés de configuration du client Kafka */
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        
        /* Utilise un bloc try-with-resources pour créer un objet AdminClient et s'assurer qu'il est fermé automatiquement */
        try (AdminClient admin = AdminClient.create(props)) {
            
            /* Crée un objet NewTopic qui représente le nouveau sujet que nous souhaitons créer.
            Dans cet exemple, le nom du sujet est "topic_admin_client",
            il aura 3 partitions et un facteur de réplication de 1. */
            NewTopic newTopic = new NewTopic(topic_name, Partition, (short) replicationFactor);
            
            /* Crée le sujet en utilisant la méthode "createTopics" de l'objet AdminClient, qui prend en
            paramètre une collection d'objets NewTopic */
            admin.createTopics(Collections.singleton(newTopic));
            
            LOGGER.info("Le sujet {} a été créé avec succès", topic_name);
           } 
        
        catch (Exception e) {
               LOGGER.error("Une exception s'est produite lors de la création du sujet : {}", e.getMessage());
           }
       }
	
	
	
	public static void DeleteTopic(String  topic_name) {
		 Properties props = new Properties();
	        props.put("bootstrap.servers", "localhost:9092");
	        try (AdminClient admin = AdminClient.create(props)) {

	 /*. La méthode "deleteTopics" renvoie un objet DeleteTopicsResult qui nous permet d'attendre que la suppression
	 soit terminée. En appelant la méthode "all()" sur cet objet, nous récupérons un objet futur qui nous permet
	 d'attendre que toutes les suppressions soient terminées.En appelant la méthode "get()" sur cet objet futur, nous attendons la fin de la suppression.*/
	            DeleteTopicsResult result = admin.deleteTopics(Collections.singletonList(topic_name));




	/*la méthode all() de cet objet renvoie un objet CompletableFuture<Void> */
	            result.all().get();
	            LOGGER.info("Le sujet {} supprimé avec succès", topic_name);
	        } catch (Exception e) {
	               LOGGER.error("Échec de la suppression du sujet : {}", e.getMessage());
	          
	            e.printStackTrace();
	        }
	    }
	
	
	
	public static Set<String> listTopics() {
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");

	    Set<String> topicNames = new HashSet<>();

	    try (AdminClient admin = AdminClient.create(props)) {
	        ListTopicsResult topics = admin.listTopics();
	        topicNames.addAll(topics.names().get());
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    }

	    return topicNames;
	}
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   
		
	


