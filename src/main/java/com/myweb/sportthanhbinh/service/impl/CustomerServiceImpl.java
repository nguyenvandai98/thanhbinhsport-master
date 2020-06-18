package com.myweb.sportthanhbinh.service.impl;

import com.myweb.sportthanhbinh.entity.Customer;
import com.myweb.sportthanhbinh.repository.CustomerRepository;
import com.myweb.sportthanhbinh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
     customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Customer findByEmail(String email) {
               return customerRepository.findByEmail(email);
    }
}
