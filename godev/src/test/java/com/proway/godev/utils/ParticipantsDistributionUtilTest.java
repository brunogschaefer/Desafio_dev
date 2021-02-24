/*package com.proway.godev.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.services.ParticipantServices;

@ExtendWith(MockitoExtension.class)
public class ParticipantsDistributionUtilTest {
	
	@Mock
	private ParticipantRepository partRepo;
	
	@InjectMocks
	private ParticipantServices partService;
	
	@InjectMocks
	private ParticipantsDistributionUtil distribution;
	
	
	
	@ParameterizedTest
	@CsvSource({"Amanda, Arrabal, STAGE_A", 
				"Bruno, Bonano, STAGE_A", 
				"Caio, Costela, STAGE_A",
				"Daniel, Danette, STAGE_B", 
				"Eduarda, Escadinávia, STAGE_B", 
				"Franciele, Francisca, STAGE_B"})
	void shouldItVerifyIfParticipitansAreDistributed (String firstName, String lastName, StagesEnum stage) {
		//given
		ParticipantDTO expectedDTO = new ParticipantDTO(null, firstName, lastName, stage);
		//Participant expectedSaved = partRepo.findByFullName(expectedDTO.getFirstName(), expectedDTO.getLastName());
		
		//then
		assertThat(distribution(new Participant(expectedDTO))).;
	}
	


}
*/