package com.proway.godev.entities.utils;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.exceptions.EventRoomDoesntExistException;
import com.proway.godev.repository.EventRoomRepository;

public class ParticipantsDistributionUtil {
	
	@Autowired
	private EventRoom eventRoom;
	
	@Autowired
	private EventRoomRepository repo;
	
	Queue<Participant> queueOfParticipants = new LinkedList<>();
	
	public void ParticipantsDistribution (Participant participant) throws EventRoomDoesntExistException {
		queueOfParticipants.add(participant);
		Integer numberOfRooms = (int) repo.count();
		while (!queueOfParticipants.isEmpty()) {
			for (int i=0; i<numberOfRooms; i++) {
				Long l = Long.valueOf(i);
				eventRoom = repo.getOne(l);
				eventRoom.addParticipants(queueOfParticipants.poll());
				repo.save(eventRoom);
			}
		}		
	}
}
