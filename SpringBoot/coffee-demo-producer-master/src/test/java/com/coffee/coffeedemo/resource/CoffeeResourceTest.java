package com.coffee.coffeedemo.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coffee.coffeedemo.model.Coffee;
import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.service.CoffeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class CoffeeResourceTest {

	@InjectMocks
	private CoffeeResource coffeeResource;

	@MockBean
	private CoffeeService coffeeService;

	@MockBean
	protected Jackson2ObjectMapperBuilder mapper;

	private ObjectMapper map;

	private MockMvc mockMvc;

	private Coffee coffeeMock;

	private static final Integer COFFEE_ID = 1;

	private List<Coffee> coffeeList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(coffeeResource).build();

		map = new ObjectMapper();
		map.findAndRegisterModules();

		when(mapper.build()).thenReturn(map);

		coffeeMock = new Coffee();
		coffeeMock.setId(COFFEE_ID);
		coffeeMock.setName("coffeeName");
		coffeeMock.setPrice(60.00);
		coffeeMock.setType("coffeeType");

		coffeeList = new ArrayList<>();
		coffeeList.add(coffeeMock);
	}

	@Test
	public void testGetCoffee_ResultOk() throws Exception {

		when(coffeeService.getCoffee(any(Integer.class))).thenReturn(coffeeMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/coffee/{id}", COFFEE_ID)
				.header("Access-Control-Allow-Origin", "*").accept(MediaType.APPLICATION_JSON);;

		mockMvc.perform(requestBuilder).andDo(print());
		 //.andExpect(status().isOk())
		 //.andExpect(content().json("{\"id\": 1, \"name\": \"coffeeName\", \"type\": \"coffeeType\", \"price\": \"60.00\"}"))

	}
	
	@Test
	public void testDeleteCoffe_ResultOk() throws Exception {

		Mockito.doNothing().when(coffeeService).deleteCoffee(any(Integer.class));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/rest/coffee/delete/{id}", COFFEE_ID);

		mockMvc.perform(requestBuilder).andDo(print());
		//.andExpect(status().isNoContent())
	}

	@Test
	public void testGetAllCoffees_ResultOk() throws Exception {
		when(coffeeService.getAllCoffees()).thenReturn(coffeeList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/coffee/all")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andDo(print())

		;
	}
}
