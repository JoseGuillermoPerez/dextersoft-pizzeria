package com.dextersoft.pizzeria.service;

import com.dextersoft.pizzeria.persistence.entity.CustomerEntity;
import com.dextersoft.pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerEntity findByPhone(String phone) {
    return this.customerRepository.findByPhone(phone);
  }

  public CustomerEntity save(CustomerEntity customer) {
    return this.customerRepository.save(customer);
  }

  public boolean exists(String idCustomer) {
    return this.customerRepository.existsById(idCustomer);
  }

}
