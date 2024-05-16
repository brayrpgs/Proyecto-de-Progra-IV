/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;


import cr.ac.una.booleanKitchen.domain.Utensil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ceasar
 */
@Service
@Primary
public class UtensilioService implements IUtensilioService {

    @Autowired
    private utensilioRepository serUtl;

    @Override
    public void crearUtensilio(Utensil utensilio) {
        serUtl.save(utensilio);
    }

    @Override
    public List leerUtensilio() {
        return serUtl.findAll();
    }

    @Override
    public void EliminarUtensilio(int codigo) {
        serUtl.deleteById(codigo);
    }
    
    @Override
    public Utensil getUtensilio(String identificador) {
       return serUtl.findByIdentificador(identificador);
    }
    
    

    public final String UPLOAD = "src/main/resources/static/img";

    public String date(LocalDateTime localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        return localDate.format(formatter);
    }

    public String getRoute(MultipartFile file) {
        LocalDateTime dateTime = LocalDateTime.now();
        String att = date(dateTime);
        String route = att + file.getOriginalFilename();
        return route;
    }

    
    /*
    public boolean insertImg(MultipartFile file, String route) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD + "/" + route);
                Files.write(path, bytes);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public boolean deleteImage(String imageName) {
        try {
            Path imagePath = Paths.get(UPLOAD, imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
*/
    
    //Agrego eso a la carpeta y la imagen con el nuevo nombre
       public boolean  insertImg(MultipartFile file,String route){
            if (!file.isEmpty()) {
            try {
                
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
                System.out.println(absolutePath);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\imgUts") + "/" +route);
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
            Path imagePath = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\imgUts"), imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
    
}
