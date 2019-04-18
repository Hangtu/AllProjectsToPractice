package com.coffee.coffeedemo.messaging.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class UserSpringKafka {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String topic, String key, String data) {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, data);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("\nSpring Producer info");
				System.out.println("Message topic: " + result.getRecordMetadata().topic());
				System.out.println("Message sent to partition: " + result.getRecordMetadata().partition());
				System.out.println("Message offset: " + result.getRecordMetadata().offset());
				System.out.println("Message key: " + key);
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Spring Producer info");
				System.out.println("Unable to send message: " + data);
				System.out.println("Due to: " + ex.getMessage());
			}
		});
	}

}
