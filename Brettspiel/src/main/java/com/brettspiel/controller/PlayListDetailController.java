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

import com.brettspiel.controller.service.IPlayListDetailService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.PlayListDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/playListDetail")
@Api(tags="PlayListDetail", value="RESTFul PlayListDetail Web Service")
public class PlayListDetailController {
	@Autowired
	private IPlayListDetailService playListDetailService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new PlayListDetail",notes="Service to create a new playListDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetail created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<PlayListDetail> insert(@Valid @RequestBody PlayListDetail playListDetail){
		PlayListDetail playListDetailNew=new PlayListDetail();
		playListDetailNew=playListDetailService.insert(playListDetail);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(playListDetailNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update PlayListDetail",notes="Service to update a playListDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetail updated correctly"), @ApiResponse(code=404,message = "PlayListDetail not found")})
	public ResponseEntity<PlayListDetail> update(@Valid @RequestBody PlayListDetail playListDetail){
		playListDetailService.update(playListDetail);
		return new ResponseEntity<PlayListDetail>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete PlayListDetail",notes="Service to delete a playListDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetail deleted correctly"), @ApiResponse(code=404,message = "PlayListDetail not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<PlayListDetail> playListDetail=playListDetailService.findById(id);
		if(playListDetail.isPresent())
			playListDetailService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List PlayListDetails",notes="Service to list all playListDetails")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetails found"), @ApiResponse(code=404,message = "PlayListDetails not found")})
	public ResponseEntity<List<PlayListDetail>> findAll(){
		List<PlayListDetail> playListDetails= new ArrayList<PlayListDetail>();
		playListDetails=playListDetailService.findAll();
		return new ResponseEntity<List<PlayListDetail>>(playListDetails,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a playListDetail",notes="Service to get a playListDetail")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetail found"), @ApiResponse(code=404,message = "PlayListDetail not found")})
	public ResponseEntity<PlayListDetail> findById(@PathVariable("id") Integer id){
		Optional<PlayListDetail> playListDetail=playListDetailService.findById(id);
		if(playListDetail.isPresent())
			return new ResponseEntity<PlayListDetail>(playListDetail.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	@GetMapping(value = "/findByPlayListId{id}")
	@ApiOperation(value = "Get a playListDetail by PlayListId",notes="Service to get a playListDetail by PlayListId")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayListDetail found"), @ApiResponse(code=404,message = "PlayListDetail not found")})
	public ResponseEntity<List<PlayListDetail>> findByPlayListId (@PathVariable("id") Integer id){
		List<PlayListDetail> playListDetails= new ArrayList<PlayListDetail>();
		playListDetails=playListDetailService.findByPlayListId(id);
		return new ResponseEntity<List<PlayListDetail>>(playListDetails,HttpStatus.OK);

	}
}
