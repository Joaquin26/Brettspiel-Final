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

import com.brettspiel.controller.service.ICopyService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Copy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/copy")
@Api(tags="Copy", value="RESTFul Copy Web Service")
public class CopyController {
	 @Autowired
	 private ICopyService copyService;
	 
	 @PostMapping
	 @ApiOperation(value = "Insert a new Copy",notes="Service to create a new copy")
	 @ApiResponses(value = {@ApiResponse(code=201,message = "Copy created correctly"), @ApiResponse(code=400,message = "invalid request")})
	 public ResponseEntity<Copy> insert(@Valid @RequestBody Copy copy){
		 Copy copyNew=new Copy();
		 copyNew=copyService.insert(copy);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(copyNew.getId()).toUri();
		 
		 return ResponseEntity.created(location).build();
	 }
	 
	 @PutMapping
	 @ApiOperation(value = "Update Copy",notes="Service to update a copy")
	 @ApiResponses(value = {@ApiResponse(code=201,message = "Copy updated correctly"), @ApiResponse(code=404,message = "Copy not found")})
	 public ResponseEntity<Copy> update(@Valid @RequestBody Copy copy){
		 copyService.update(copy);
		 return new ResponseEntity<Copy>(HttpStatus.OK);
	 }
	
	 @DeleteMapping(value = "/{id}" )
	 @ApiOperation(value = "Delete Copy",notes="Service to delete a copy")
	 @ApiResponses(value = {@ApiResponse(code=201,message = "Copy deleted correctly"), @ApiResponse(code=404,message = "Copy not found")})
	 public void delete(@PathVariable("id") Integer id) {
		 Optional<Copy> copy=copyService.findById(id);
		 if(copy.isPresent())
			 copyService.Delete(id);
		 else 
			 throw new ModelNotFoundException("ID: "+id);
	 }
	 
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 @ApiOperation(value = "List Copies",notes="Service to list all copies")
	 @ApiResponses(value = {@ApiResponse(code=201,message = "Copies found"), @ApiResponse(code=404,message = "Copies not found")})
	 public ResponseEntity<List<Copy>> findAll(){
		 List<Copy> copies= new ArrayList<Copy>();
		 copies=copyService.findAll();
		 return new ResponseEntity<List<Copy>>(copies,HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/{id}")
	 @ApiOperation(value = "Get a copy",notes="Service to get a copy")
	 @ApiResponses(value = {@ApiResponse(code=201,message = "Copy found"), @ApiResponse(code=404,message = "Copy not found")})
	 public ResponseEntity<Copy> findById(@PathVariable("id") Integer id){
		 Optional<Copy> copy=copyService.findById(id);
		 if(copy.isPresent())
			 return new ResponseEntity<Copy>(copy.get(),HttpStatus.OK);
		 else 
			 throw new ModelNotFoundException("ID: "+id);
	 }
	 
	 
	 
	 
}
