package com.coffee.coffeedemo.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.coffee.coffeedemo.model.Coffee;
import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.repository.CoffeeRepository;
import com.coffee.coffeedemo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class LoadDatabase {

	private Logger LOG = LoggerFactory.getLogger("CoffeeDemoApplication");
	
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, CoffeeRepository coffeeRepository) {
		return args -> {
			LOG.info("Preloading User database");
			
			LOG.info("User count in DB: {}", userRepository.count());
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream userFile = this.getClass().getClassLoader().getResourceAsStream("users.json");
			// File userFile = ResourceUtils.getFile("users.json");
			List<User> userList = objectMapper.readValue(userFile, new TypeReference<List<User>>(){});
			for (User entry : userList)
				LOG.info("Preloading " + userRepository.save(entry));
			LOG.info("User count in DB: {}", userRepository.count());

			LOG.info("Preloading Coffee database");
			InputStream coffeeFile = this.getClass().getClassLoader().getResourceAsStream("coffees.json");
			//File coffeeFile = ResourceUtils.getFile("classpath:coffees.json");
			List<Coffee> coffeeList = objectMapper.readValue(coffeeFile, new TypeReference<List<Coffee>>(){});
			for (Coffee entry : coffeeList)
				LOG.info("Preloading " + coffeeRepository.save(entry));
			LOG.info("Coffee count in DB: {}", coffeeRepository.count());
		};
	}

}
