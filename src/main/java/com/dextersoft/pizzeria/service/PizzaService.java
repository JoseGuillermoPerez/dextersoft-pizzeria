package com.dextersoft.pizzeria.service;

import com.dextersoft.pizzeria.persistence.entity.PizzaEntity;
import com.dextersoft.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.dextersoft.pizzeria.persistence.repository.PizzaRepository;
import com.dextersoft.pizzeria.service.dto.UpdatePizzaPriceDto;
import com.dextersoft.pizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

  private final PizzaRepository pizzaRepository;
  private final PizzaPagSortRepository pizzaPagSortRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository,
      PizzaPagSortRepository pizzaPagSortRepository) {
    this.pizzaRepository = pizzaRepository;
    this.pizzaPagSortRepository = pizzaPagSortRepository;
  }

  public List<PizzaEntity> getAvailable() {
    System.out.println(this.pizzaRepository.countByVeganTrue());
    return this.pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
  }

  public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy,
      String sortDirection) {

    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    //    Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy));
    Pageable pageRequest = PageRequest.of(page, elements, sort);

    return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
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

  public Page<PizzaEntity> getAll(int page, int elements) {
    Pageable pageRequest = PageRequest.of(page, elements);
    return this.pizzaPagSortRepository.findAll(pageRequest);
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

  @Transactional(noRollbackFor = EmailApiException.class, propagation = Propagation.REQUIRED)
  public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
    this.pizzaRepository.updatePrice(updatePizzaPriceDto);
    this.sendEmail();
  }

  private void sendEmail() {
    throw new EmailApiException();
  }

}
