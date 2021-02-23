package com.proway.godev.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.services.EventRoomServices;

@RestController
@RequestMapping(value = "/eventroom")
public class EventRoomController {

	@Autowired
	private EventRoomServices services;
	
	@GetMapping
	public ResponseEntity<EventRoomDTO> findByName (@RequestBody EventRoomDTO dto){
		EventRoomDTO eventRoom = services.findByName (dto);
		return ResponseEntity.ok().body(eventRoom);
	}
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventRoomDTO> insert (@RequestBody EventRoomDTO dto) throws Exception {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	

}
