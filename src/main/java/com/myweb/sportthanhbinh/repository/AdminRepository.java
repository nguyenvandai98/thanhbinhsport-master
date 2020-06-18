package com.myweb.sportthanhbinh.repository;

import com.myweb.sportthanhbinh.entity.Admin;
import com.myweb.sportthanhbinh.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select c from Admin c where c.Email like :email")
    Admin findByEmail(String email);
}
