package com.proway.godev.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;

@Component
public class StageChangeUtil {

	@Autowired
	private ParticipantRepository part;
	@Autowired
	private EventRoomRepository erRepo;
	
	
	public Boolean setStage (boolean set) {
		long erCount = erRepo.count();
		List<Participant> listAll = part.findAll();
		if (set == true) {			
			listAll.forEach(e -> e.setStage(StagesEnum.STAGE_B));
			listAll.stream().
					filter(e -> (e.getId()) % 2 == 1).
					forEach(ne -> {
						ne.setRoom(erRepo.getOne((ne.getId() + erCount) % erCount + 1));
						part.save(ne);
					});
			return true;
		}
		return false;
	}
}
