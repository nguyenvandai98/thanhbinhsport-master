package com.myweb.sportthanhbinh.controller;

import com.myweb.sportthanhbinh.entity.Brand;
import com.myweb.sportthanhbinh.entity.Category;
import com.myweb.sportthanhbinh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("brand", new Brand());
        return "admin/brand/addOrEdit";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ModelMap model, Brand brand) {
        brandService.save(brand);
        model.addAttribute("brand",brand);
        return list(model);
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
        Optional<Brand> opt = brandService.findById(id);
        if(opt.isPresent()){
            model.addAttribute("brand",opt.get());
        }
        else{
            return list(model);
        }
        return "admin/brand/addOrEdit";
    }

    @GetMapping("/delete/{id}")
    public String delete(ModelMap model,@PathVariable(name = "id") Long id) {
        brandService.deleteById(id);
        return list(model);
    }

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<Brand> list = (List<Brand>) brandService.findAll();
        model.addAttribute("brand", list);
        return "admin/brand/list";
    }
}
