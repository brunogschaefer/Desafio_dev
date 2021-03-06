package com.proway.godev.entities;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "tb_eventroom")
public class EventRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column (unique = true)
	private String name;
	@NotNull
	private Integer capacity;
	
	@OneToMany (cascade = CascadeType.PERSIST,
				fetch = FetchType.EAGER,
				mappedBy = "room",
				orphanRemoval = true) 
	@JsonManagedReference (value = "event_room")
	Set<Participant> participants = new LinkedHashSet<>();
	
	public EventRoom() {}
	public EventRoom(Long id, String name, Integer capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
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
	public Set<Participant> getParticipants() {
		return participants;
	}
	public void addParticipants(Participant p) {
		participants.add(p);
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
		EventRoom other = (EventRoom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
