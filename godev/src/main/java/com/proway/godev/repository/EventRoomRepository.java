package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.EventRoom;

@Repository
public interface EventRoomRepository extends JpaRepository <EventRoom, Long> {

	@Query ("SELECT DISTINCT obj FROM EventRoom obj WHERE obj.name = ?1")
	EventRoom findByName(String name);
	
	@Query ("SELECT DISTINCT obj FROM EventRoom obj WHERE obj.id = ?1")
	EventRoom findByLongId(Long id);
	
}
