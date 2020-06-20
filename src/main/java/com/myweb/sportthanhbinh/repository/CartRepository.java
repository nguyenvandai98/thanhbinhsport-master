package com.myweb.sportthanhbinh.repository;

import com.myweb.sportthanhbinh.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    @Query("select c from Cart c where c.customer.CustomerId = :idCustomer")
    Cart findByCustomer(Long idCustomer);
}
