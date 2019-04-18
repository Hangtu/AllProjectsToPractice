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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coffee.coffeedemo.messaging.spring.UserSpringKafka;
import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserSpringKafka userSpringKafka;
	

	private User userMock;

	private Optional<User> optionalUser;

	private List<User> userList;

	private static final Integer USER_ID = 1;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		userMock = new User();
		userMock.setId(1);
		userMock.setName("name");
		userMock.setLastName("lastName");
		userMock.setEmail("email@test.com");

		optionalUser = Optional.of(userMock);

		userList = new ArrayList<>();
		userList.add(userMock);
	}

	@Test
	public void testSaveUser_ResultOk() throws Exception {
		when(userRepository.save(any(User.class))).thenReturn(userMock);

		User userResponse = userService.saveUser(userMock);

		assertThat(userResponse, is(notNullValue()));
	}

	@Test
	public void testGetUser_ResultOk() throws Exception {
		when(userRepository.findById(any(Integer.class))).thenReturn(optionalUser);

		User userResponse = userService.getUser(USER_ID);

		assertThat(userResponse, is(notNullValue()));
	}

	@Test
	public void testUpdateUser_ResultOk() throws Exception {
		userMock.setName("userNameUpdated");

		when(userRepository.findById(any(Integer.class))).thenReturn(optionalUser);
		when(userRepository.save(any(User.class))).thenReturn(userMock);

		User userResponse = userService.updateUser(userMock);

		assertEquals(userResponse.getName(), "userNameUpdated");
	}

	@Test
	public void testDeleteUser_ResultOk() throws Exception {
		Mockito.doNothing().when(userRepository).delete(any(User.class));

		userService.deleteUser(USER_ID);  

		verify(userRepository, times(1)).deleteById(USER_ID);
	}

	@Test
	public void testGetAllUsers_ResultOk() throws Exception {
		List<User> userListExpected = Arrays.asList(userMock);

		when(userRepository.findAll()).thenReturn(userList);

		List<User> userListResponse = userService.getAllUsers();

		assertThat(userListResponse, is(userListExpected));
		assertThat(userListResponse.get(0), is(notNullValue()));
	}

}
