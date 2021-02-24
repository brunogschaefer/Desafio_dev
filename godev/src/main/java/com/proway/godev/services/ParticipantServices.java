package com.proway.godev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.utils.ParticipantsDistributionUtil;

@Service
public class ParticipantServices {
	
	@Autowired
	private ParticipantRepository participantRepo;
	
	@Autowired
	private ParticipantsDistributionUtil distribution;
	
	@Autowired
	private EventRoomRepository eventRoomRepo;
	
	@Transactional
	public ParticipantDTO findByName(ParticipantDTO dto) {
		Participant participant = participantRepo.findByFirstName(dto.getFirstName());
		return new ParticipantDTO(participant);
	}
	
	@Transactional
	public ParticipantDTO insert (ParticipantDTO dto) throws Exception {
		existsByFullName(dto.getFirstName(), dto.getLastName());
		try {
			noEventRoomRegistered(eventRoomRepo.count());
		} catch (NullPointerException e) {
			e.getMessage();
		}
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), dto.getStage());	
		participant = participantRepo.save(participant);
		distribution.ParticipantsDistribution(participant);
		return new ParticipantDTO(participant);
	}
	
	public void existsByFullName(String firstName, String lastName) throws ParticipantAlreadyExistException {
		boolean itExists = participantRepo.existsParticipantByFirstNameAndLastName(firstName, lastName);
		if (itExists == true) {
			throw new ParticipantAlreadyExistException(firstName, lastName);
		}
	}
	
	public void noEventRoomRegistered (Long amountOfRooms) throws NullPointerException {
		if (amountOfRooms == null) {
			throw new NullPointerException("Nenhuma sala de evento encontrada. Por favor, registre uma nova sala.");
		}	
	}
}
