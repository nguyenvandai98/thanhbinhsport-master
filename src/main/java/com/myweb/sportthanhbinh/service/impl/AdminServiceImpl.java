package com.myweb.sportthanhbinh.service.impl;

import com.myweb.sportthanhbinh.entity.Admin;
import com.myweb.sportthanhbinh.entity.Customer;
import com.myweb.sportthanhbinh.repository.AdminRepository;
import com.myweb.sportthanhbinh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            if (password.equals(admin.getPassword()) && admin.isStatus()== true) {
                return true;
            }
        }
        return false;
    }
}
