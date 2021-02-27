package com.proway.godev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.utils.ParticipantsDistributionUtil;
import com.proway.godev.utils.StageChangeUtil;

@Service
public class ParticipantServices {
	
	@Autowired
	private ParticipantRepository pRepo;
	@Autowired
	private ParticipantsDistributionUtil distribution;
	@Autowired
	private StageChangeUtil setStage;
	@Autowired
	private EventRoomRepository erRepo;
	@Autowired
	private CoffeeSpaceRepository csRepo;
	
	
	@Transactional (readOnly = true)
	public ParticipantDTO findByName(String firstName, String lastName) {
		Participant participant = pRepo.findByFullNameWithRoomAndSpace(firstName, lastName);
		//ReturnAssignedIdsUtil partUtil = pRepo.findByAssignedIds(dto.getFirstName(), dto.getLastName());
		return new ParticipantDTO(participant);
	}
	
	@Transactional
	public List<ParticipantDTO> findAll() {
		List<Participant> list = pRepo.findAll();
		return list.stream().map(e -> new ParticipantDTO(e)).collect(Collectors.toList());
	}
	
	@Transactional
	public ParticipantDTO insert (ParticipantDTO dto) throws ParticipantAlreadyExistException, NullPointerException {
		existsByFullName(dto.getFirstName(), dto.getLastName());
		noEventRoomRegistered(erRepo.count(), csRepo.count());
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), StagesEnum.STAGE_A, null, null);	
		pRepo.save(participant);
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

	public List<ParticipantDTO> setStage(boolean set) {
		List<Participant> setStageList = setStage.setStage(set);
		return setStageList.stream().map(e -> new ParticipantDTO(e)).collect(Collectors.toList());
	}
}
