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

import com.brettspiel.controller.service.IWebCartDetailService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.WebCartDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/webcartdetail")
@Api(tags="WebCartDetail", value="RESTFul WebCartDetail Web Service")
public class WebCartDetailController {
	@Autowired
	private IWebCartDetailService webCartDetailService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new WebCartDetail",notes="Service to create a new webCartDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCartDetail created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<WebCartDetail> insert(@Valid @RequestBody WebCartDetail webCartDetail){
		WebCartDetail webCartDetailNew=new WebCartDetail();
		webCartDetailNew=webCartDetailService.insert(webCartDetail);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(webCartDetailNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update WebCartDetail",notes="Service to update a webCartDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCartDetail updated correctly"), @ApiResponse(code=404,message = "WebCartDetail not found")})
	public ResponseEntity<WebCartDetail> update(@Valid @RequestBody WebCartDetail webCartDetail){
		webCartDetailService.update(webCartDetail);
		return new ResponseEntity<WebCartDetail>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete WebCartDetail",notes="Service to delete a webCartDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCartDetail deleted correctly"), @ApiResponse(code=404,message = "WebCartDetail not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<WebCartDetail> webCartDetail=webCartDetailService.findById(id);
		if(webCartDetail.isPresent())
			webCartDetailService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List WebCartDetails",notes="Service to list all webCartDetails")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCartDetails found"), @ApiResponse(code=404,message = "WebCartDetails not found")})
	public ResponseEntity<List<WebCartDetail>> findAll(){
		List<WebCartDetail> webCartDetails= new ArrayList<WebCartDetail>();
		webCartDetails=webCartDetailService.findAll();
		return new ResponseEntity<List<WebCartDetail>>(webCartDetails,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a webCartDetail",notes="Service to get a webCartDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "WebCartDetail found"), @ApiResponse(code=404,message = "WebCartDetail not found")})
	public ResponseEntity<WebCartDetail> findById(@PathVariable("id") Integer id){
		Optional<WebCartDetail> webCartDetail=webCartDetailService.findById(id);
		if(webCartDetail.isPresent())
			return new ResponseEntity<WebCartDetail>(webCartDetail.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
}
