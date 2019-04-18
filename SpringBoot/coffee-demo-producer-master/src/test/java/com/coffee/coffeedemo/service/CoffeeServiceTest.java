package com.coffee.coffeedemo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.coffee.coffeedemo.messaging.plain.CoffeePlainKafka;
import com.coffee.coffeedemo.model.Coffee;
import com.coffee.coffeedemo.repository.CoffeeRepository;

@RunWith(SpringRunner.class)
public class CoffeeServiceTest {

	@InjectMocks
	private CoffeeService coffeeService;

	@MockBean
	private CoffeeRepository coffeeRepository;

	@MockBean
	private CoffeePlainKafka coffeePlainKafka;

	private Optional<Coffee> optionalCoffee;

	private Coffee coffeeMock;

	private List<Coffee> coffeeList;

	private static final Integer COFFEE_ID = 1;

	private static final String COFFEE_EXPENSIVE = "Expensive";

	private static final String COFFEE_CHEAP = "Cheap";

	private static final String COFFEE_HOT = "Hot";

	private static final String COFFEE_NAME = "coffeeName";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		coffeeMock = new Coffee();
		coffeeMock.setId(new Random().nextInt(10));
		coffeeMock.setName("coffeeName");
		coffeeMock.setPrice(60.00);
		coffeeMock.setType("coffeeType");

		optionalCoffee = Optional.of(coffeeMock);

		coffeeList = new ArrayList<>();
		coffeeList.add(coffeeMock);
	}

	@Test
	public void testAddCoffee_ResultOk() throws Exception {
		when(coffeeRepository.save(any(Coffee.class))).thenReturn(coffeeMock);

		Coffee coffeeResponse = coffeeService.addCoffee(coffeeMock);

		assertThat(coffeeResponse, is(notNullValue()));
	}

	@Test
	public void testGetCoffee_ResultOk() throws Exception {
		when(coffeeRepository.findById(any(Integer.class))).thenReturn(optionalCoffee);

		Coffee coffeeResponse = coffeeService.getCoffee(COFFEE_ID);

		assertThat(coffeeResponse, is(notNullValue()));
	}

	@Test
	public void testUpdateCoffee_ResultOk() throws Exception {
		coffeeMock.setName("coffeeNameUpdated");

		when(coffeeRepository.findById(any(Integer.class))).thenReturn(optionalCoffee);
		when(coffeeRepository.save(any(Coffee.class))).thenReturn(coffeeMock);

		Coffee coffeeResponse = coffeeService.updateCoffee(coffeeMock);

		assertEquals(coffeeResponse.getName(), "coffeeNameUpdated");
	}

	@Test
	public void testDeleteCoffee_ResultOk() throws Exception {
		Mockito.doNothing().when(coffeeRepository).delete(any(Coffee.class));

		coffeeService.deleteCoffee(COFFEE_ID);

		verify(coffeeRepository, times(1)).deleteById(COFFEE_ID);
	}

	@Test
	public void testGetAllCoffees_ResultOk() throws Exception {
		List<Coffee> coffeeListExpected = Arrays.asList(coffeeMock);

		when(coffeeRepository.findAll()).thenReturn(coffeeList);

		List<Coffee> coffeeListResponse = coffeeService.getAllCoffees();

		assertThat(coffeeListResponse, is(coffeeListExpected));
		assertThat(coffeeListResponse.get(0), is(notNullValue()));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAllCoffeesPageable_ResultOk() throws Exception {
		when(coffeeRepository.findAll(new PageRequest(0, 1))).thenReturn(new PageImpl<Coffee>(coffeeList));

		Pageable pageable = new PageRequest(0, 1);
		Page<Coffee> coffeeResponse = coffeeService.getAllCoffees(pageable);
		assertThat(coffeeResponse.getContent().get(0), is(notNullValue()));
	}

	@Test
	public void testGetCoffeeByType_ResultOk() throws Exception {
		List<Coffee> coffeeListExpected = coffeeList;
		when(coffeeRepository.findByType(any(String.class))).thenReturn(coffeeList);

		List<Coffee> coffeeListResponse = coffeeService.getCoffeesByType(COFFEE_HOT);

		assertThat(coffeeListResponse, is(coffeeListExpected));
		assertThat(coffeeListResponse.get(0), is(notNullValue()));
	}

	@Test
	public void testGetCoffeeByName_ResultOk() throws Exception {
		Coffee coffeeListExpected = coffeeMock;
		when(coffeeRepository.findAll()).thenReturn(coffeeList);

		Coffee coffeeListResponse = coffeeService.getCoffeeByName(COFFEE_NAME);

		assertThat(coffeeListResponse, is(coffeeListExpected));

	}

	@Test
	public void testGetCoffeeByPrice_ResultOk() throws Exception {
		when(coffeeRepository.findAll()).thenReturn(coffeeList);

		Coffee coffeeResponse = coffeeService.getCoffeeByPrice(COFFEE_EXPENSIVE);
		assertThat(coffeeResponse, is(notNullValue()));

		coffeeResponse = coffeeService.getCoffeeByPrice(COFFEE_CHEAP);
		assertThat(coffeeResponse, is(notNullValue()));

	}

}
