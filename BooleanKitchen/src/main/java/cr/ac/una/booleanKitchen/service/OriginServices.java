/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.Data.DataOrigin;
import cr.ac.una.booleanKitchen.domain.Origin;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ceasar
 */
@Service
@Primary
public class OriginServices implements IOriginService{
    
    @Autowired
    private OriginRepoisitory serOrg;

    public final String UPLOAD = "src/main/resources/static/imgOrg";


   

    

    public boolean updateOrigin(Origin origin) throws SQLException {
        return new DataOrigin().updateOrigin(origin);
    }

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

    public Origin obtenerOrigen(String id) {

        return new DataOrigin().obtenerOrigen(id);
    }

    public boolean validar(String name,
            String description,
            String identificador,
            String country,
            String continent,
            String taste) {

        return !(name.trim().isEmpty() || description.trim().isEmpty() || country.trim().isEmpty()
                || continent.trim().isEmpty() || taste.trim().isEmpty());
    }

    @Override
    public void EliminarOrigen(int codigo) {
      serOrg.deleteById(codigo);  
        
    }

    @Override
    public void crearOrigen(Origin origin) {
        serOrg.save(origin);
        
    }

    @Override
    public Origin getOrigen(String identificador) {
        return serOrg.findByIdentificador(identificador);
    }

    @Override
    public List<Origin> leerOrigen() {
        return serOrg.findAll();
    }

    @Override
    public Page<Origin> getPageOrigin(Pageable pageable){
        return serOrg.findAll(pageable);
    }
}
