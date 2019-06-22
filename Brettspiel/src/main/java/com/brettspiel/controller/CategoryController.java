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

import com.brettspiel.controller.service.ICategoryService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/category")
@Api(tags="Category", value="RESTFul Category Web Service")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new Category",notes="Service to create a new category")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Category created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Category> insert(@Valid @RequestBody Category category){
		Category categoryNew=new Category();
		categoryNew=categoryService.insert(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoryNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Category",notes="Service to update a category")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Category updated correctly"), @ApiResponse(code=404,message = "Category not found")})
	public ResponseEntity<Category> update(@Valid @RequestBody Category category){
		categoryService.update(category);
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Category",notes="Service to delete a category")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Category deleted correctly"), @ApiResponse(code=404,message = "Category not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Category> category=categoryService.findById(id);
		if(category.isPresent())
			categoryService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Categories",notes="Service to list all categories")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Categories found"), @ApiResponse(code=404,message = "Categories not found")})
	public ResponseEntity<List<Category>> findAll(){
		List<Category> categories= new ArrayList<Category>();
		categories=categoryService.findAll();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a category",notes="Service to get a category")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Category found"), @ApiResponse(code=404,message = "Category not found")})
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		Optional<Category> category=categoryService.findById(id);
		if(category.isPresent())
			return new ResponseEntity<Category>(category.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
	@GetMapping(value = "/name/{name}")
	@ApiOperation(value = "Get a category",notes="Service to get a category")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Category found"), @ApiResponse(code=404,message = "Category not found")})
	public ResponseEntity<Category> findById(@PathVariable("name") String name){
		Optional<Category> category=categoryService.findByName(name);
		if(category.isPresent())
			return new ResponseEntity<Category>(category.get(),HttpStatus.OK);
		else 
		{
			category.get().setId(-1);
			return new ResponseEntity<Category>(category.get(),HttpStatus.OK);
		}

	}
}