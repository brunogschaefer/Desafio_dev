package com.proway.godev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.repository.ParticipantRepository;

@Service
public class ParticipantServices {
	
	@Autowired
	private ParticipantRepository repo;
	
	@Transactional
	public ParticipantDTO insert (ParticipantDTO dto) {
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), dto.getStage());
		participant = repo.save(participant);
		return new ParticipantDTO(participant);
	}
}
