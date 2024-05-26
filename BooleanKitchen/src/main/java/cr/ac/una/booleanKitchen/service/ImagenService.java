/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Brayan rosales 
 */
public class ImagenService {

    /**
     * @param image    es la imagen de la web
     * @param idUser   es el id de el usuario bd !IMPORTANTE valdiar primero
     * @param prefix   es el tipo de imagen (receta,utencilios,compras etc)
     * @param nickName es el nombre de usuario
     * @return
     */
    public Path saveImage(MultipartFile image, String idUser, String prefix, String nickName) {
        Path path = Path.of("BooleanKitchen", "src", "main", "resources", "static", "resources", idUser);
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        path = Path.of("BooleanKitchen", "src", "main", "resources", "static", "resources", idUser, prefix);
        // ahora de valida la ruta de la categoria de imagen
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // nombre del archivo final para guradar en disco
        String nameFinal = nickName + "-" + prefix + "-" + LocalDate.now().toString() + "-"
                + image.getOriginalFilename();

        // IMPORTATANTE ESTA ES LA RUTA QUE SE VA GUARDAR EN LA BASE DE DATOS
        path = Path.of("BooleanKitchen", "src", "main", "resources", "static", "resources", idUser, prefix, nameFinal);

        try {
            Files.createFile(path);
            Files.write(path, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return path;// salida
    }

}
