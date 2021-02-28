package com.proway.godev.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.enums.StagesEnum;

@Entity
@Table (name = "tb_participant")
public class Participant {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private StagesEnum stage;
	
	
	@ManyToOne (fetch = FetchType.EAGER,
				cascade = CascadeType.PERSIST)
	@JoinColumn(name = "eventroom_id")
	@JsonBackReference (value = "event_room")
	private EventRoom room;
	
	@ManyToOne (fetch = FetchType.EAGER,
				cascade = CascadeType.PERSIST)
	@JoinColumn(name = "coffeespace_id")
	@JsonBackReference (value = "coffee_space")
	private CoffeeSpace space;
	
	public Participant () {
	}
	public Participant(Long id, String firstName, String lastName, StagesEnum stage, EventRoom room, CoffeeSpace space) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stage = stage;
		this.room = room;
		this.space = space;
	}
	public Participant(ParticipantDTO dto) {
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		stage = dto.getStage();
		room = dto.getRoom();
		space = dto.getSpace();
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
	public void setRoom(EventRoom room) {
		this.room = room;
	}
	public void setSpace(CoffeeSpace space) {
		this.space = space;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
