/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maik
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    @Query("SELECT sl  FROM User sl WHERE sl.userName = ?1")
    User getUser(String i);

    //Optional<User> findByNOMBRE_USUARIO(String NOMBRE_USUARIO);
}
