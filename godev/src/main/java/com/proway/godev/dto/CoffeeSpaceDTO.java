package com.proway.godev.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.proway.godev.entities.CoffeeSpace;

public class CoffeeSpaceDTO {
	
	private Long id;
	private String name;
	List<ParticipantDTO> participants = new ArrayList<>();
	
	public CoffeeSpaceDTO () {}
	public CoffeeSpaceDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public CoffeeSpaceDTO(CoffeeSpace entity) {
		id = entity.getId();
		name = entity.getName();
		participants = entity.getParticipants().stream().
				map(x -> new ParticipantDTO(x)).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ParticipantDTO> getParticipants() {
		return participants;
	}
}
