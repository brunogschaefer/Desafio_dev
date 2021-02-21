package com.proway.godev.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.proway.godev.entities.enums.CoffeeSpaceEnum;

@Entity
public class CoffeeSpace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private CoffeeSpaceEnum space;
	
	public CoffeeSpace () {}
	public CoffeeSpace(Long id, String name, CoffeeSpaceEnum space) {
		this.id = id;
		this.name = name;
		this.space = space;
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
	public CoffeeSpaceEnum getSpace() {
		return space;
	}
	public void setSpace(CoffeeSpaceEnum space) {
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
		CoffeeSpace other = (CoffeeSpace) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
