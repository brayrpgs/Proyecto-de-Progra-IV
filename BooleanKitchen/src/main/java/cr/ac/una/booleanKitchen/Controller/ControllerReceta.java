/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.domain.Preparation;
import cr.ac.una.booleanKitchen.domain.Recipe;
import cr.ac.una.booleanKitchen.domain.Utensil;
import cr.ac.una.booleanKitchen.service.IRecetaService;
import cr.ac.una.booleanKitchen.service.serviceReceta;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/c_receta")
public class ControllerReceta {
    
    @Autowired
    private IRecetaService recetaService;
    
    @PostMapping("/nuevaReceta")
    public String agregar(
            @RequestParam("nombre") String nombre,
            @RequestParam("origen") String origen,
            @RequestParam("categoria") String categoria,
            @RequestParam("img_receta") MultipartFile img_receta,
            @RequestParam("datosTablaUtensilios") List<String> Utensilios,
            @RequestParam("datosTablaIngredientes") List<String> ingredientes,
            @RequestParam("dificultad") String dificultad,
            @RequestParam("tiempo") String tiempo,
            @RequestParam("advertencias") String advertencias,
            @RequestParam("nota") String nota,
            @RequestParam("img_preparacion") MultipartFile img_preparacion,
            Model model){
            

        LinkedList<Utensil> listaUtensilios = new LinkedList<>();
        for(String utensil : Utensilios){
            Utensil u = new Utensil();
            u.setIdentificador(utensil);
            listaUtensilios.add(u);
            System.out.println(u.getIdentificador());
        }

        System.out.println("\nIngredientes:\n");
        LinkedList<Ingredient> listaIngredientes = new LinkedList<Ingredient>();
        for(String ingre : ingredientes){
            Ingredient i = new Ingredient();
            i.setIdentificador(ingre);
            listaIngredientes.add(i);
            System.out.println(i.getIdentificador());
        }
        

        Category cat = new Category();
        cat.setId(1);
        cat.setIdSerial(categoria);

        Preparation prep = new Preparation();
        prep.setIdSerial("PREP-"+dificultad+LocalDateTime.now());
        prep.setDifficulty(dificultad);
        prep.setTime(LocalTime.parse(tiempo, DateTimeFormatter.ofPattern("HH:mm")));
        prep.setWarnings(advertencias);
        prep.setNoteAuthor(nota);
        System.out.println("\nPreparación:\n");
        System.out.println(prep.getDifficulty());
        System.out.println(prep.getTime());
        System.out.println(prep.getWarnings());
        System.out.println(prep.getNoteAuthor());
        System.out.println("IMG_preparación: " + img_preparacion.getOriginalFilename());

//        Recipe receta = new Recipe(-1, "REC-" + nombre + LocalDateTime.now(), listaIngredientes, nombre, Integer.parseInt(origen.substring(origen.length() -1)), listaUtensilios, null, cat, prep, 0, "REC-" + nombre + LocalDateTime.now()+ img_receta.getOriginalFilename() , "");
        Origin orige = new Origin();
        orige.setId(1);
        
        Recipe receta = new Recipe(0, "REC-" + nombre + LocalDateTime.now(), nombre, orige, cat, 0, "REC-" + nombre + LocalDateTime.now()+ img_receta.getOriginalFilename(), Utilidades.user, 0);
        insertImg(img_receta, receta.getIdentificador() + img_receta.getOriginalFilename());
        insertImg(img_preparacion, prep.getIdSerial()+img_preparacion.getOriginalFilename());

//        new serviceReceta().agregarReceta(receta);
        
        recetaService.Guardar(receta);
        return "redirect:/c_inicio/index";
    }

    

    @GetMapping("/verDetallesDeReceta")
    public String verDetallesDeReceta(@RequestParam("id") String identificador, Model model){
          
        model.addAttribute("receta", recetaService.findByIdentificador(identificador));
        return "detallesRecetas";
    }
    
    
    
    @GetMapping("/editarReceta")
    public String editar(@RequestParam("identificadorReceta") String identificador, Model model){
        
        System.out.println("Receta a editar: " + identificador);
        return "redirect:/c_inicio/index";
    }
    
    @GetMapping("/eliminarReceta")
    public String eliminar(@RequestParam("identificadorReceta") String identificador, Model model){
        
        System.out.println("Receta a eliminar: " + identificador);
        Recipe receta = recetaService.findByIdentificador(identificador);
        if(receta != null){
            recetaService.eliminar(receta);
        } else {
            System.out.println("No se puedo eliminar la receta con el identificador: " + identificador);
        }
        return "redirect:/c_inicio/index";
    }

    public boolean insertImg(MultipartFile file, String name) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath().replace("\\target\\classes", "\\src\\main\\resources\\static\\assets");
                Path path = Paths.get(absolutePath + "/" + name.replace(":", "-"));

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
}
