package com.myweb.sportthanhbinh.repository;

import com.myweb.sportthanhbinh.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.customer.CustomerId = :idCustomer")
    Cart findByCustomer(Long idCustomer);
}
