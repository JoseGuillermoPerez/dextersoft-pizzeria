package com.dextersoft.pizzeria.web.controller;

import com.dextersoft.pizzeria.persistence.entity.CustomerEntity;
import com.dextersoft.pizzeria.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/phone/{phone}")
  public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
    return ResponseEntity.ok(this.customerService.findByPhone(phone));
  }

  @PostMapping
  public ResponseEntity<CustomerEntity> add(@RequestBody CustomerEntity customer) {

    if (Objects.isNull(customer.getIdCustomer()) || !this.customerService.exists(
        customer.getIdCustomer())) {
      return ResponseEntity.ok(this.customerService.save(customer));
    }

    return ResponseEntity.badRequest().build();
  }

}
