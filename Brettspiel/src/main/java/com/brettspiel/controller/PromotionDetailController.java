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
import com.brettspiel.controller.service.IPromotionDetailService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.PromotionDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/promotionDetail")
@Api(tags="PromotionDetail", value="RESTFul promotionDetail. Web Service")
public class PromotionDetailController {

	@Autowired
	IPromotionDetailService promotionDetailService;
	@PostMapping
	@ApiOperation(value = "Insert a new PromotionDetail",notes="Service to create a new promotionDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PromotionDetail created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<PromotionDetail> insert(@Valid @RequestBody PromotionDetail promotionDetail){
		PromotionDetail promotionDetailNew=new PromotionDetail();
		promotionDetailNew=promotionDetailService.insert(promotionDetailNew);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(promotionDetailNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update PromotionDetail",notes="Service to update a promotionDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PromotionDetail updated correctly"), @ApiResponse(code=404,message = "PromotionDetail not found")})
	public ResponseEntity<PromotionDetail> update(@Valid @RequestBody PromotionDetail promotionDetail){
		promotionDetailService.update(promotionDetail);
		return new ResponseEntity<PromotionDetail>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete PromotionDetail",notes="Service to delete a promotionDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PromotionDetail deleted correctly"), @ApiResponse(code=404,message = "PromotionDetail not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<PromotionDetail> promotionDetail=promotionDetailService.findById(id);
		if(promotionDetail.isPresent())
			promotionDetailService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List PromotionDetails",notes="Service to list all promotionDetails")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PromotionDetails found"), @ApiResponse(code=404,message = "PromotionDetails not found")})
	public ResponseEntity<List<PromotionDetail>> findAll(){
		List<PromotionDetail> promotionDetails= new ArrayList<PromotionDetail>();
		promotionDetails=promotionDetailService.findAll();
		return new ResponseEntity<List<PromotionDetail>>(promotionDetails,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a promotionDetail",notes="Service to get a promotionDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PromotionDetail found"), @ApiResponse(code=404,message = "PromotionDetail not found")})
	public ResponseEntity<PromotionDetail> findById(@PathVariable("id") Integer id){
		Optional<PromotionDetail> promotionDetail=promotionDetailService.findById(id);
		if(promotionDetail.isPresent())
			return new ResponseEntity<PromotionDetail>(promotionDetail.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
}
