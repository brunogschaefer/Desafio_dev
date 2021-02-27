package com.proway.godev.dto;

import com.proway.godev.entities.CoffeeSpace;
import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;

public class ParticipantDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private StagesEnum stage;
	
	private EventRoom room;
	private CoffeeSpace space;
	
	public ParticipantDTO() {}
	public ParticipantDTO(Long id, String firstName, String lastName, StagesEnum stage, EventRoom room, CoffeeSpace space) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stage = stage;
		this.room = room;
		this.space = space;
	}
	public ParticipantDTO(Participant entity) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		stage = entity.getStage();
		room = entity.getRoom();
		space = entity.getSpace();
	}
	public ParticipantDTO(Participant entity, EventRoom room, CoffeeSpace space) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		stage = entity.getStage();
		this.room = room;
		this.space = space;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public StagesEnum getStage() {
		return stage;
	}
	public void setStage(StagesEnum stage) {
		this.stage = stage;
	}
	public EventRoom getRoom() {
		return room;
	}
	public CoffeeSpace getSpace() {
		return space;
	}
	
}
