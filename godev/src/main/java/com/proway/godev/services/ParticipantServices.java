package com.proway.godev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.entities.utils.ParticipantsDistributionUtil;
import com.proway.godev.exceptions.EventRoomDoesntExistException;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.ParticipantRepository;

@Service
public class ParticipantServices {
	
	@Autowired
	private ParticipantRepository repo;
	
	@Autowired
	private ParticipantsDistributionUtil distribution;
	
	@Transactional
	public ParticipantDTO insert (ParticipantDTO dto) throws Exception {
		existsByFullName(dto.getFirstName(), dto.getLastName());
		noEventRoomRegistered(repo.count());
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), dto.getStage());
		distribution.ParticipantsDistribution(participant);
		participant = repo.save(participant);
		return new ParticipantDTO(participant);
	}
	
	public void existsByFullName(String firstName, String lastName) throws ParticipantAlreadyExistException {
		boolean itExists = repo.existsParticipantByFirstNameAndLastName(firstName, lastName);
		if (itExists == true) {
			throw new ParticipantAlreadyExistException(firstName, lastName);
		}
	}
	
	public void noEventRoomRegistered (Long amountOfRooms) throws EventRoomDoesntExistException {
		if (amountOfRooms == null) {
			throw new EventRoomDoesntExistException("Nenhuma sala de evento encontrada. Por favor, registre uma nova sala.");
		}	
	}
}
