package com.myweb.sportthanhbinh.service.impl;

import com.myweb.sportthanhbinh.entity.Cart;
import com.myweb.sportthanhbinh.repository.CartRepository;
import com.myweb.sportthanhbinh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
@Autowired
private CartRepository cartRepository;
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
