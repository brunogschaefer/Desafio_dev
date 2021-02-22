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

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.services.ParticipantServices;

@RestController
@RequestMapping(value = "/api/v1/participants")
public class ParticipantController {
	
	@Autowired
	private ParticipantServices services;
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParticipantDTO> insert (@RequestBody ParticipantDTO dto) throws ParticipantAlreadyExistException {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	

}
