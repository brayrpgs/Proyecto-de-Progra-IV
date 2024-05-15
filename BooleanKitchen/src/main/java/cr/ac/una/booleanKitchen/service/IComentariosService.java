/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Comment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IComentariosService {
     public void guardar(Comment comentario);
    
//     public Comment getCommentSelect(String identificador);
     
     public List<Comment> getComments();
 
//     public void deleteCom(String idSerial);
     List<Comment> findByRecetaId(String identificador);
}
