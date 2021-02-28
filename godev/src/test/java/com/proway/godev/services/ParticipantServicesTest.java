package com.proway.godev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.exceptions.ParticipantAlreadyExistException;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;
import com.proway.godev.utils.ParticipantsDistributionUtil;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParticipantServicesTest {
	
	@Mock
	private ParticipantRepository partRepo;	
	@Mock
	private Participant part;
	@Mock
	private ParticipantsDistributionUtil distribution;
	@InjectMocks
	private ParticipantServices partService;	
	@Mock
	private EventRoomRepository erRepo;	
	@InjectMocks
	private EventRoomServices erService;
	@Mock
	private CoffeeSpaceRepository csRepo;	
	@InjectMocks
	private CoffeeSpaceServices csService;
	
	@ParameterizedTest
	@MethodSource ("provideDefaultParameters")
	public void itShouldReturnNewParticipant (String firstName, String lastName) throws NullPointerException, ParticipantAlreadyExistException {		
		ParticipantDTO expected = new ParticipantDTO(null, firstName, lastName, null, null, null);
		ParticipantDTO created = partService.insert(expected);
		
		//when(partRepo.save(created)).thenReturn(created);
		
		assertThat(expected.getFirstName()).isEqualTo(created.getFirstName());
		assertThat(expected.getLastName()).isEqualTo(created.getLastName());
		assertThat(expected.getSpace()).isNotEqualTo(created);
	}
	
	/*@ParameterizedTest
	@MethodSource ("provideDefaultParameters")
	public void itShouldVerifyIfParticipantAlreadyExistsAndThrowException (String firstName, String lastName) throws NullPointerException, ParticipantAlreadyExistException {
		ParticipantDTO expected = new ParticipantDTO(null, firstName, lastName, null, null, null);
		ParticipantDTO duplicated = partService.insert(expected);
		Participant getDupli = new Participant();
		
		when(partRepo.findByFirstName(expected.getFirstName())).thenReturn(partRepo.));
		
		assertThrows(ParticipantAlreadyExistException.class, () -> partService.insert(duplicated));
	}*/
	
	private static Stream<Arguments> provideDefaultParameters () {
		return Stream.of(Arguments.of("Suzana", "Cabrita"),
				Arguments.of("Carla", "Joana"));
	}

}
