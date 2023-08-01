package br.com.aeon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.aeon.entity.Costumer;
import br.com.aeon.repository.CostumerRepository;
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
	
	void shouldfindAllCostumers() {	
		List<Costumer> costumerList = new ArrayList<Costumer>();
		costumerList.add(costumer);
		
		//Mockito.when(costumerRepository.findAll())
		//	.thenReturn(costumerList);
		
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

	void shouldAddCostumer() {	}
	
	void shouldUpdateCostumer() {	}
	
	void shouldDeleteCostumer() {	}
	
}
