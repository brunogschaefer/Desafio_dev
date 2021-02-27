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

import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.services.EventRoomServices;

@RestController
@RequestMapping(value = "/eventroom")
public class EventRoomController {

	@Autowired
	private EventRoomServices services;
	
	@GetMapping("/{name}")
	public ResponseEntity<EventRoomDTO> findByName (@PathVariable String name){
		EventRoomDTO eventRoom = services.findByName (name);
		return ResponseEntity.ok().body(eventRoom);
	}
	
	@GetMapping
	public ResponseEntity<List<EventRoomDTO>> findAll(){
		List<EventRoomDTO> list = services.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventRoomDTO> insert (@RequestBody EventRoomDTO dto) throws Exception {
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	

}
