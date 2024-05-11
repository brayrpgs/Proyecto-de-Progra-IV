/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;



import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.jpa.CategoryRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josue
 */
@Service
@Primary
public class CategoryServiceUpdate implements ICategoryService{
    @Autowired
    private CategoryRepository repoCat;
//    public final String UPLOAD = "src/main/resources/static/img";
    
   
    
   
    
    
    
     public boolean ValidationNumero(String input) {
        String regex = "[-+]?\\d*\\.?\\d+";
        return input.matches(regex);
    }

    public boolean getVisibleCat(String status){
        return (status.equals("Activo"));
    }
    
    public boolean validation(Category cat,String status){
        return !(cat.getIdSerial().trim().isEmpty()||cat.getName().trim().isEmpty()||cat.getLabel().trim().isEmpty()||cat.getDescription().trim().isEmpty()||
                status.trim().isEmpty() || status.trim().equals("NS") || cat.getLabel().trim().equals("NS"));
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
     
     
      
       public boolean  insertImg(MultipartFile file,String route){
            if (!file.isEmpty()) {
            try {
                
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
                System.out.println(absolutePath);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\img") + "/" +route);
                System.out.println(path);
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
            Path imagePath = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\img"), imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
      
      public LinkedList<String> getListLabel(){
          LinkedList<String> list= new LinkedList();
          list.add("INGREDIENTE");
          list.add("UTENCILIO");
          list.add("RECETAS");
       return list;
      }

    @Override
    public int guardar(Category cat) {
    try {
        repoCat.save(cat);
       
        return 1; // Éxito en el guardado
    } catch (DataIntegrityViolationException ex) {
        int errorStatus = getErrorStatus(ex.getMessage());
        return errorStatus; // Devuelve el código de error específico
    } catch (Exception e) {
        // Manejo de otras excepciones generales
        // Aquí puedes registrar el error, mostrar un mensaje, etc.
        return -3; // Otro tipo de error
    }
}

private int getErrorStatus(String message) {
    if (message.contains("IDENTIFICADOR")) {
        return -1; // Error de clave única en el campo IDENTIFICADOR
    } else if (message.contains("NOMBRE")) {
        return -2; // Error de clave única en el campo NOMBRE
    }
    return -3; // Otro tipo de error
}

    @Override
    public List getCategorias() {
       return repoCat.findAll();
    }

    @Override
    public void EliminarCategoria(int codigo) {
        
    }

    @Override
    public Category getCategory(String cat) {
       return repoCat.findByIdentificador(cat);
    }

    @Override
    public boolean DeleteCategory(String cat) {
       try{
           repoCat.deleteByIdentificador(cat);
           return true;
       }catch(Exception e){
           return false;
       }
    }

    @Override
    public boolean modifyCat(Category cat) {
        try{
           
      repoCat.updateCategory(cat,cat.getIdSerial());
      return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Page<Category> getPage(Pageable page) {
     return repoCat.findAll(page);
    }
      
      
      
}


