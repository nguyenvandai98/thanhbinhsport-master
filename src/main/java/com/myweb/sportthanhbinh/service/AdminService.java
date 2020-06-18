package com.myweb.sportthanhbinh.service;

import com.myweb.sportthanhbinh.entity.Admin;


public interface AdminService {
    void save(Admin admin);

    void update(Admin admin);

    Admin findByEmail(String email);

    boolean checkLogin(String email, String password);
}
