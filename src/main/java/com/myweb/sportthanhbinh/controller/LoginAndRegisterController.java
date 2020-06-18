package com.myweb.sportthanhbinh.controller;

import com.myweb.sportthanhbinh.entity.Customer;
import com.myweb.sportthanhbinh.service.AdminService;
import com.myweb.sportthanhbinh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class LoginAndRegisterController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/login")
    public String login(@ModelAttribute("alert")String alert, @ModelAttribute("message")String message, Model model) {
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return "web/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "web/register";
    }


    @PostMapping(value = "register")
    public String register(Model model, Customer customer, RedirectAttributes ra) {
        try {
            if (customerService.findByEmail(customer.getEmail()) == null) {
                customerService.save(customer);
                ra.addFlashAttribute("alert", "alert alert-success");
                ra.addFlashAttribute("message", "Account registration successful");
                return "redirect:/login";
            } else {
                model.addAttribute("alert", "alert alert-danger");
                model.addAttribute("message", "Your email has been used");
                model.addAttribute("customer", customer);
                return "web/register";
            }

        } catch (Exception e) {
            model.addAttribute("alert", "alert alert-danger");
            model.addAttribute("message", "Account registration fail");
            model.addAttribute("customer", new Customer());
            return "web/register";
        }


    }

    @PostMapping(value = "authentic_login")
    public String login(Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String pass,RedirectAttributes ra) {
        Customer customer = customerService.findByEmail(email);
        if(customerService.checkLogin(email,pass)){
            customer.setPassword(null);
            session.setAttribute("customer", customer);
            return "redirect:/home-page";
        }else if(adminService.checkLogin(email,pass)){
            customer.setPassword(null);
            session.setAttribute("customer", customer);
            return "redirect:/admin/home";
        }
        ra.addAttribute("alert", "alert alert-danger");
        ra.addAttribute("message", "Login fail");
        return "redirect:/login";
    }

}
