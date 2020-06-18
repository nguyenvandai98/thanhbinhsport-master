package com.myweb.sportthanhbinh.service;

import com.myweb.sportthanhbinh.entity.Customer;

public interface CustomerService {
    void save(Customer customer);

    void update(Customer customer);

    Customer findByEmail(String email);
}
