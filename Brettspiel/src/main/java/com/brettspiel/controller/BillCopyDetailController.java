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

import com.brettspiel.controller.service.IBillCopyDetailService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.BillCopyDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/billCopyDetail")
@Api(tags="BillCopyDetail", value="RESTFul billCopyDetail Web Service")
public class BillCopyDetailController {

	@Autowired
	IBillCopyDetailService billCopyDetailService;
	@PostMapping
	@ApiOperation(value = "Insert a new BillCopyDetail",notes="Service to create a new billCopyDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BillCopyDetail created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<BillCopyDetail> insert(@Valid @RequestBody BillCopyDetail billCopyDetail){
		BillCopyDetail billCopyDetailNew=new BillCopyDetail();
		billCopyDetailNew=billCopyDetailService.insert(billCopyDetailNew);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(billCopyDetailNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update BillCopyDetail",notes="Service to update a billCopyDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BillCopyDetail updated correctly"), @ApiResponse(code=404,message = "BillCopyDetail not found")})
	public ResponseEntity<BillCopyDetail> update(@Valid @RequestBody BillCopyDetail billCopyDetail){
		billCopyDetailService.update(billCopyDetail);
		return new ResponseEntity<BillCopyDetail>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete BillCopyDetail",notes="Service to delete a billCopyDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BillCopyDetail deleted correctly"), @ApiResponse(code=404,message = "BillCopyDetail not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<BillCopyDetail> billCopyDetail=billCopyDetailService.findById(id);
		if(billCopyDetail.isPresent())
			billCopyDetailService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List BillCopyDetails",notes="Service to list all billCopyDetails")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BillCopyDetails found"), @ApiResponse(code=404,message = "BillCopyDetails not found")})
	public ResponseEntity<List<BillCopyDetail>> findAll(){
		List<BillCopyDetail> billCopyDetails= new ArrayList<BillCopyDetail>();
		billCopyDetails=billCopyDetailService.findAll();
		return new ResponseEntity<List<BillCopyDetail>>(billCopyDetails,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a billCopyDetail",notes="Service to get a billCopyDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BillCopyDetail found"), @ApiResponse(code=404,message = "BillCopyDetail not found")})
	public ResponseEntity<BillCopyDetail> findById(@PathVariable("id") Integer id){
		Optional<BillCopyDetail> billCopyDetail=billCopyDetailService.findById(id);
		if(billCopyDetail.isPresent())
			return new ResponseEntity<BillCopyDetail>(billCopyDetail.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
}
