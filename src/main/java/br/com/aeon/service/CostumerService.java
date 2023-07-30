package br.com.aeon.service;

import java.util.List;

import br.com.aeon.entity.Costumer;
import br.com.aeon.repository.CostumerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CostumerService {

	@Inject
	private CostumerRepository costumerRepository;
	
	public List<Costumer> findAllCostumers() {
		return this.costumerRepository.findAll().list();
	}
	
	public Costumer addCostumer(Costumer costumer) {
		this.costumerRepository.persist(costumer);
		return costumer;
	}

	public Costumer updateCostumer(Long id, Costumer costumer) {
		Costumer costumerTemp = this.costumerRepository.findById(id);
		costumerTemp.setName(costumer.getName());
		costumerTemp.setLastName(costumer.getLastName());
		costumerTemp.setAge(costumer.getAge());
		costumerTemp.setEmail(costumer.getEmail());
		
		this.costumerRepository.persist(costumerTemp);
		return costumer;
	}
	
	public void deleteCostumer(Long id) {
		this.costumerRepository.deleteById(id);
	}
	
}
