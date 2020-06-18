package com.myweb.sportthanhbinh.repository;

import com.myweb.sportthanhbinh.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
   @Query("select c from Customer c where c.Email like :email")
    Customer findByEmail(String email);
}
