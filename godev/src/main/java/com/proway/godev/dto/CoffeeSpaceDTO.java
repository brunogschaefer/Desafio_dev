package com.proway.godev.dto;

import com.proway.godev.entities.CoffeeSpace;
import com.proway.godev.enums.CoffeeSpaceEnum;

public class CoffeeSpaceDTO {
	
	private Long id;
	private String name;
	private CoffeeSpaceEnum space;
	
	public CoffeeSpaceDTO () {}
	public CoffeeSpaceDTO(Long id, String name, CoffeeSpaceEnum space) {
		this.id = id;
		this.name = name;
		this.space = space;
	}
	public CoffeeSpaceDTO(CoffeeSpace entity) {
		id = entity.getId();
		name = entity.getName();
		space = entity.getSpace();
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
}
