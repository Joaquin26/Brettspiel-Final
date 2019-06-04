package com.brettspiel.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brettspiel.controller.service.IUserService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user")
@Api(tags="User", value="RESTFul User Web Service")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new User",notes="Service to create a new user")
	@ApiResponses(value = {@ApiResponse(code=201,message = "User created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		User userNew=new User();
		userNew=userService.insert(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(userNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update User",notes="Service to update a user")
	@ApiResponses(value = {@ApiResponse(code=201,message = "User updated correctly"), @ApiResponse(code=404,message = "User not found")})
	public ResponseEntity<User> update(@Valid @RequestBody User user){
		userService.update(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete User",notes="Service to delete a user")
	@ApiResponses(value = {@ApiResponse(code=201,message = "User deleted correctly"), @ApiResponse(code=404,message = "User not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<User> user=userService.findById(id);
		if(user.isPresent())
			userService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List User",notes="Service to list all users")
	@ApiResponses(value = {@ApiResponse(code=201,message = "users found"), @ApiResponse(code=404,message = "users not found")})
	public ResponseEntity<List<User>> findAll(){
		List<User> users= new ArrayList<User>();
		users=userService.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a user",notes="Service to get a user")
	@ApiResponses(value = {@ApiResponse(code=201,message = "User found"), @ApiResponse(code=404,message = "User not found")})
	public ResponseEntity<User> findById(@PathVariable("id") Integer id){
		Optional<User> user=userService.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<User>(user.get(),HttpStatus.OK);
		}
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
}