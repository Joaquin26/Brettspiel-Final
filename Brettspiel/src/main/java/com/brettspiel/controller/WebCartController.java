package com.brettspiel.controller;

import java.net.URI;
import java.time.LocalDate;
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

import com.brettspiel.controller.service.IWebCartService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.User;
import com.brettspiel.model.WebCart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/webcart")
@Api(tags="WebCart", value="RESTFul WebCart Web Service")
public class WebCartController {
	@Autowired
	private IWebCartService webCartService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new WebCart",notes="Service to create a new webCart")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCart created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<WebCart> insert(@Valid @RequestBody WebCart webCart){
		WebCart webCartNew=new WebCart();
		webCartNew=webCartService.insert(webCart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(webCartNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update WebCart",notes="Service to update a webCart")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCart updated correctly"), @ApiResponse(code=404,message = "WebCart not found")})
	public ResponseEntity<WebCart> update(@Valid @RequestBody WebCart webCart){
		webCartService.update(webCart);
		return new ResponseEntity<WebCart>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete WebCart",notes="Service to delete a webCart")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCart deleted correctly"), @ApiResponse(code=404,message = "WebCart not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<WebCart> webCart=webCartService.findById(id);
		if(webCart.isPresent())
			webCartService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List WebCart",notes="Service to list all webCarts")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCarts found"), @ApiResponse(code=404,message = "WebCarts not found")})
	public ResponseEntity<List<WebCart>> findAll(){
		List<WebCart> webCarts= new ArrayList<WebCart>();
		webCarts=webCartService.findAll();
		return new ResponseEntity<List<WebCart>>(webCarts,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a webCart",notes="Service to get a webCart")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCart found"), @ApiResponse(code=404,message = "WebCart not found")})
	public ResponseEntity<WebCart> findById(@PathVariable("id") Integer id){
		Optional<WebCart> webCart=webCartService.findById(id);
		if(webCart.isPresent())
			return new ResponseEntity<WebCart>(webCart.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	@GetMapping(value = "/findWebcartByUserId/{id}")
	@ApiOperation(value = "Get a WebCart by userId",notes="Service to get a WebCart by user id")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCart found"), @ApiResponse(code=404,message = "WebCart not found")})
	public ResponseEntity<WebCart> findByUserId(@PathVariable("id") Integer id){
		Optional<WebCart> webCart=webCartService.findByUserId(id);
		if(webCart.isPresent())
			return new ResponseEntity<WebCart>(webCart.get(),HttpStatus.OK);
		else 
		{	User user=new User();
			user.setId(id);
			WebCart wt=new WebCart("",LocalDate.now(),LocalDate.now(),LocalDate.now(),user,null);
			WebCart newWebCart=webCartService.insert(wt);
			return new ResponseEntity<WebCart>(newWebCart,HttpStatus.OK);
		}

	}
}
