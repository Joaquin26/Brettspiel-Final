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

import com.brettspiel.controller.service.IPromotionService;

import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Promotion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/promotion")
@Api(tags="Promotion", value="RESTFul promotion Web Service")
public class PromotionController {

	@Autowired
	IPromotionService promotionService;
	@PostMapping
	@ApiOperation(value = "Insert a new Promotion",notes="Service to create a new promotion")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Promotion created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Promotion> insert(@Valid @RequestBody Promotion promotion){
		Promotion promotionNew=new Promotion();
		promotionNew=promotionService.insert(promotionNew);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(promotionNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Promotion",notes="Service to update a promotion")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Promotion updated correctly"), @ApiResponse(code=404,message = "Promotion not found")})
	public ResponseEntity<Promotion> update(@Valid @RequestBody Promotion promotion){
		promotionService.update(promotion);
		return new ResponseEntity<Promotion>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Promotion",notes="Service to delete a promotion")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Promotion deleted correctly"), @ApiResponse(code=404,message = "Promotion not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Promotion> promotion=promotionService.findById(id);
		if(promotion.isPresent())
			promotionService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Promotions",notes="Service to list all promotions")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Promotions found"), @ApiResponse(code=404,message = "Promotions not found")})
	public ResponseEntity<List<Promotion>> findAll(){
		List<Promotion> promotions= new ArrayList<Promotion>();
		promotions=promotionService.findAll();
		return new ResponseEntity<List<Promotion>>(promotions,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a promotion",notes="Service to get a promotion")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Promotion found"), @ApiResponse(code=404,message = "Promotion not found")})
	public ResponseEntity<Promotion> findById(@PathVariable("id") Integer id){
		Optional<Promotion> promotion=promotionService.findById(id);
		if(promotion.isPresent())
			return new ResponseEntity<Promotion>(promotion.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
}
