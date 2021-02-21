package com.proway.godev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proway.godev.entities.EventRoom;

@Repository
public interface EventRoomRepository extends JpaRepository <EventRoom, Long> {

}
