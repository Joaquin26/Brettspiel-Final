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

import com.brettspiel.controller.service.IMembershipService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Membership;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/membership")
@Api(tags="Membership", value="RESTFul Membership Web Service")
public class MembershipController {
	@Autowired
	private IMembershipService membershipService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new Membership",notes="Service to create a new membership")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Membership created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Membership> insert(@Valid @RequestBody Membership membership){
		Membership membershipNew=new Membership();
		membershipNew=membershipService.insert(membership);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(membershipNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Membership",notes="Service to update a membership")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Membership updated correctly"), @ApiResponse(code=404,message = "Membership not found")})
	public ResponseEntity<Membership> update(@Valid @RequestBody Membership membership){
		membershipService.update(membership);
		return new ResponseEntity<Membership>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Membership",notes="Service to delete a membership")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Membership deleted correctly"), @ApiResponse(code=404,message = "Membership not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Membership> membership=membershipService.findById(id);
		if(membership.isPresent())
			membershipService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Membership",notes="Service to list all memberships")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Memberships found"), @ApiResponse(code=404,message = "Memberships not found")})
	public ResponseEntity<List<Membership>> findAll(){
		List<Membership> memberships= new ArrayList<Membership>();
		memberships=membershipService.findAll();
		return new ResponseEntity<List<Membership>>(memberships,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a membership",notes="Service to get a membership")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Membership found"), @ApiResponse(code=404,message = "Membership not found")})
	public ResponseEntity<Membership> findById(@PathVariable("id") Integer id){
		Optional<Membership> membership=membershipService.findById(id);
		if(membership.isPresent())
			return new ResponseEntity<Membership>(membership.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
}
