package com.proway.godev.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.repository.EventRoomRepository;

@Component
public class ParticipantsDistributionUtil {
	
	@Autowired
	private EventRoomRepository repo;
	
	private long currentRoom = 0L;
	
	public void ParticipantsDistribution (Participant participant) throws NullPointerException {
		long numberOfRooms = repo.count();
		currentRoom = (currentRoom + 1) % numberOfRooms;  
		EventRoom actual = repo.getOne(currentRoom + 1);
		actual.addParticipants(participant);
		repo.save(actual);
		//limites da lista n~ao est~ao funcionado
		//adicionar exceçao para quando o limite for atingido
	}
}
