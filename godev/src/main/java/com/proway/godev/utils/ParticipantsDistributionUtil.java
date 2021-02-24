package com.proway.godev.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proway.godev.entities.CoffeeSpace;
import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;

@Component
public class ParticipantsDistributionUtil {
	
	@Autowired
	private EventRoomRepository erRepo;
	
	@Autowired
	private CoffeeSpaceRepository csRepo;
	
	private long currentRoom = 0L;
	private long currentSpace = 0L;
	
	public void distributeOverEventRoom (Participant participant) {
		long numberOfRooms = erRepo.count();
		currentRoom = (currentRoom + 1) % numberOfRooms;  
		EventRoom actual = erRepo.getOne(currentRoom + 1);
		if (actual.getParticipants().size() < actual.getCapacity()) {
			actual.addParticipants(participant);
			erRepo.save(actual);
		} 
	}
	
	public void distributeOverCoffeeSpace (Participant participant) {
		long numberOfSpaces = csRepo.count();
		currentSpace = (currentSpace + 1) % numberOfSpaces;  
		CoffeeSpace actual = csRepo.getOne(currentSpace + 1);
		actual.addParticipants(participant);
		csRepo.save(actual);
	}	
}
