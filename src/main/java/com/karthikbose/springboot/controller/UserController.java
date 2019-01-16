package com.karthikbose.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthikbose.springboot.exception.UserNotFoundException;
import com.karthikbose.springboot.model.User;
import com.karthikbose.springboot.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public Resource<User> addUser(@RequestBody @Valid User entity) {
		entity = userRepository.save(entity);
		
		//Hateoas Implementation
		Resource<User> resource = new Resource<User>(entity);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		
		resource.add(linkTo.withSelfRel());
		
		return resource;
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User with ID: "+userId+" not found!!");
		}
		
		return user.get();
	}

}
