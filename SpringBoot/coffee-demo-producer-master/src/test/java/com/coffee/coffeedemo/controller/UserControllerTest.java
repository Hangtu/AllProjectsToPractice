package com.coffee.coffeedemo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@MockBean
	private UserService userService;

	@MockBean
	protected Jackson2ObjectMapperBuilder mapper;

	private MockMvc mockMvc;

	private User userMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		when(mapper.build()).thenReturn(new ObjectMapper());

		userMock = new User();
		userMock.setId(1);
		userMock.setName("name");
		userMock.setLastName("lastName");
		userMock.setEmail("email@test.com");
	}

	@Test
	public void testSaveUser_ResultOk() throws Exception {
		when(userService.saveUser(any(User.class))).thenReturn(userMock);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.build().writeValueAsString(userMock));

		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
	}

	@Test
	public void testSaveUser_BadRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
				.content("Bad request");

		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetUser_ResultOk() throws Exception {
		when(userService.getUser(any(Integer.class))).thenReturn(userMock);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content()
				.json("{\"id\": 1, \"name\": \"name\", \"lastName\": \"lastName\", \"email\": \"email@test.com\"}"));
	}

	@Test
	public void testUpdateUser_ResultOk() throws Exception {
		userMock.setName("Antonio");

		when(userService.updateUser(any(User.class))).thenReturn(userMock);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.build().writeValueAsString(userMock));

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=utf-8"));
	}

	@Test
	public void testDeleteUser_ResultOk() throws Exception {
		Mockito.doNothing().when(userService).deleteUser(any(Integer.class));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/delete/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());
	}

	@Test(expected = RuntimeException.class)
	public void testGetAllUsers_Exception() throws Exception {
		Mockito.doThrow(RuntimeException.class).when(userService.getAllUsers());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/all").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().is(500));
	}

}
