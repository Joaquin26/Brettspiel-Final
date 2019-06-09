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

import com.brettspiel.controller.service.ISnackService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Snack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/snack")
@Api(tags="Snack", value="RESTFul Snack Web Service")
public class SnackController {
	@Autowired
	private ISnackService snackService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new Snack",notes="Service to create a new snack")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Snack created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Snack> insert(@Valid @RequestBody Snack snack){
		Snack snackNew=new Snack();
		snackNew=snackService.insert(snack);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(snackNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Snack",notes="Service to update a snack")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Snack updated correctly"), @ApiResponse(code=404,message = "Snack not found")})
	public ResponseEntity<Snack> update(@Valid @RequestBody Snack snack){
		snackService.update(snack);
		return new ResponseEntity<Snack>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Snack",notes="Service to delete a snack")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Snack deleted correctly"), @ApiResponse(code=404,message = "Snack not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Snack> snack=snackService.findById(id);
		if(snack.isPresent())
			snackService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Snack",notes="Service to list all snacks")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Snacks found"), @ApiResponse(code=404,message = "Snacks not found")})
	public ResponseEntity<List<Snack>> findAll(){
		List<Snack> snacks= new ArrayList<Snack>();
		snacks=snackService.findAll();
		return new ResponseEntity<List<Snack>>(snacks,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a snack",notes="Service to get a snack")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Snack found"), @ApiResponse(code=404,message = "Snack not found")})
	public ResponseEntity<Snack> findById(@PathVariable("id") Integer id){
		Optional<Snack> snack=snackService.findById(id);
		if(snack.isPresent())
			return new ResponseEntity<Snack>(snack.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
}
