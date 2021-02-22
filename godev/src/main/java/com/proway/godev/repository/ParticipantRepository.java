package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {
	
	@Query ("SELECT DISTINCT obj FROM Participant obj WHERE obj.firstName = ?1")
	Participant findByFirstName(String firstName);
	
	boolean existsParticipantByFirstName(String firstName);
	
	boolean existsParticipantByFirstNameAndLastName(String firstName, String lastName);

}
