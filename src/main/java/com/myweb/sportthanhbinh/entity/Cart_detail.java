package com.myweb.sportthanhbinh.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_detail")
@IdClass(Cart_detail.class)
public class Cart_detail implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cart")
    @Id
    private Cart idCart;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Product")
    @Id
    private Product idProduct;

    @Column(name = "quantity")
    private int quantity;

    public Cart getIdCart() {
        return idCart;
    }

    public void setIdCart(Cart idCart) {
        this.idCart = idCart;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
