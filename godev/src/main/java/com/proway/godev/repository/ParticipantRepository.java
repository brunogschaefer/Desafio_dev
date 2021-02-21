package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {

}
