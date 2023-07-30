package br.com.aeon.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.aeon.entity.Costumer;
import br.com.aeon.service.CostumerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
		List<Costumer> costumerList = new ArrayList<Costumer>();
		try {
			costumerList = this.costumerService.findAllCostumers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return costumerList;
	}
	
	@POST
	@Transactional
	public Costumer addCostumer(Costumer costumer) {
		return this.costumerService.addCostumer(costumer);
	}
	
	@PUT
	@Transactional
	@Path("/{id}")
	public Costumer updateCostumer(Long id, Costumer costumer) {
		return this.costumerService.updateCostumer(id, costumer);
	}
	
	@DELETE
	@Transactional
	@Path("/{id}")
	public void deleteCostumer(Long id) {
		this.costumerService.deleteCostumer(id);
	}
	
}
