/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;
import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import org.springframework.core.io.Resource;
import cr.ac.una.booleanKitchen.domain.Preparation;
import cr.ac.una.booleanKitchen.domain.Step;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel Briones
 */
@Service
@Primary
public class PreparationService implements IPreparationService {
    @Autowired
    private preparationRepository repoPrep;

    //private LinkedList<Step> stepsLis;
    private Preparation prep;
    private int index;
    public final String UPLOAD = "src/main/resources/static/assets";

    /*Lista de dificultad*/

    public PreparationService() {
      //  stepsLis = new LinkedList();
        prep = new Preparation();
    }

    public Preparation getPrep() {
        return prep;
    }

    public void setPrep(Preparation prep) {
        this.prep = prep;
    }

    public boolean ValidationNumero(String input) {
        String regex = "[-+]?\\d*\\.?\\d+";
        return input.matches(regex);
    }
    
     public boolean validarCadena(String input) {
    String regex = "^[\\p{L}\\s]+$";

    return input.matches(regex);
}

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public LinkedList<String> getDifficulty (){
        LinkedList<String> list= new LinkedList();
        list.add("Novato");
        list.add("Cocinero");
        list.add("Chef");
        
        return list;
    }
    
    public boolean verifyNameTitle(String date){
        
        for(Step step: Utilidades.stepsLis){
            if(step.getTitle().trim().equalsIgnoreCase(date.trim())){
                return true;
            }
        }
        return false;
    }
    
    public boolean verifyNameTitleModify(String date){
        int indexSize=0;
        for(Step step: Utilidades.stepsLis){
           if(Utilidades.index!=indexSize){
            if(step.getTitle().trim().equalsIgnoreCase(date.trim())){
                return true;
            }
           }
            indexSize+=1;
        }
        return false;
    }
    
    public boolean validation(String label, String time, String warning){
        
     return !(label.trim().isEmpty() || time.trim().isEmpty() || warning.trim().isEmpty());
    }
    
    
    public boolean ValidationStep(String title,String step){
        return !(title.trim().isEmpty()||step.trim().isEmpty());
    }
    
    
      public String date(LocalDateTime localDate){
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        return localDate.format(formatter);
    }
   
   
     public String getRoute(MultipartFile file){
           LocalDateTime dateTime=LocalDateTime.now();
             String att=date(dateTime);

                // Ruta donde se guardará la imagen en el directorio de recursos estáticos
               String route= att+file.getOriginalFilename();
               return route;
      }
     
     
       //metodo de ingreso de imagen a carpeta externa en proyecto
       public boolean  insertImg(MultipartFile file,String route){
            if (!file.isEmpty()) {
            try {
                
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
         
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\assets") + "/" +route);
                System.out.println("\nLa ruta es: " + path);
                Files.write(path, bytes);
               return true;
            } catch (IOException e) {
                //por si da error
              return false;
            }
        } 
            return false;
      }
      
      
      public  boolean deleteImage(String imageName) {
        
      

        try {
               Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
            Path imagePath = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\assets"), imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
    
      public String getCodePrep(){
          LocalDateTime ahora = LocalDateTime.now();

        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        String fechaFormateada = ahora.format(formateador);
        return "PRP-"+fechaFormateada;
      }
      
     public int getIndexSteps(String url) {
        int i = 0; // Inicializar a 0
        for (Step step : Utilidades.stepsLis) {
            if (step.getTitle().trim().equalsIgnoreCase(url.trim())) {
                return i; // Devuelve el índice actual si hay una coincidencia
            }
            i++; // Incrementa el índice en cada iteración
        }
        return -1; // Devuelve -1 si no se encontró ninguna coincidencia
    }

    @Override
    public void guardar(Preparation prep) {
        //prep.getIdRecipe().setImage(prep.getIdRecipe().getImage().replace(":", "-"));
        //prep.setRouteImg(prep.getRouteImg().replace(":", "-"));
        repoPrep.save(prep);
    }

public List<Step> getStepsByPrep(Preparation prep){
   List<Step> steps = new ArrayList();
   for (Step step : Utilidades.stepsLis){
        step.setPreparation(prep);
        steps.add(step);
    }
return steps;
}
    

}
