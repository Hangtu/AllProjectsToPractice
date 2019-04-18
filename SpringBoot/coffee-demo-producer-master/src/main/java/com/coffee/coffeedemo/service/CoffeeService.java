package com.coffee.coffeedemo.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coffee.coffeedemo.messaging.plain.CoffeePlainKafka;
import com.coffee.coffeedemo.model.Coffee;
import com.coffee.coffeedemo.repository.CoffeeRepository;

@Service
public class CoffeeService {

	@Autowired
	private CoffeeRepository coffeeRepository;
	
	@Autowired
	private CoffeePlainKafka coffeePlainKafka;
	
	@Value(value = "${topic.coffee}")
	public String topic;
	
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";

	/**
	 * 
	 * @param coffee
	 * @return
	 */
	public Coffee addCoffee(Coffee coffee) {
		coffeePlainKafka.sendMessage(topic, POST, coffee.toString());
		return coffeeRepository.save(coffee);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Coffee getCoffee(Integer id) {
		Optional<Coffee> coffee = coffeeRepository.findById(id);
		coffeePlainKafka.sendMessage(topic, GET, coffee.get().toString());
		return coffee.get();
	}

	/**
	 * 
	 * @param coffee
	 * @return
	 */
	public Coffee updateCoffee(Coffee coffee) {
		Optional<Coffee> coffeeOptional = coffeeRepository.findById(coffee.getId());
		Coffee coffeeToUpdate = coffeeOptional.get();
		ModelMapper modelMapper = new ModelMapper();
		coffeeToUpdate = modelMapper.map(coffee, Coffee.class);
		coffeePlainKafka.sendMessage(topic, PUT, coffeeToUpdate.toString());
		return coffeeRepository.save(coffeeToUpdate);
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCoffee(Integer id) {
		coffeePlainKafka.sendMessage(topic, DELETE, "Coffee [id="+id.toString()+"]");
		coffeeRepository.deleteById(id);
	}

	/**
	 * 
	 * @return
	 */
	public List<Coffee> getAllCoffees() {
		List<Coffee> listCoffees = coffeeRepository.findAll();
		coffeePlainKafka.sendMessage(topic, GET, listCoffees.toString());
		return listCoffees;
	}

	/**
	 * 
	 * @return
	 */
	public Page<Coffee> getAllCoffees(Pageable pageable) {
		Page<Coffee> allCoffees = coffeeRepository.findAll(pageable);
		coffeePlainKafka.sendMessage(topic, GET, allCoffees.getContent().toString());
		return allCoffees;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Coffee getCoffeeByName(String name) {
		List<Coffee> coffeeList = coffeeRepository.findAll();
		Coffee coffeeByName = coffeeList.stream().filter(coffee -> name.equals(coffee.getName())).findFirst().get();
		coffeePlainKafka.sendMessage(topic, GET, coffeeByName.toString());
		return coffeeByName;
		}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Coffee> getCoffeesByType(String type) {
		List<Coffee> coffeesByTypes = coffeeRepository.findByType(type);
		coffeePlainKafka.sendMessage(topic, GET, coffeesByTypes.toString());
		return coffeesByTypes;
	}

	/**
	 * 
	 * @param priceKeyword
	 * @return
	 */
	public Coffee getCoffeeByPrice(String priceKeyword) throws Exception {
		List<Coffee> coffeeList = coffeeRepository.findAll();
		Coffee coffeeResult = new Coffee();

		if (priceKeyword.equals("Expensive")) {
			coffeeResult = coffeeList.stream().max(Comparator.comparing(Coffee::getPrice))
					.orElseThrow(NoSuchElementException::new);
		} else if (priceKeyword.equals("Cheap")) {
			coffeeResult = coffeeList.stream().min(Comparator.comparing(Coffee::getPrice))
					.orElseThrow(NoSuchElementException::new);
		}
		coffeePlainKafka.sendMessage(topic, GET, coffeeResult.toString());
		return coffeeResult;
	}

}
