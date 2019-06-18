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

import com.brettspiel.controller.service.IImageService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.Image;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/image")
@Api(tags="Image", value="RESTFul Image Web Service")
public class ImageController {
	@Autowired
	private IImageService imageService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new Image",notes="Service to create a new image")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Image created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<Image> insert(@Valid @RequestBody Image image){
		Image imageNew=new Image();
		imageNew=imageService.insert(image);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(imageNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update Image",notes="Service to update a image")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Image updated correctly"), @ApiResponse(code=404,message = "Image not found")})
	public ResponseEntity<Image> update(@Valid @RequestBody Image image){
		imageService.update(image);
		return new ResponseEntity<Image>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete Image",notes="Service to delete a image")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Image deleted correctly"), @ApiResponse(code=404,message = "Image not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<Image> image=imageService.findById(id);
		if(image.isPresent())
			imageService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List Image",notes="Service to list all images")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Images found"), @ApiResponse(code=404,message = "Images not found")})
	public ResponseEntity<List<Image>> findAll(){
		List<Image> images= new ArrayList<Image>();
		images=imageService.findAll();
		return new ResponseEntity<List<Image>>(images,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a image",notes="Service to get a image")
	@ApiResponses(value = {@ApiResponse(code=201,message = "Image found"), @ApiResponse(code=404,message = "Image not found")})
	public ResponseEntity<Image> findById(@PathVariable("id") Integer id){
		Optional<Image> image=imageService.findById(id);
		if(image.isPresent())
			return new ResponseEntity<Image>(image.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
}
