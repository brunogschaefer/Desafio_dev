package com.proway.godev.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proway.godev.dto.CoffeeSpaceDTO;
import com.proway.godev.services.CoffeeSpaceServices;

@RestController
@RequestMapping(value = "/coffeespace")
public class CoffeeSpaceController {
	
	@Autowired
	private CoffeeSpaceServices services;

	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CoffeeSpaceDTO> insert (@RequestBody CoffeeSpaceDTO dto) throws Exception {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
