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

import com.brettspiel.controller.service.IBatchService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Batch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/batch")
@Api(tags="Batch", value="RESTFul batch Web Service")
public class BatchController {

	@Autowired
	IBatchService batchService;
	@PostMapping
	@ApiOperation(value = "Insert a new Batch",notes="Service to create a new batch")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Batch created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Batch> insert(@Valid @RequestBody Batch batch){
		Batch batchNew=new Batch();
		batchNew=batchService.insert(batchNew);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(batchNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Batch",notes="Service to update a batch")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Batch updated correctly"), @ApiResponse(code=404,message = "Batch not found")})
	public ResponseEntity<Batch> update(@Valid @RequestBody Batch batch){
		batchService.update(batch);
		return new ResponseEntity<Batch>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Batch",notes="Service to delete a batch")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Batch deleted correctly"), @ApiResponse(code=404,message = "Batch not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Batch> batch=batchService.findById(id);
		if(batch.isPresent())
			batchService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Batchs",notes="Service to list all batchs")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Batchs found"), @ApiResponse(code=404,message = "Batchs not found")})
	public ResponseEntity<List<Batch>> findAll(){
		List<Batch> batchs= new ArrayList<Batch>();
		batchs=batchService.findAll();
		return new ResponseEntity<List<Batch>>(batchs,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a batch",notes="Service to get a batch")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Batch found"), @ApiResponse(code=404,message = "Batch not found")})
	public ResponseEntity<Batch> findById(@PathVariable("id") Integer id){
		Optional<Batch> batch=batchService.findById(id);
		if(batch.isPresent())
			return new ResponseEntity<Batch>(batch.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
}
