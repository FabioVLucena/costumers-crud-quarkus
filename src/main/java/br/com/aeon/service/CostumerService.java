package br.com.aeon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.aeon.dto.CostumerRequestDTO;
import br.com.aeon.entity.Costumer;
import br.com.aeon.exception.NotFoundException;
import br.com.aeon.repository.CostumerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CostumerService {

	@Inject
	private CostumerRepository costumerRepository;
	
	public List<Costumer> findAllCostumers() {
		List<Costumer> costumerList = new ArrayList<Costumer>();
		try {
			costumerList = this.costumerRepository.findAll().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costumerList; 
	}
	
	public Costumer addCostumer(CostumerRequestDTO costumerDTO) {
		Costumer costumer = new Costumer();
		costumer.setName(costumerDTO.getName());
		costumer.setLastName(costumerDTO.getLastName());
		costumer.setAge(costumerDTO.getAge());
		costumer.setEmail(costumerDTO.getEmail());
		
		this.costumerRepository.persist(costumer);
		return costumer;
	}

	public Costumer updateCostumer(Long id, CostumerRequestDTO costumerDTO) throws Exception {
		Costumer costumer = this.costumerRepository.findByIdOptional(id)
				.orElseThrow(() -> new NotFoundException("Costumer not found"));
		
		costumer.setName(costumerDTO.getName());
		costumer.setLastName(costumerDTO.getLastName());
		costumer.setAge(costumerDTO.getAge());
		costumer.setEmail(costumerDTO.getEmail());
		
		this.costumerRepository.persist(costumer);
		return costumer;
	}
	
	public void deleteCostumer(Long id) {
		this.costumerRepository.deleteById(id);
	}
	
}
