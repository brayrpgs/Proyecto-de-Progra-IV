/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prograIV.Usuario.Services;

import com.prograIV.Usuario.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dj229
 */
public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Admin findByUserId(Integer userId);
    
}
