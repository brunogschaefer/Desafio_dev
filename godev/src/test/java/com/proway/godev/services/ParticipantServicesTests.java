package com.proway.godev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.entities.enums.StagesEnum;
import com.proway.godev.repository.ParticipantRepository;

@ExtendWith(MockitoExtension.class)
public class ParticipantServicesTests {
	
	@Autowired
	@InjectMocks
	private ParticipantServices participantServices;
	
	@Autowired
	@Mock
	private ParticipantRepository participantRepository;
	
	@ParameterizedTest
	@CsvSource(value = {"David, Bowie, STAGE_A"})
	void shouldItBeCreatedWithParametersInformedAndPersisted(String firstName, String lastName, StagesEnum stage) {
		//given
		ParticipantDTO dto = new ParticipantDTO(null, firstName, lastName, stage);
		Participant participant = new Participant(null, dto.getFirstName(), dto.getLastName(), dto.getStage());
		
		//when
		when(participantRepository.save(participant)).thenReturn(participant);
		
		//then
		ParticipantDTO created = participantServices.insert(dto);
		assertThat(created.getFirstName()).isNotNull();

	}
	
	
	
	

}
