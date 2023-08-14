package com.dextersoft.pizzeria.persistence.repository;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
}
