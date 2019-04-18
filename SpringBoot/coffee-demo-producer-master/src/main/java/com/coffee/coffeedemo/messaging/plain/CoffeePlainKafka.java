package com.coffee.coffeedemo.messaging.plain;

import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

@Component
public class CoffeePlainKafka {

	private static final String KAFKA_PRODUCER_PROPERTIES = "kafka-producer.properties";

	private Properties loadProperties(String fileName) throws Exception {
		Properties props = new Properties();
		try {
			InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
			props.load(input);
		} catch (Exception e) {
			throw new Exception("Error when loading kafka properties: " + e.getMessage());
		}
		return props;
	}

	public void sendMessage(String topic, String key, String data) {
		try {
			Producer<String, String> producer = new KafkaProducer<String, String>(
					this.loadProperties(KAFKA_PRODUCER_PROPERTIES));
			ProducerRecord<String, String> message = new ProducerRecord<>(topic, key, data);

			producer.send(message, new Callback() {
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e != null) {
						System.out.println("Unable to send message: " + data);
						System.out.print("Due to: ");
						e.printStackTrace();
					} else {
						System.out.println("\nPlain Producer info");
						System.out.println("Message topic: " + metadata.topic());
						System.out.println("Message sent to partition: " + metadata.partition());
						System.out.println("Message offset: " + metadata.offset());
						System.out.println("Message key: " + key);
					}
				}
			});

			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
