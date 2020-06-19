package com.myweb.sportthanhbinh.controller;

import com.myweb.sportthanhbinh.entity.Cart_detail;
import com.myweb.sportthanhbinh.entity.Category;
import com.myweb.sportthanhbinh.entity.Customer;
import com.myweb.sportthanhbinh.entity.Product;
import com.myweb.sportthanhbinh.repository.CartDetailRepository;
import com.myweb.sportthanhbinh.repository.CartRepository;
import com.myweb.sportthanhbinh.repository.ProductReponsitory;
import com.myweb.sportthanhbinh.service.CategoryService;
import com.myweb.sportthanhbinh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(value = "/admin/home")
    public String index() {
        return "admin/home";
    }

    @GetMapping(value = {"/home-page", "/"})
    public String index(ModelMap model) {
        List<Category> listcate = (List<Category>) categoryService.findAll();
        model.addAttribute("category", listcate);
        List<Product> list = (List<Product>) productService.findAll();
        model.addAttribute("product", list);
        return "web/index";
    }

    @GetMapping(value = "/productDetail")
    public String productDetail() {
        return "web/product/productDetail";
    }


    @GetMapping("/home-page/product/{id}")
    public String productDetail(ModelMap model, @PathVariable(name = "id") Long id) {
        Optional<Product> opt = productService.findById(id);

        if (opt.isPresent()) {
            model.addAttribute("product", opt.get());
        }
        List<Category> listcategory = (List<Category>) categoryService.findAll();
        model.addAttribute("category", listcategory);
        return "web/product/productDetail";

    }

    @RequestMapping("/cartView")
    public String cartView() {
        return "web/cart";
    }

    @RequestMapping("/addToCart/{idProduct}")
    public String addToCart(@PathVariable(name = "idProduct") Long idProduct, HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "web/login";
        }
        model.addAttribute("customer", customer);
        List<Cart_detail> cart_details = cartDetailRepository.findByCustomer(customer.getCustomerId());
        for (Cart_detail cart_detail : cart_details) {
            if (cart_detail.getIdProduct().getId() == idProduct) {
                cart_detail.setQuantity(cart_detail.getQuantity() + 1);
                cartDetailRepository.save(cart_detail);
                model.addAttribute("cart_details", cartDetailRepository.findByCustomer(customer.getCustomerId()));
                return "web/cart";
            }
        }
        Cart_detail cart_detail = new Cart_detail();
        cart_detail.setQuantity(1);
        cart_detail.setIdCart(cartRepository.findByCustomer(customer.getCustomerId()));
        cart_detail.setIdProduct(productReponsitory.findById(idProduct).get());
        cartDetailRepository.save(cart_detail);
        model.addAttribute("cart_details", cartDetailRepository.findByCustomer(customer.getCustomerId()));

        return "web/cart";
    }
}
