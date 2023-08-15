package com.dextersoft.pizzeria.persistence.repository;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

  List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();

  PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

  Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(double price);

  int countByVeganTrue();



}
