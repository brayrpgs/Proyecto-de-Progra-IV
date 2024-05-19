/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.service.AdminRepository;
import cr.ac.una.booleanKitchen.service.UserRepository;
import cr.ac.una.booleanKitchen.domain.Admin;
import cr.ac.una.booleanKitchen.domain.User;
import cr.ac.una.booleanKitchen.domain.UserDto;
import jakarta.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Maik
 */

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired   
    private UserRepository repo;
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//Encriptar contraseña
    @GetMapping("/")
    public String showIndex() {
    return "index";
    }
    @GetMapping({"", "/"})
	public String showUsersList(Model model) {
		List<User> users = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
		model.addAttribute("users", users);
    	return "UserList";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
    	UserDto userDto = new UserDto();
    	model.addAttribute("userDto", userDto);
        return "CreateUser";
    }
    @PostMapping("/create")
public String createUser(@Valid @ModelAttribute UserDto userDto, BindingResult result) {
    
    if (result.hasErrors()) {
        result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        return "CreateUser";
    }
   
    // Guardar la imagen
    MultipartFile image = userDto.getImageFile();
    String storageFileName = null;
    if (image != null && !image.isEmpty()) {
    try {
        String uploadDir = "public/images/";
        Path uploadPath = Paths.get(uploadDir);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        storageFileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        try (InputStream inputStream = image.getInputStream()) {
            Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
        }
    } catch (IOException ex) {
        System.out.println("Error al guardar la imagen: " + ex.getMessage());

    }
}
    
    // Crear y guardar el usuario
    User user = new User();
    user.setUserName(userDto.getUserName());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setUserType(userDto.getUserType());
    user.setImagenFileName(storageFileName);
    user = repo.save(user);
    
     // Verificar si el usuario es un administrador y guardar datos de administrador
        if ("ADMIN".equals(user.getUserType())) {
            Admin admin = new Admin();
            admin.setUser(user);  // Establecer la relación con el usuario
            admin.setNombre(userDto.getNombre());
            admin.setApellido(userDto.getApellido());
            admin.setCarne(userDto.getCarnet());
            adminRepo.save(admin);
        }
    return "redirect:/users";
    
}
 @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            System.out.println("El usuario con ID " + id + " no existe.");
            return "redirect:/users";
        }
        
        UserDto userDto = new UserDto();
        userDto.setId(user.getId()); 
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserType(user.getUserType());
        userDto.setImagenFileName(user.getImagenFileName()); 
        
        // Si el usuario es administrador, cargar los datos adicionales
        if ("ADMIN".equals(user.getUserType())) {
            Admin admin = adminRepo.findByUserId(user.getId());
            if (admin != null) {
                userDto.setNombre(admin.getNombre());
                userDto.setApellido(admin.getApellido());
                userDto.setCarnet(admin.getCarne());
            }
        }

        model.addAttribute("userDto", userDto);
        return "EditUser";
    }

    @PostMapping("/edit")
public String updateUser(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("userDto", userDto);
        return "EditUser";
    }

    User user = repo.findById(userDto.getId()).orElse(null);
    if (user == null) {
        System.out.println("Usuario no encontrado con ID: " + userDto.getId());
        return "redirect:/users";
    }

    // Verifica si se ha proporcionado una imagen y si no, mantiene la actual
    String storageFileName = user.getImagenFileName(); // Se inicia con la imagen actual
    MultipartFile imageFile = userDto.getImageFile();
    if (imageFile != null && !imageFile.isEmpty()) {
        storageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get("src/main/resources/static/images/");
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = imageFile.getInputStream()) {
                Path filePath = uploadPath.resolve(storageFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar la imagen: " + ex.getMessage());
            
        }
    }

    // Actualiza la imagen solo si se cargó una nueva
    if (!storageFileName.equals(user.getImagenFileName())) {
        user.setImagenFileName(storageFileName);
    }

    user.setUserName(userDto.getUserName());
    user.setEmail(userDto.getEmail());

    // Actualiza la contraseña solo si se ingresó una nueva
    if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
        user.setPassword(userDto.getPassword());
    } else {
        // Si no se proporciona una nueva contraseña, mantiene la actual
        userDto.setPassword(user.getPassword());
    }

    user.setUserType(userDto.getUserType());
    repo.save(user);
    
    if ("ADMIN".equals(user.getUserType())) {
        Admin admin = adminRepo.findByUserId(user.getId());
        if (admin != null) {
            // Actualiza el registro de admin si existe
            admin.setNombre(userDto.getNombre());
            admin.setApellido(userDto.getApellido());
            admin.setCarne(userDto.getCarnet());
            adminRepo.save(admin);
        } else {
            // Si no existe el registro de admin, crearlo
            admin = new Admin();
            admin.setUser(user);
            admin.setNombre(userDto.getNombre());
            admin.setApellido(userDto.getApellido());
            admin.setCarne(userDto.getCarnet());
            adminRepo.save(admin);
        }
    }
    
    return "redirect:/users";
}

//Brayan
@GetMapping("/delete")
public String deleteUser(@RequestParam int id) {
    try {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            System.out.println("Usuario no encontrado con ID: " + id);
            return "redirect:/users";
        }
        
        // Eliminar la imagen del usuario si existe
        if (user.getImagenFileName() != null) {
            Path imagePath = Paths.get("public/images/" + user.getImagenFileName());
            try {
                Files.delete(imagePath);
            } catch (IOException ex) {
                System.out.println("Error al eliminar la imagen del usuario: " + ex.getMessage());
            }
        }
        
        // Eliminar el registro de administrador si existe
            if ("ADMIN".equals(user.getUserType())) {
                Admin admin = adminRepo.findByUserId(user.getId());
                if (admin != null) {
                    adminRepo.delete(admin);
                }
            }
        
        // Eliminar el usuario
        repo.delete(user);
    } catch (Exception ex) {
        System.out.println("Excepción al eliminar usuario: " + ex.getMessage());
    }
    
    return "redirect:/users";
}

}

