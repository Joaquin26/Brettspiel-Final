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

import com.brettspiel.controller.service.IPlayListService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.PlayList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/playList")
@Api(tags="PlayList", value="RESTFul PlayList Web Service")
public class PlayListController {
	@Autowired
	private IPlayListService playListService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new PlayList",notes="Service to create a new playList")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayList created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<PlayList> insert(@Valid @RequestBody PlayList playList){
		PlayList playListNew=new PlayList();
		playListNew=playListService.insert(playList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(playListNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update PlayList",notes="Service to update a playList")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayList updated correctly"), @ApiResponse(code=404,message = "PlayList not found")})
	public ResponseEntity<PlayList> update(@Valid @RequestBody PlayList playList){
		playListService.update(playList);
		return new ResponseEntity<PlayList>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete PlayList",notes="Service to delete a playList")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayList deleted correctly"), @ApiResponse(code=404,message = "PlayList not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<PlayList> playList=playListService.findById(id);
		if(playList.isPresent())
			playListService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List PlayLists",notes="Service to list all playLists")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayLists found"), @ApiResponse(code=404,message = "PlayLists not found")})
	public ResponseEntity<List<PlayList>> findAll(){
		List<PlayList> playLists= new ArrayList<PlayList>();
		playLists=playListService.findAll();
		return new ResponseEntity<List<PlayList>>(playLists,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a playList",notes="Service to get a playList")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayList found"), @ApiResponse(code=404,message = "PlayList not found")})
	public ResponseEntity<PlayList> findById(@PathVariable("id") Integer id){
		Optional<PlayList> playList=playListService.findById(id);
		if(playList.isPresent())
			return new ResponseEntity<PlayList>(playList.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	@GetMapping(value = "findByUserId/{id}")
	@ApiOperation(value = "Get a playList by User Id",notes="Service to get a playList with User Id")
	@ApiResponses(value = {@ApiResponse(code=201,message = "PlayList found"), @ApiResponse(code=404,message = "PlayList not found")})
	public ResponseEntity<List<PlayList>> findByUserId(@PathVariable("id") Integer id){
		List<PlayList> playLists= new ArrayList<PlayList>();
		playLists=playListService.findByUserId(id);
		return new ResponseEntity<List<PlayList>>(playLists,HttpStatus.OK);
	}
}
