package com.proway.godev.services;

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
	public EventRoomDTO insert(EventRoomDTO dto) {
		EventRoom eventRoom = new EventRoom (null, dto.getName(), dto.getCapacity());
		eventRoom = repo.save(eventRoom);
		return new EventRoomDTO(eventRoom);
	}
}