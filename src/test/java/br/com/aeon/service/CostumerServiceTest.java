package br.com.aeon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import br.com.aeon.dto.CostumerRequestDTO;
import br.com.aeon.entity.Costumer;
import br.com.aeon.repository.CostumerRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CostumerServiceTest {

	@InjectMock
	private CostumerRepository costumerRepository;

	@Inject
	private CostumerService costumerService;

	private Costumer costumer;

	@BeforeEach
	void setUp() {
		costumer = new Costumer();
		costumer.setId(1L);
		costumer.setName("first");
		costumer.setLastName("costumer");
		costumer.setAge(33);
		costumer.setEmail("costumer@test.com");
	}

	@Test
	void shouldfindAllCostumers() {	
		List<Costumer> costumerList = new ArrayList<Costumer>();
		costumerList.add(costumer);

		PanacheQuery<Costumer> panacheQueryMock = mock(PanacheQuery.class);
		when(panacheQueryMock.list()).thenReturn(costumerList);

		Mockito.when(costumerRepository.findAll())
		.thenReturn(panacheQueryMock);

		List<Costumer> costumerListTemp = costumerService.findAllCostumers();

		assertNotNull(costumerListTemp);
		assertFalse(costumerListTemp.isEmpty());
		assertEquals(1, costumerListTemp.size());

		Costumer costumerTemp = costumerListTemp.get(0);
		assertEquals("first", costumerTemp.getName());
		assertEquals("costumer", costumerTemp.getLastName());
		assertEquals(33, costumerTemp.getAge());
		assertEquals("costumer@test.com", costumerTemp.getEmail());
	}

	@Test
	void shouldAddCostumer() {	
		Mockito.doAnswer(invocation -> {
            Costumer costumer = invocation.getArgument(0);
            costumer.setId(1L);
            return costumer;
        })
		.when(this.costumerRepository)
		.persist(ArgumentMatchers.any(Costumer.class));

		Mockito.when(this.costumerRepository.isPersistent(ArgumentMatchers.any(Costumer.class)))
		.thenReturn(true);

		CostumerRequestDTO costumerDTO = new CostumerRequestDTO();
		costumerDTO.setName("second");
		costumerDTO.setLastName("costumer");
		costumerDTO.setAge(45);
		costumerDTO.setEmail("second-costumer@teste.com");

		Costumer costumerTemp = this.costumerService.addCostumer(costumerDTO);

		assertNotNull(costumerTemp);
		assertNotNull(costumerTemp.getId());
		assertEquals(costumerTemp.getName(), costumerDTO.getName());
		assertEquals(costumerTemp.getLastName(), costumerDTO.getLastName());
		assertEquals(costumerTemp.getAge(), costumerDTO.getAge());
		assertEquals(costumerTemp.getEmail(), costumerDTO.getEmail());
	}

	@Test
	void shouldUpdateCostumer() throws Exception {	
		Mockito.when(costumerRepository.findByIdOptional(1L))
		.thenReturn(Optional.of(costumer));

		Mockito.doNothing()
		.when(this.costumerRepository)
		.persist(ArgumentMatchers.any(Costumer.class));

		Mockito.when(this.costumerRepository.isPersistent(ArgumentMatchers.any(Costumer.class)))
		.thenReturn(true);

		CostumerRequestDTO costumerDTO = new CostumerRequestDTO();
		costumerDTO.setName("second");
		costumerDTO.setLastName("costumer");
		costumerDTO.setAge(45);
		costumerDTO.setEmail("second-costumer@teste.com");

		Costumer costumerTemp = this.costumerService.updateCostumer(1L, costumerDTO);

		assertNotNull(costumerTemp);
		assertEquals(costumerTemp.getName(), costumerDTO.getName());
		assertEquals(costumerTemp.getLastName(), costumerDTO.getLastName());
		assertEquals(costumerTemp.getAge(), costumerDTO.getAge());
		assertEquals(costumerTemp.getEmail(), costumerDTO.getEmail());
	}

	@Test
	void shouldDeleteCostumer() {
		Mockito.doAnswer(invocation -> {
            Costumer costumer = invocation.getArgument(0);
            costumer.setId(1L);
            return costumer;
        })
		.when(this.costumerRepository)
		.persist(ArgumentMatchers.any(Costumer.class));

		Mockito.when(this.costumerRepository.isPersistent(ArgumentMatchers.any(Costumer.class)))
		.thenReturn(true);
		
		CostumerRequestDTO costumerDTO = new CostumerRequestDTO();
		costumerDTO.setName("second");
		costumerDTO.setLastName("costumer");
		costumerDTO.setAge(45);
		costumerDTO.setEmail("second-costumer@teste.com");

		Costumer costumerTemp = this.costumerService.addCostumer(costumerDTO);

		Long costumerId = costumerTemp.getId();

		this.costumerService.deleteCostumer(costumerId);

		verify(this.costumerRepository).deleteById(costumerId);
	}

}
