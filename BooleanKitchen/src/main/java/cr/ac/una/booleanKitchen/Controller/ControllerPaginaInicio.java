/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.domain.Notice;
import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.domain.Utensil;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IRecetaService;
import cr.ac.una.booleanKitchen.service.IngredientService;
import cr.ac.una.booleanKitchen.service.NoticeRepository;

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

    @Autowired
	private NoticeRepository noticeRepository;

    //llamar
    @GetMapping({ "/index" })
    public String inicio(Model model) {
        model.addAttribute("usuario", Utilidades.user);

        LinkedList<Origin> origenes = new LinkedList<>();
        origenes.add(new Origin(1, "Mexicana", "", "UTE-001", "", "receta.jpeg", "", ""));

        LinkedList<Ingredient> ingredients = IngredientService.getIngredients();

        LinkedList<Utensil> utensils = new LinkedList<>();
        utensils.add(new Utensil(1, "UTE-001", "Cuchara", "", null, "", 0, 0, ""));

        LinkedList<Notice> notices = noticeRepository.findAllNotices();

        model.addAttribute("categorias", categoryService.getCategory());
        model.addAttribute("origen", origenes);
        model.addAttribute("utensilios", utensils);
        model.addAttribute("ingredientes", ingredients);
        model.addAttribute("noticias", notices);

        // Traer el top 10
        model.addAttribute("mejoresRecetas", recetaService.mejoresRecetas());

        return "paginaInicio";
    }

    @GetMapping("/verRecetas")
    public String verRecetas(Model model) {
        model.addAttribute("usuario", Utilidades.user);

        model.addAttribute("recetas", recetaService.getRecipes());
        return "verRecetas";
    }

    @GetMapping({ "/showProfile" })
    public String showProfile(Model model) {

        model.addAttribute("usuario", Utilidades.user);
        return "showProfile";
    }
}
