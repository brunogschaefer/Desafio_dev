package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.CoffeeSpace;

@Repository
public interface CoffeeSpaceRepository extends JpaRepository <CoffeeSpace, Long>{

	@Query ("SELECT DISTINCT obj FROM CoffeeSpace obj WHERE obj.name = ?1")
	CoffeeSpace findByName(String name);

	@Query ("SELECT DISTINCT obj FROM CoffeeSpace obj WHERE obj.id = ?1")
	CoffeeSpace findByLongId(Long id);
}
