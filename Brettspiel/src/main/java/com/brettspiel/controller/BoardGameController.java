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

import com.brettspiel.controller.service.IBoardGameService;
import com.brettspiel.exception.ModelNotFoundException;
import com.brettspiel.model.BoardGame;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/boardgame")
@Api(tags="BoardGame", value="RESTFul BoardGame Web Service")
public class BoardGameController {
	@Autowired
	private IBoardGameService boardGameService;
	
	@PostMapping
	@ApiOperation(value = "Insert a new BoardGame",notes="Service to create a new BoardGame")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGame created correctly"), @ApiResponse(code=400,message = "invalid request")})
	public ResponseEntity<BoardGame> insert(@Valid @RequestBody BoardGame boardGame){
		BoardGame boardGameNew=new BoardGame();
		boardGameNew=boardGameService.insert(boardGame);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(boardGameNew.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update BoardGame",notes="Service to update a boardGame")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGame updated correctly"), @ApiResponse(code=404,message = "BoardGame not found")})
	public ResponseEntity<BoardGame> update(@Valid @RequestBody BoardGame boardGame){
		boardGameService.update(boardGame);
		return new ResponseEntity<BoardGame>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}" )
	@ApiOperation(value = "Delete BoardGame",notes="Service to delete a boardGame")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGame deleted correctly"), @ApiResponse(code=404,message = "BoardGame not found")})
	public void delete(@PathVariable("id") Integer id) {
		Optional<BoardGame> boardGame=boardGameService.findById(id);
		if(boardGame.isPresent())
			boardGameService.Delete(id);
		else 
			throw new ModelNotFoundException("ID: "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List BoardGames",notes="Service to list all boardGames")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGames found"), @ApiResponse(code=404,message = "BoardGames not found")})
	public ResponseEntity<List<BoardGame>> findAll(){
		List<BoardGame> boardGames= new ArrayList<BoardGame>();
		boardGames=boardGameService.findAll();
		return new ResponseEntity<List<BoardGame>>(boardGames,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a boardGame",notes="Service to get a boardGame")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGame found"), @ApiResponse(code=404,message = "BoardGame not found")})
	public ResponseEntity<BoardGame> findById(@PathVariable("id") Integer id){
		Optional<BoardGame> boardGame=boardGameService.findById(id);
		if(boardGame.isPresent())
			return new ResponseEntity<BoardGame>(boardGame.get(),HttpStatus.OK);
		else 
			throw new ModelNotFoundException("ID: "+id);

	}
	
	@GetMapping(value = "/filter/{categoryName}/{age}/{minCost}/{maxCost}/{miPlayers}")
	@ApiOperation(value = "List BoardGames by filter",notes="Service to list all boardGames filters")
	@ApiResponses(value = {@ApiResponse(code=201,message = "BoardGames found"), @ApiResponse(code=404,message = "BoardGames not found")})
	public ResponseEntity<List<BoardGame>> filter(String categoryName,Integer age,Float minCost,Float maxCost,Integer minPlayers){
		List<BoardGame> boardGames= new ArrayList<BoardGame>();
		boardGames=boardGameService.filter(categoryName,age,minCost,maxCost,minPlayers);
		return new ResponseEntity<List<BoardGame>>(boardGames,HttpStatus.OK);
	}
}