/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Comment;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Usuario
 */
public interface IComentariosService {
     public void guardar(Comment comentario);
     
     public List<Comment> getComments();
 
     public Comment findByIdentificador(String identificador);
     
     public void eliminar(Comment comentario);
     
     Page<Comment> findByRecipeId(String identificador, Pageable pageable);
}
