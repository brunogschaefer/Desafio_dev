package com.proway.godev.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.proway.godev.entities.EventRoom;

public class EventRoomDTO {
	
	private Long id;
	private String name;
	private Integer capacity;
	List<ParticipantDTO> participants = new ArrayList<>();
	
	public EventRoomDTO() {}
	public EventRoomDTO(Long id, String name, Integer capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}
	public EventRoomDTO(EventRoom entity) {
		id = entity.getId();
		name = entity.getName();
		capacity = entity.getCapacity();
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
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public List<ParticipantDTO> getParticipants() {
		return participants;
	}
}
