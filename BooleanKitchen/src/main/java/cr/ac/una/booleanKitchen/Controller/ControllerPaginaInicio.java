/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Logica.LogicaReceta;
import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IRecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private ICategoryService categoryService;

    @Autowired
    private IRecetaService recetaService;

    @GetMapping({ "/index" })
    public String inicio(Model model) {
        model.addAttribute("usuario", Utilidades.user);        
        model.addAttribute("mejoresRecetas", recetaService.findTop10ByOrderByCalificacionDesc());


        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);

        return "paginaInicio";
    }

    @GetMapping("/verRecetas")
    public String verRecetas(@PageableDefault(size = 4, page = 0) Pageable pageable, Model model) {
        model.addAttribute("usuario", Utilidades.user);

        // model.addAttribute("recetas", recetaService.getRecipes());

        //Obtiene la paginación de la receta
        LogicaReceta.paginacionRecetas(model, "", pageable, recetaService);

        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "verRecetas";
    }

    @GetMapping({ "/showProfile" })
    public String showProfile(Model model) {

        model.addAttribute("usuario", Utilidades.user);
        return "showProfile";
    }

}
