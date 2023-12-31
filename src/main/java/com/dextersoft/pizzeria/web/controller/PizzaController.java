package com.dextersoft.pizzeria.web.controller;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import com.dextersoft.pizzeria.service.PizzaService;
import com.dextersoft.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @GetMapping("/available")
  public ResponseEntity<List<PizzaEntity>> getAvailable() {
    return ResponseEntity.ok(this.pizzaService.getAvailable());
  }

  @GetMapping("/available/paginated")
  public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "8") int elements,
      @RequestParam(defaultValue = "price") String sortBy,
      @RequestParam(defaultValue = "ASC") String sortDirection) {

    return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
    return ResponseEntity.ok(this.pizzaService.getByName(name));
  }

  @GetMapping("/with/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
    return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
  }

  @GetMapping("/without/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
    return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
  }

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return ResponseEntity.ok(this.pizzaService.getAll());
  }

  @GetMapping("/paginated")
  public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "8") int elements) {
    return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
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

  @GetMapping("/cheapest/{price}")
  public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
    return ResponseEntity.ok(this.pizzaService.getCheapest(price));
  }

  @PutMapping("update/price")
  public ResponseEntity<PizzaEntity> updatePrice(
      @RequestBody UpdatePizzaPriceDto updatePizzaPriceDto) {

    if (this.pizzaService.exists(updatePizzaPriceDto.getPizzaId())) {
      this.pizzaService.updatePrice(updatePizzaPriceDto);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.badRequest().build();
  }

}
