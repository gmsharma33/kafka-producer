package com.example.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithKeyValue {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithKeyValue.class);

    public static void main(String[] args) {
        log.info("I am kafka producer");

        String bootStrapServer = "172.24.36.96:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for(int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "Key " + i , "hello world " + i);

            producer.send(producerRecord, (metadata, exception) -> {
                log.info("partition " + metadata.partition() + ", Topic " + metadata.topic() + ", offset " + metadata.offset() +
                        "key " + producerRecord.key());
            });
        }



        producer.close();

        log.info("Successfully sent message");
    }
}
