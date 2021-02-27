package com.proway.godev.services;

import java.util.List;
import java.util.stream.Collectors;

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
	public CoffeeSpaceDTO findByName(String name) {
		CoffeeSpace CoffeeSpace = repo.findByName(name);
		return new CoffeeSpaceDTO(CoffeeSpace);
	}
	
	@Transactional
	public List<CoffeeSpaceDTO> findAll() {
		List<CoffeeSpace> list = repo.findAll();
		return list.stream().map(e -> new CoffeeSpaceDTO(e)).collect(Collectors.toList());
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
