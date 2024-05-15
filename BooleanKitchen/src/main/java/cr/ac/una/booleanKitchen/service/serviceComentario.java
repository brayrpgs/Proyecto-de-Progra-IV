/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@Primary
public class serviceComentario implements IComentariosService {

    @Autowired
    private comentarioRepository repoComment;

    @Override
    public void guardar(Comment comentario) {
        repoComment.save(comentario);
    }

    @Override
    public List<Comment> getComments() {
        return repoComment.findAll();
    }

    @Override
    public List<Comment> findByRecetaId(String identificador) {
        return repoComment.findByRecipeIdentificador(identificador);
    }

//    public LinkedList<Comment> comentarios(String codReceta){
//        return new AccesoDatosComentarios().comentarios(codReceta);
//    }
}