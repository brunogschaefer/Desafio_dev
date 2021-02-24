package com.proway.godev.services;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.EventRoom;
import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParticipantServicesTest {
	
	@Mock
	private ParticipantRepository partRepo;
	
	@Mock
	private EventRoom eventRoom;	
	
	@InjectMocks
	private ParticipantServices partService;
	
	@Mock
	private EventRoomRepository eventRoomRepo;
	
	@InjectMocks
	private EventRoomServices eventRoomService;
	
	/*@ParameterizedTest
	@CsvSource({"Amanda, Arrabal, STAGE_A",
				"Joana, Suzana, STAGE_B",})
	void shouldItVerifyIfParticipitansArePersistedWithoutEventRoom(String firstName, String lastName, StagesEnum stage) {
		//given
		ParticipantDTO expectedDTO = new ParticipantDTO(null, firstName, lastName, stage);
		//then
		assertThrows(NullPointerException.class, () -> partService.insert(expectedDTO));
	}*/
	
	@ParameterizedTest
	@MethodSource("provideDefaultParameters")
	void shouldItVerifyIfParticipitansArePersistedWithEventRoom(Long id, String eventName, int eventQuantity, String firstName, String lastName, StagesEnum stage) throws Exception {
		//given
		EventRoomDTO createRoom = new EventRoomDTO (id, eventName, eventQuantity);
		eventRoomService.insert(createRoom);
		ParticipantDTO expectedDTO = new ParticipantDTO(null, firstName, lastName, stage);
		partService.insert(expectedDTO);
		Participant expectedSaved = partRepo.findByFullName(expectedDTO.getFirstName(), expectedDTO.getLastName());
		//when
		//when(partRepo.findByFullName(expectedDTO.getFirstName(), expectedDTO.getLastName())).thenReturn(expectedSaved);
		//when(partRepo.save(expectedSaved)).thenReturn(expectedSaved);
		//then
		assertThat(expectedSaved).isNotNull();
	}
	
	private static Stream<Arguments> provideDefaultParameters () {
		return Stream.of(Arguments.of(1L, "SalaUm", 10, "Amanda", "Carlota", "STAGE_A"),
				Arguments.of(2L, "SalaDois", 10, "Sara", "Seda", "STAGE_A"),
				Arguments.of(null, null, "Suzana", "Sussa", "STAGE_A"),
				Arguments.of(null, null, "Adam", "Jalapao", "STAGE_B"),
				Arguments.of(null, null, "Johann", "Corolla", "STAGE_B"));
	}


}
