package com.proway.godev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proway.godev.dto.CoffeeSpaceDTO;
import com.proway.godev.entities.CoffeeSpace;
import com.proway.godev.exceptions.MaxLimitReachedException;
import com.proway.godev.repository.CoffeeSpaceRepository;

@Service
public class CoffeeSpaceServices {
	
	@Autowired
	private CoffeeSpaceRepository repo;
	
	@Transactional
	public CoffeeSpaceDTO findByName(CoffeeSpaceDTO dto) {
		CoffeeSpace CoffeeSpace = repo.findByName(dto.getName());
		return new CoffeeSpaceDTO(CoffeeSpace);
	}
	
	@Transactional
	public CoffeeSpaceDTO insert (CoffeeSpaceDTO dto) throws MaxLimitReachedException{
		maxLimitOfSpacesRegistered(repo.count());
		CoffeeSpace coffeeSpace = new CoffeeSpace(null, dto.getName());
		coffeeSpace = repo.save(coffeeSpace);
		return new CoffeeSpaceDTO(coffeeSpace);
	}
	
	public void maxLimitOfSpacesRegistered (Long amountOfSpaces) throws MaxLimitReachedException {
		if (amountOfSpaces >= 2) {
			throw new MaxLimitReachedException("Limite máximo de espaços criados.");
		}	
	}
}
