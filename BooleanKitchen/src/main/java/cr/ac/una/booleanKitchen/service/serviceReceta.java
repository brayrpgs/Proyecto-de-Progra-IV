/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;
import cr.ac.una.booleanKitchen.domain.Recipe;

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
public class serviceReceta implements IRecetaService{

    @Autowired
    private RecetaRepository repoReceta;
    
    @Override
    public void Guardar(Recipe receta) {
        receta.setImage(receta.getImage().replace(":", "-"));
        repoReceta.save(receta);
    }

    @Override
    public List<Recipe> getRecipes() {
        return repoReceta.findAll();
    }
    
    @Override
    public Recipe findByIdentificador(String identificador) {
        return repoReceta.findOneRecipe(identificador);
    }
    
    @Override
    public void eliminar(Recipe receta){
        repoReceta.delete(receta);
    }

     @Override
    public boolean DeleteRecipe(String idSerial) {
        try{
        repoReceta.deleteStepsByRecetaIdentificador(idSerial);
        repoReceta.deletePreparacionByRecetaIdentificador(idSerial);
        repoReceta.deleteByIdentificador(idSerial);
        return true;
        }catch(Exception e){
            return false;
        }
    }
     
}
