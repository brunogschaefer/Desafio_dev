package com.proway.godev.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.services.ParticipantServices;

@RestController
@RequestMapping(value = "/participants",
				consumes = MediaType.APPLICATION_JSON_VALUE)
public class ParticipantController {
	
	@Autowired
	private ParticipantServices services;
	
	@GetMapping("/{firstName}+{lastName}")
	public ResponseEntity<ParticipantDTO> findByName (@PathVariable String firstName,
													  @PathVariable String lastName){
		ParticipantDTO participant = services.findByName (firstName, lastName);
		return ResponseEntity.ok().body(participant);
	}
	
	@GetMapping
	public ResponseEntity<List<ParticipantDTO>> findAll(){
		List<ParticipantDTO> list = services.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParticipantDTO> insert (@RequestBody ParticipantDTO dto) throws Exception {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/setstage/{set}")
	public ResponseEntity<List<ParticipantDTO>> setDelivered (@PathVariable boolean set){
		List<ParticipantDTO> dto = services.setStage(set);
		return ResponseEntity.ok().body(dto);
	}
}
