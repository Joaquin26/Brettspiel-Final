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

import com.brettspiel.controller.service.ICreditCardService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.CreditCard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/creditcard")
@Api(tags="CreditCard", value="RESTFul CreditCart Web Service")
public class CreditCardController {
	@Autowired
	private ICreditCardService creditCardService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new CreditCard",notes="Service to create a new creditCard")
	@ApiResponses(value = {@ApiResponse(code=201,message = "CreditCard created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<CreditCard> insert(@Valid @RequestBody CreditCard creditCard){
		CreditCard creditcardNew=new CreditCard();
		creditcardNew=creditCardService.insert(creditCard);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(creditcardNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping
	@ApiOperation(value = "Update CreditCard",notes="Service to update a creditCard")
	@ApiResponses(value = {@ApiResponse(code=201,message = "CreditCard updated correctly"), @ApiResponse(code=404,message = "CreditCard not found")})
	public ResponseEntity<CreditCard> update(@Valid @RequestBody CreditCard creditCard){
		creditCardService.update(creditCard);
		return new ResponseEntity<CreditCard>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete CreditCard",notes="Service to delete a creditCard")
	@ApiResponses(value = {@ApiResponse(code=201,message = "CreditCard deleted correctly"), @ApiResponse(code=404,message = "CreditCard not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<CreditCard> creditCard=creditCardService.findById(id);
		if(creditCard.isPresent())
			creditCardService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List CreditCards",notes="Service to list all creditCards")
	@ApiResponses(value = {@ApiResponse(code=201,message = "CreditCards found"), @ApiResponse(code=404,message = "CreditCards not found")})
	public ResponseEntity<List<CreditCard>> findAll(){
		List<CreditCard> creditCards= new ArrayList<CreditCard>();
		creditCards=creditCardService.findAll();
		return new ResponseEntity<List<CreditCard>>(creditCards,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a creditCard",notes="Service to get a creditCard")
	@ApiResponses(value = {@ApiResponse(code=201,message = "CreditCard found"), @ApiResponse(code=404,message = "CreditCard not found")})
	public ResponseEntity<CreditCard> findById(@PathVariable("id") Integer id){
		Optional<CreditCard> creditCard=creditCardService.findById(id);
		if(creditCard.isPresent())
			return new ResponseEntity<CreditCard>(creditCard.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}

}
