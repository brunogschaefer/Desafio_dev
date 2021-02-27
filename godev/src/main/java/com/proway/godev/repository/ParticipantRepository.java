package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.Participant;
import com.proway.godev.utils.ReturnAssignedIdsUtil;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {
	
	@Query ("SELECT DISTINCT obj FROM Participant obj WHERE obj.firstName = ?1")
	Participant findByFirstName(String firstName);
	
	boolean existsParticipantByFirstName(String firstName);
	
	boolean existsParticipantByFirstNameAndLastName(String firstName, String lastName);
	
	@Query ("SELECT DISTINCT obj FROM Participant obj WHERE obj.firstName = ?1 AND obj.lastName = ?2")
	Participant findByFullName(String firstName, String lastName);
	
	@Query ("SELECT DISTINCT obj FROM Participant obj JOIN FETCH obj.room JOIN FETCH obj.space WHERE obj.firstName = ?1 AND obj.lastName = ?2 ")
	Participant findByFullNameWithRoomAndSpace(String firstName, String lastName);
	
	@Query (value = "select eventroom_id as eventRoomId, coffeespace_id as coffeeSpaceId from tb_participant part where part.first_name = :firstName and part.last_name = :lastName", nativeQuery = true)
	ReturnAssignedIdsUtil findByAssignedIds (String firstName, String lastName);
}
