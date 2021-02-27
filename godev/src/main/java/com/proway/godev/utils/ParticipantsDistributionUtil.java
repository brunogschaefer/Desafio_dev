package com.proway.godev.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proway.godev.entities.CoffeeSpace;
import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;

@Component
public class ParticipantsDistributionUtil {
	
	@Autowired
	private EventRoomRepository erRepo;
	@Autowired
	private CoffeeSpaceRepository csRepo;
	@Autowired
	private ParticipantRepository part;
	
	private long currentRoom = 0L;
	private long currentSpace = 0L;
	
	public void distributeOverEventRoom (Participant participant) {
		long numberOfRooms = erRepo.count();
		currentRoom = (currentRoom + 1) % numberOfRooms;  
		EventRoom actual = erRepo.findById(currentRoom + 1).get();
		if (actual.getParticipants().size() < actual.getCapacity()) {
			actual.addParticipants(participant);
			participant.setRoom(actual);
			part.save(participant);
			erRepo.save(actual);			
		} 
	}
	
	public void distributeOverCoffeeSpace (Participant participant) {
		long numberOfSpaces = csRepo.count();
		currentSpace = (currentSpace + 1) % numberOfSpaces;  
		CoffeeSpace actual = csRepo.findById(currentSpace + 1).get();
		actual.addParticipants(participant);
		participant.setSpace(actual);
		part.save(participant);
		csRepo.save(actual);		
	}
}
