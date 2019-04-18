package com.coffee.coffeedemo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coffee.coffeedemo.model.User;
import com.coffee.coffeedemo.service.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins="*",maxAge=2000,allowedHeaders="*")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(user));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<Page<User>> getPageOfUsers(@PathVariable Integer page, @PathVariable Integer size) {
		return ResponseEntity.ok(userService.findUsers(PageRequest.of(page, size)));
		
	}

}
