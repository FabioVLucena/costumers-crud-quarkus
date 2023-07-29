package br.com.aeon.repository;

import br.com.aeon.entity.Costumer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CostumerRepository implements PanacheRepository<Costumer>{

}
