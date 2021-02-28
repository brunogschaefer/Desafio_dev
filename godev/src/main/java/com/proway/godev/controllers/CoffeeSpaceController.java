package com.proway.godev.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proway.godev.dto.CoffeeSpaceDTO;
import com.proway.godev.exceptions.MaxLimitReachedException;
import com.proway.godev.services.CoffeeSpaceServices;

@RestController
@RequestMapping(value = "/coffeespace")
public class CoffeeSpaceController {
	
	@Autowired
	private CoffeeSpaceServices services;

	@GetMapping("/{name}")
	public ResponseEntity<CoffeeSpaceDTO> findByName (@PathVariable String name){
		CoffeeSpaceDTO coffeeSpace = services.findByName (name);
		return ResponseEntity.ok().body(coffeeSpace);
	}
	
	@GetMapping
	public ResponseEntity<List<CoffeeSpaceDTO>> findAll(){
		List<CoffeeSpaceDTO> list = services.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CoffeeSpaceDTO> insert (@RequestBody CoffeeSpaceDTO dto) throws MaxLimitReachedException {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
