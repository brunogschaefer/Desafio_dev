package com.proway.godev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.utils.ParticipantsDistributionUtil;

@Service
public class ParticipantServices {
	
	@Autowired
	private ParticipantRepository pRepo;
	
	@Autowired
	private ParticipantsDistributionUtil distribution;
	
	@Autowired
	private EventRoomRepository erRepo;
	
	@Autowired
	private CoffeeSpaceRepository csRepo;
	
	@Transactional
	public ParticipantDTO findByName(ParticipantDTO dto) {
		Participant participant = pRepo.findByFirstName(dto.getFirstName());
		return new ParticipantDTO(participant);
	}
	
	@Transactional
	public ParticipantDTO insert (ParticipantDTO dto) throws ParticipantAlreadyExistException {
		existsByFullName(dto.getFirstName(), dto.getLastName());
		noEventRoomRegistered(erRepo.count(), csRepo.count());
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), dto.getStage());	
		participant = pRepo.save(participant);
		distribution.distributeOverEventRoom(participant);
		distribution.distributeOverCoffeeSpace(participant);
		return new ParticipantDTO(participant);
	}
	
	public void existsByFullName(String firstName, String lastName) throws ParticipantAlreadyExistException {
		boolean itExists = pRepo.existsParticipantByFirstNameAndLastName(firstName, lastName);
		if (itExists == true) {
			throw new ParticipantAlreadyExistException(firstName, lastName);
		}
	}
	
	public void noEventRoomRegistered (Long amountOfRooms, Long amountOfSpaces) throws NullPointerException {
		if (amountOfRooms == null || amountOfSpaces == null) {
			throw new NullPointerException("Nenhuma sala de evento e/ou espaço de café encontrado. Por favor, registre uma nova sala.");
		}	
	}
}
