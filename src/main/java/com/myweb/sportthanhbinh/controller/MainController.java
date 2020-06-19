package com.myweb.sportthanhbinh.controller;

import com.myweb.sportthanhbinh.entity.Category;
import com.myweb.sportthanhbinh.entity.Product;
import com.myweb.sportthanhbinh.repository.ProductReponsitory;
import com.myweb.sportthanhbinh.service.CategoryService;
import com.myweb.sportthanhbinh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
