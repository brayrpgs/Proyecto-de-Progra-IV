/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.domain.Notice;
import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.domain.Recipe;
import cr.ac.una.booleanKitchen.domain.Utensil;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IRecetaService;
import cr.ac.una.booleanKitchen.service.serviceReceta;
import java.time.LocalDate;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel Briones
 */
@Controller
@RequestMapping("/c_inicio")
public class ControllerPaginaInicio {

    @Autowired 
    private IRecetaService recetaService;
    
    @Autowired 
    private ICategoryService categoryService;

    @GetMapping({"/index"})
    public String inicio(Model model){
        model.addAttribute("usuario", Utilidades.user);
        
        
//        LinkedList<Category> categorias = new serviceReceta().categorias();
//        
//        LinkedList<Notice> noticias = new serviceReceta().noticias();
//        
        LinkedList<Origin> origenes = new LinkedList<>();
        origenes.add(new Origin(1, "Mexicana", "", "UTE-001", "", "receta.jpeg", "", ""));
//
//
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient("", "Manzana", 1, 1, null, 1, "", "receta.jpeg", "ING-001"));
        LinkedList<Utensil> utensils = new LinkedList<>();
        utensils.add(new Utensil(1, "Cuchara", "", null, 0, 0, "", "UTE-001"));
        
//        model.addAttribute("noticias", noticias);
        model.addAttribute("categorias", categoryService.getCategory());
        model.addAttribute("origen", origenes);
        model.addAttribute("utensilios", utensils);
        model.addAttribute("ingredientes", ingredients);
        
        //Traer el top 10
        model.addAttribute("mejoresRecetas", recetaService.getRecipes());
        
       
        return "paginaInicio";
    }

    @GetMapping("/verRecetas")
    public String verRecetas(Model model){
        model.addAttribute("usuario", Utilidades.user);
        
        
//        LinkedList<Category> categorias = new serviceReceta().categorias();
//        
//        LinkedList<Notice> noticias = new serviceReceta().noticias();
//        
//        LinkedList<Origin> origenes = new serviceReceta().origen();
//
//
//        LinkedList<Ingredient> ingredients = new serviceReceta().ingredientes();
//
//        LinkedList<Utensil> utensils = new serviceReceta().utensilios();
//        
//        model.addAttribute("noticias", noticias);
//        model.addAttribute("categorias", categorias);
//        model.addAttribute("origen", origenes);
//        model.addAttribute("utensilios", utensils);
//        model.addAttribute("ingredientes", ingredients);
        
        model.addAttribute("recetas", recetaService.getRecipes());
        return "verRecetas";
    }

    @GetMapping({"/showProfile"})
    public String showProfile(Model model){

        model.addAttribute("usuario", Utilidades.user);
        return "showProfile";
    }
}
