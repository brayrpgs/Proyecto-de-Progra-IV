/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;
import cr.ac.una.booleanKitchen.domain.Recipe;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        List<Recipe> lista = repoReceta.findAll();
//      Asignr la antiguedad de la receta aqui        
        
        return lista;
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
     
    @Override
    public List<Recipe> mejoresRecetas(){
        List<Recipe> lista = repoReceta.findAll();
        List<Recipe> nuevaLista = new ArrayList<>();
        for(int i = 0; i < lista.size() && i < 10; i++){
            nuevaLista.add(lista.get(i));
        }
        return nuevaLista;
        
    }
    
    private String calcularAntiguedad(LocalDateTime fechaPublicacion) {
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaPublicacion, ahora);
        
        if (duracion.toDays() < 1) {
            long horas = duracion.toHours();
            if (horas < 1) {
                long minutos = duracion.toMinutes();
                if(minutos < 1){
                    return "Justo ahora";
                } else {
                    return "Hace " + minutos + " minuto" + (minutos == 1 ? "" : "s");
                }
            } else {
                return "Hace " + horas + " hora" + (horas == 1 ? "" : "s");
            }
        } else if (duracion.toDays() < 7) {
            long dias = duracion.toDays();
            return "Hace " + dias + " día" + (dias == 1 ? "" : "s");
        } else if (duracion.toDays() < 30) {
            long semanas = duracion.toDays() / 7;
            return "Hace " + semanas + " semana" + (semanas == 1 ? "" : "s");
        } else {
            long meses = duracion.toDays() / 30;
            return "Hace " + meses + " mes" + (meses == 1 ? "" : "es");
        }
    }
}
