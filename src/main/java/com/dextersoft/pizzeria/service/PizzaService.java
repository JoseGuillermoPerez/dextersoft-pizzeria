package com.dextersoft.pizzeria.service;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import com.dextersoft.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

  private final PizzaRepository pizzaRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public List<PizzaEntity> getAvailable() {
    System.out.println(this.pizzaRepository.countByVeganTrue());
    return this.pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
  }

  public PizzaEntity getByName(String name) {
    return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
  }

  public List<PizzaEntity> getWith(String ingredient) {
    return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(
        ingredient);
  }

  public List<PizzaEntity> getWithout(String ingredient) {
    return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(
        ingredient);
  }

  public List<PizzaEntity> getAll() {
    return this.pizzaRepository.findAll();
  }

  public PizzaEntity get(int idPizza) {
    return this.pizzaRepository.findById(idPizza).orElse(null);
  }

  public PizzaEntity save(PizzaEntity pizza) {
    return this.pizzaRepository.save(pizza);
  }

  public void delete(int idPizza) {
    this.pizzaRepository.deleteById(idPizza);
  }

  public boolean exists(int idPizza) {
    return this.pizzaRepository.existsById(idPizza);
  }

  public PizzaEntity getNameFirst(String name) {
    return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
        .orElseThrow(() -> new RuntimeException("La pizza no existe"));
  }

  public List<PizzaEntity> getCheapest(double price) {
    return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(price);
  }

}
