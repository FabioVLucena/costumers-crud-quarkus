package br.com.aeon.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aeon.dto.CostumerRequestDTO;
import br.com.aeon.entity.Costumer;
import br.com.aeon.exception.NotFoundException;
import br.com.aeon.repository.CostumerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CostumerService {

	@Inject
	private ObjectMapper objectMapper;

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
		Costumer costumer = objectMapper.convertValue(costumerDTO, Costumer.class);
		
		this.costumerRepository.persist(costumer);
		return costumer;
	}

	public Costumer updateCostumer(Long id, CostumerRequestDTO costumerDTO) throws Exception {
		// POR ALGUM MOTIVO O PANACHE TE OBRIGA
		// A FAZER UM SELECT DO REGISTRO Q VC VAI ATUALIZAR
		// E DEPOIS VC TEM Q USAR A MSM INSTANCIA PRA FAZER O UPDATE
		// E ISSO BOICOTA O MAPPER PQ ELE CRIA OUTRA INSTANCIA
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
