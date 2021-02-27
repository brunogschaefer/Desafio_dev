package com.proway.godev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.entities.EventRoom;
import com.proway.godev.repository.EventRoomRepository;

@Service
public class EventRoomServices {
	
	@Autowired
	private EventRoomRepository repo;
	
	@Transactional
	public EventRoomDTO findByName(String name) {
		EventRoom eventRoom = repo.findByName(name);
		return new EventRoomDTO(eventRoom);
	}
	
	@Transactional
	public List<EventRoomDTO> findAll() {
		List<EventRoom> list = repo.findAll();
		return list.stream().map(e -> new EventRoomDTO(e)).collect(Collectors.toList());
	}
	
	@Transactional
	public EventRoomDTO insert(EventRoomDTO dto) {
		EventRoom eventRoom = new EventRoom (null, dto.getName(), dto.getCapacity());
		eventRoom = repo.save(eventRoom);
		return new EventRoomDTO(eventRoom);
	}
}