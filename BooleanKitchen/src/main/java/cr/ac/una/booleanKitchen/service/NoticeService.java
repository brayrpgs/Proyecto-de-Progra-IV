package cr.ac.una.booleanKitchen.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cr.ac.una.booleanKitchen.domain.Notice;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    

    public void saveNotice(Notice notice) {
        // Guardar la instancia de Notice en la base de datos
        noticeRepository.save(notice);
    }

    public Optional<Notice> findByIdentificador(String IDENTIFICADOR){
        return noticeRepository.findByIDENTIFICADOR(IDENTIFICADOR);
    }
    public Optional<Notice> findByURL(String URL){
        return noticeRepository.findByURL(URL);
    }

    @Transactional
    public void deleteNoticeByID(Integer ID){
        noticeRepository.deleteByID(ID);   
    }

    // Apartado de imagenes

    public String date(LocalDateTime localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        return localDate.format(formatter);
    }

    public String uploadImage(MultipartFile image) {

        String date = date(LocalDateTime.now());

        if (!image.isEmpty()) {
            try {
                // Obtener los bytes de la imagen
                //byte[] bytes = image.getBytes();

                // Ruta del directorio de destino
                //String uploadDir = "src/main/resources/static/img/ingredientes/";

                // Ruta del archivo de destino
                //String filePath = uploadDir + date + image.getOriginalFilename();

                // Escribir los bytes en el archivo de destino
                //Path path = Paths.get(filePath);
                //Files.write(path, bytes);

                String nameFinal = date + image.getOriginalFilename();

                Path path = Path.of("src" , "main", "resources", 
                "static" , "imgNot", nameFinal);   
                
                Files.createFile(path);
                Files.write(path, image.getBytes());

                return nameFinal;

            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
                
            }
        }
        return "";
    }

    public boolean deleteImage(String imageName) {
        
        try {
            Path path = Path.of("src" , "main", "resources", 
                "static" , "imgNot", imageName);  
            Files.deleteIfExists(path);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }

}
