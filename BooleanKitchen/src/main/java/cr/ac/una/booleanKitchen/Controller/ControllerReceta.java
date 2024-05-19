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
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IPreparationService;
import cr.ac.una.booleanKitchen.service.IRecetaService;
import cr.ac.una.booleanKitchen.service.PreparationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/c_receta")
public class ControllerReceta {
    @Autowired
    private IPreparationService prepService;
    @Autowired
    private IRecetaService recetaService;
    
    @Autowired
    private ICategoryService categoryService;
    
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
            i.setCode(ingre);
            listaIngredientes.add(i);
            System.out.println(i.getCode());
        }
        
        
        
        Category cat = categoryService.getCategoryOnly(categoria);
        

        Origin orige = new Origin();
        orige.setId(1);
        
        if(new PreparationService().validation(dificultad,tiempo,advertencias)){
         
            Utilidades.preparacion.setIdSerial("PREP-"+dificultad+LocalDateTime.now());
            Utilidades.preparacion.setDifficulty(dificultad);
            Utilidades.preparacion.setTime(LocalTime.parse(tiempo, DateTimeFormatter.ofPattern("HH:mm")));
            Utilidades.preparacion.setWarnings(advertencias);
            Utilidades.preparacion.setNoteAuthor(nota);
            System.out.println("\nPreparación:\n");
            System.out.println("La dificultad: " + Utilidades.preparacion.getDifficulty());
            System.out.println(Utilidades.preparacion.getTime());
            System.out.println(Utilidades.preparacion.getWarnings());
            System.out.println(Utilidades.preparacion.getNoteAuthor());
            System.out.println("IMG_preparación: " + img_preparacion.getOriginalFilename());
            
            System.out.println("primero en la Lista: " + Utilidades.stepsLis.get(0).getTitle());
            Recipe receta = new Recipe(0, "REC-" + nombre + LocalDateTime.now(), nombre, orige, cat, 0, ("REC-" + nombre + LocalDateTime.now()+ img_receta.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.-]", "-"), Utilidades.user, 0);
            insertImg(img_receta, receta.getImage());
            Utilidades.preparacion.setRouteImg( (Utilidades.preparacion.getIdSerial()+img_preparacion.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.-]", "-"));
            insertImg(img_preparacion, Utilidades.preparacion.getRouteImg());
  
            Utilidades.preparacion.setIdRecipe(receta);
            System.out.println("Tamaño de la lista de pasos: " + Utilidades.stepsLis.size() + " : " + Utilidades.stepsLis.get(0).getTitle());
            Utilidades.preparacion.setPreparationList(Utilidades.stepsLis);
            //prep.setPreparationList(new PreparationService().getStepsByPrep(prep));
            
            
            prepService.guardar(Utilidades.preparacion);
            
            Utilidades.stepsLis.clear(); //Limpiamos las listas de pasos
            Utilidades.preparacion = new Preparation();
            //recetaService.Guardar(receta);
        }else{
            //MENSAJE DE ERROR
        }
       
        
        
        return "redirect:/c_inicio/index";
    }

    

    @GetMapping("/verDetallesDeReceta")
    public String verDetallesDeReceta(@RequestParam("id") String identificador, Model model){
        Recipe re = recetaService.findByIdentificador(identificador);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        re.getPreparacion().setTimeStr(re.getPreparacion().getTime().format(formatter));
        model.addAttribute("receta", re);
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

    public boolean  insertImg(MultipartFile file,String route){
            if (!file.isEmpty()) {
            try {
                
                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();
         
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\assets") + "/" +route.replaceAll("[^a-zA-Z0-9.-]", "-"));
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
