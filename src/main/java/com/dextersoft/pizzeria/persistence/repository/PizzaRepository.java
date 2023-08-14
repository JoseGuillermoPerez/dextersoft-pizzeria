package com.dextersoft.pizzeria.persistence.repository;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

  List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();

  PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

  int countByVeganTrue();

}
