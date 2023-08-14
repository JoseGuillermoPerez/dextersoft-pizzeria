package com.dextersoft.pizzeria.persistence.repository;

import com.dextersoft.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

  List<OrderEntity> findALlByDateAfter(LocalDateTime date);

  List<OrderEntity> findAllByMethodIn(List<String> methods);

}
