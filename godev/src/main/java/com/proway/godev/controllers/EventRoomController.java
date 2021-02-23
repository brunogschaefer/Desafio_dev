package com.proway.godev.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.services.EventRoomServices;

public class EventRoomController {

	@Autowired
	private EventRoomServices services;
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventRoomDTO> insert (@RequestBody EventRoomDTO dto) throws Exception {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	

}
