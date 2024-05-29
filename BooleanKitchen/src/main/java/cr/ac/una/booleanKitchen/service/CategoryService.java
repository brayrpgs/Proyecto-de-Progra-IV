
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Category;
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
 * @author Usuario
 */
@Service
@Primary
public class CategoryService implements ICategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;

 //validacion [ara datos numericos (aun sin utilizar)
     public boolean ValidationNumero(String input) {
        String regex = "[-+]?\\d*\\.?\\d+";
        return input.matches(regex);
    }
    
     
    public boolean getVisibleCat(String status){
        return (status.equals("Activo"));
    }
    //Validacion
    public boolean validation(Category cat,String status){
        return !(cat.getIdSerial().trim().isEmpty()||cat.getName().trim().isEmpty()||cat.getLabel().trim().isEmpty()||cat.getDescription().trim().isEmpty()||
                status.trim().isEmpty() || status.trim().equals("NS") || cat.getLabel().trim().equals("NS"));
    }
    
   
   //Genero una secuenta de fecha, horas, minutos y segundos
    public String date(LocalDateTime localDate){
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        return localDate.format(formatter);
    }
   
   //hago un nombre de imagen por ejemplo: 2024 05 14 08 51 06Hola.png
     public String getRoute(MultipartFile file){
           LocalDateTime dateTime=LocalDateTime.now();
             String att=date(dateTime);

                // Ruta donde se guardará la imagen en el directorio de recursos estáticos
               String route= att+file.getOriginalFilename();
               return route;
      }
     
     
      //Agrego eso a la carpeta y la imagen con el nuevo nombre
       public boolean  insertImg(MultipartFile file,String route){
            if (!file.isEmpty()) {
            try {
                
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
                System.out.println(absolutePath);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\imgCat") + "/" +route);
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
      
      //Para eliminar imagenes
      public  boolean deleteImage(String imageName) {
        try {
               Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
            Path imagePath = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\imgCat"), imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
      //Por el momento tengo las eqtiquetas en Backend, mas adelante las implemento en BD.
      public LinkedList<String> getListLabel(){
          LinkedList<String> list= new LinkedList<String>();
          list.add("INGREDIENTE");
          list.add("UTENCILIO");
          list.add("RECETAS");
       return list;
      }

    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    
     @Override
    public Category getCategoryOnly(String cat) {
       return categoryRepository.findByIdentificador(cat);
    }

    @Override
    public int DeleteCategory(String cat) {
       try{
           categoryRepository.deleteByIdentificador(cat);
           return 1;
       }catch(DataIntegrityViolationException ex){
           if(getCodeError(ex)==1451){
               return -1;
           }
           return -2;
       }catch(Exception e){
           return -3;
       }
    }
    //si ocurre un error por estar asociado a otra cosa se realiza el llamado
   public int getCodeError(DataIntegrityViolationException ex) {
    String mensajeError = ex.getMessage(); // Obtén el mensaje de error de la excepción
    if (mensajeError.contains("foreign key constraint fails")) {
        return 1451; // Devuelve el código de error específico de FK
    }
    return -1; // No se encontró un error de clave foránea
}

    @Override
    public boolean modifyCat(Category cat) {
        try{
           
      categoryRepository.updateCategory(cat,cat.getIdSerial());
      return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Page<Category> getPage(Pageable page) {
     return categoryRepository.findAll(page);
    }

   @Override
    public int guardar(Category cat) {
    try {
        categoryRepository.save(cat);
       
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
//Por si ocurre un error me diga de que tipo fue.
private int getErrorStatus(String message) {
    if (message.contains("IDENTIFICADOR")) {
        return -1; // Error de clave única en el campo IDENTIFICADOR
    } else if (message.contains("NOMBRE")) {
        return -2; // Error de clave única en el campo NOMBRE
    }
    return -3; // Otro tipo de error
}

    @Override
    public void EliminarCategoria(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Page<Category> getPagebyFilter(String filter, Pageable page) {
      return categoryRepository.findByCategoryFilter(filter, page);
    }
      
}
