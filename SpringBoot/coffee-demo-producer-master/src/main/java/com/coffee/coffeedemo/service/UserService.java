package com.coffee.coffeedemo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coffee.coffeedemo.messaging.spring.UserSpringKafka;
import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserSpringKafka userSpringKafka;
	
	@Value(value = "${topic.user}")
	public String topic;
	
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {
		userSpringKafka.sendMessage(topic, POST, user.toString());
		return userRepository.save(user);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		userSpringKafka.sendMessage(topic, GET, user.get().toString());
		return user.get();
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		Optional<User> userOptional = userRepository.findById(user.getId());
		User userToUpdate = userOptional.get();

		ModelMapper modelMapper = new ModelMapper();
		userToUpdate = modelMapper.map(user, User.class);
		userSpringKafka.sendMessage(topic, PUT, userToUpdate.toString());
		return userRepository.save(userToUpdate);
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
		userSpringKafka.sendMessage(topic, DELETE, "User [id="+id.toString()+"]");
	}

	/**
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> listUsers = userRepository.findAll();
		userSpringKafka.sendMessage(topic, GET, listUsers.toString());
		return listUsers;
	}
	
	/**
	 * 
	 * @return
	 */
	public Page<User> findUsers(Pageable pageable) {
		Page<User> pageUser = userRepository.findAll(pageable);
		userSpringKafka.sendMessage(topic, GET, pageUser.getContent().toString());
		return pageUser;
	}

}
