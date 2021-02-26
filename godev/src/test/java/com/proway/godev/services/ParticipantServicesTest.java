package com.proway.godev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.proway.godev.dto.CoffeeSpaceDTO;
import com.proway.godev.dto.EventRoomDTO;
import com.proway.godev.dto.ParticipantDTO;
import com.proway.godev.entities.Participant;
import com.proway.godev.enums.StagesEnum;
import com.proway.godev.exceptions.MaxLimitReachedException;
import com.proway.godev.repository.CoffeeSpaceRepository;
import com.proway.godev.repository.EventRoomRepository;
import com.proway.godev.repository.ParticipantRepository;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParticipantServicesTest {
	
	@Mock
	private ParticipantRepository partRepo;	
	@Mock
	private Participant part;
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
	
	/*@ParameterizedTest
	@MethodSource ("provideDefaultParameters")
    void whenValidParticipantNameIsGivenThenReturnAParticipantWithRoomAndSpace(Long id, String firstName, String lastName, StagesEnum stage) throws MaxLimitReachedException {
        // given
        ParticipantDTO expectedParticipantDTO = new ParticipantDTO(id, firstName, lastName, StagesEnum.STAGE_A);
        Participant expectedFoundParticipant = new Participant(id, firstName, lastName, StagesEnum.STAGE_A);
        EventRoomDTO room = new EventRoomDTO(1L, "salaUm", 2);
        CoffeeSpaceDTO space = new CoffeeSpaceDTO(1L, "espaçoUm");

        // when
        when(partService.findByName(expectedParticipantDTO)).thenReturn(expectedParticipantDTO);
        when(erService.insert(room)).thenReturn(room);
        when(csService.insert(space)).thenReturn(space);

        // then
        ParticipantDTO foundParticipantDTO = partService.findByName(expectedParticipantDTO);
        assertThat(foundParticipantDTO).isEqualTo(expectedFoundParticipant);
    }
	
	private static Stream<Arguments> provideDefaultParameters () {
		return Stream.of(Arguments.of(1L, "NomeUm", "SobreNomeUm", "STAGE_A"),
				Arguments.of(2L, "NomeDois", "SobreNomeDois", "STAGE_A"));
	}*/
}
