package com.myweb.sportthanhbinh.repository;

import com.myweb.sportthanhbinh.entity.Cart_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<Cart_detail, Integer> {
    @Query("select cd from Cart_detail cd, Cart c where c.customer.CustomerId = :idCustomer and c.idCart = cd.idCart.idCart")
    List<Cart_detail> findByCustomer(Long idCustomer);
}
