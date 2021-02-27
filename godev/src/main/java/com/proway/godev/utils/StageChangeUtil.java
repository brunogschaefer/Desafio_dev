package com.proway.godev.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;

@Component
public class StageChangeUtil {

	@Autowired
	private ParticipantRepository part;
	@Autowired
	private EventRoomRepository erRepo;
	@Autowired
	private CoffeeSpaceRepository csRepo;
	
	
	public List<Participant> setStage (boolean set) {
		List<Participant> listAll = part.findAll();
		if (set == true) {			
			listAll.forEach(e -> e.setStage(StagesEnum.STAGE_B));
			listAll.stream().
					filter(e -> e.getId() % 2 == 1).
					forEach(e -> {
						e.setRoom(erRepo.findById((e.getId() + 1) % erRepo.count()).get());
						e.setSpace(csRepo.findById((e.getId() + 1) % csRepo.count()).get());
						part.save(e);
					});
		}
		return listAll;
	}
}
