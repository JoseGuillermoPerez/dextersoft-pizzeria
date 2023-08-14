package com.dextersoft.pizzeria.web.controller;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import com.dextersoft.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

  private final PizzaService pizzaService;

  @Autowired
  public PizzaController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return ResponseEntity.ok(this.pizzaService.getAll());
  }

  @GetMapping("/{idPizza}")
  public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
    return ResponseEntity.ok(this.pizzaService.get(idPizza));
  }

  @PostMapping
  public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {

    if (Objects.isNull(pizza.getIdPizza()) || !this.pizzaService.exists(pizza.getIdPizza())) {
      return ResponseEntity.ok(this.pizzaService.save(pizza));
    }

    return ResponseEntity.badRequest().build();
  }

  @PutMapping
  public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {

    if (Objects.nonNull(pizza.getIdPizza()) && this.pizzaService.exists(pizza.getIdPizza())) {
      return ResponseEntity.ok(this.pizzaService.save(pizza));
    }

    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/{idPizza}")
  public ResponseEntity<Void> delete(@PathVariable int idPizza) {
    if (this.pizzaService.exists(idPizza)) {
      pizzaService.delete(idPizza);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.badRequest().build();
  }

}
