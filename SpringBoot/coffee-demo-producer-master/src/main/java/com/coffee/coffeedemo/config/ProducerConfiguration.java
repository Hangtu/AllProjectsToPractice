package com.coffee.coffeedemo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@PropertySource("classpath:kafka-producer.properties")
public class ProducerConfiguration {

	@Value(value = "${bootstrap.servers}")
	private String bootstrapServers;

	@Value(value = "${key.serializer}")
	private String keySerializer;

	@Value(value = "${value.serializer}")
	private String valueSerializer;

	@Value(value = "${acks}")
	private String acks;

	@Value(value = "${retries}")
	private String retries;

	@Value(value = "${linger.ms}")
	private String lingerMs;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> producerConfigProps = new HashMap<>();
		producerConfigProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		producerConfigProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		producerConfigProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		producerConfigProps.put(ProducerConfig.ACKS_CONFIG, acks);
		producerConfigProps.put(ProducerConfig.RETRIES_CONFIG, retries);
		producerConfigProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
		return new DefaultKafkaProducerFactory<>(producerConfigProps);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
