/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;


import org.springframework.data.jpa.repository.JpaRepository;

import cr.ac.una.booleanKitchen.domain.Admin;

/**
 *
 * @author Dj229
 */
public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Admin findByUserId(Integer userId);
    
}
