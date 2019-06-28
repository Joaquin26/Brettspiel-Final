package com.brettspiel.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.brettspiel.controller.service.ICreditCardService;
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

import com.brettspiel.controller.service.IBillService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Bill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/bill")
@Api(tags="Bill", value="RESTFul bill Web Service")
public class BillController {

	@Autowired
	IBillService billService;
	@Autowired
	ICreditCardService creditCardService;

	@PostMapping
	@ApiOperation(value = "Insert a new Bill",notes="Service to create a new bill")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Bill created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Bill> insert(@Valid @RequestBody Bill bill){
		creditCardService.insert(bill.getCreditCard());

		Bill billNew=new Bill();
		billNew=billService.insert(bill);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(billNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Bill",notes="Service to update a bill")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Bill updated correctly"), @ApiResponse(code=404,message = "Bill not found")})
	public ResponseEntity<Bill> update(@Valid @RequestBody Bill bill){
		billService.update(bill);
		return new ResponseEntity<Bill>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Bill",notes="Service to delete a bill")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Bill deleted correctly"), @ApiResponse(code=404,message = "Bill not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Bill> bill=billService.findById(id);
		if(bill.isPresent())
			billService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Bills",notes="Service to list all bills")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Bills found"), @ApiResponse(code=404,message = "Bills not found")})
	public ResponseEntity<List<Bill>> findAll(){
		List<Bill> bills= new ArrayList<Bill>();
		bills=billService.findAll();
		return new ResponseEntity<List<Bill>>(bills,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a bill",notes="Service to get a bill")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Bill found"), @ApiResponse(code=404,message = "Bill not found")})
	public ResponseEntity<Bill> findById(@PathVariable("id") Integer id){
		Optional<Bill> bill=billService.findById(id);
		if(bill.isPresent())
			return new ResponseEntity<Bill>(bill.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
}
