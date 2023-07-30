package br.com.aeon.controller;

import java.util.List;

import br.com.aeon.dto.CostumerRequestDTO;
import br.com.aeon.entity.Costumer;
import br.com.aeon.service.CostumerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Path("/api/costumers")
public class CostumerController {

	@Inject
	CostumerService costumerService;
	
	@GET
	public List<Costumer> retrieveCostumers() {
		return this.costumerService.findAllCostumers();
	}
	
	@POST
	@Transactional
	public Costumer addCostumer(@Valid CostumerRequestDTO costumerDTO) {
		return this.costumerService.addCostumer(costumerDTO);
	}
	
	@PUT
	@Transactional
	@Path("/{id}")
	public Costumer updateCostumer(Long id, @Valid CostumerRequestDTO costumerDTO) throws Exception {
		return this.costumerService.updateCostumer(id, costumerDTO);
	}
	
	@DELETE
	@Transactional
	@Path("/{id}")
	public void deleteCostumer(Long id) {
		this.costumerService.deleteCostumer(id);
	}
	
}
