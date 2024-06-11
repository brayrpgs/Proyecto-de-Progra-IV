package cr.ac.una.booleanKitchen.Logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
//import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.domain.Recipe;
import cr.ac.una.booleanKitchen.domain.Utensil;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IRecetaService;
//import cr.ac.una.booleanKitchen.service.IngredientService;

public class LogicaReceta {
    public static void datosParaAgregarReceta(Model model, ICategoryService categoryService) {
        LinkedList<Origin> origenes = new LinkedList<>();
        origenes.add(new Origin(1, "Mexicana", "", "UTE-001", "", "receta.jpeg", "", ""));

        //LinkedList<Ingredient> ingredients = IngredientService.getIngredients();

        LinkedList<Utensil> utensils = new LinkedList<>();
        utensils.add(new Utensil(25, "UTE-001", "Cuchara", "", null, "", 0, 0, ""));

        model.addAttribute("categorias", categoryService.getCategory());
        model.addAttribute("origen", origenes);
        // model.addAttribute("utensilios", utensils);
        // model.addAttribute("ingredientes", ingredients);
    }

    public static void paginacionRecetas(Model model, String busqueda, Pageable pageable,
            IRecetaService recetaService) {
        List<Integer> pageSizeOptions = Arrays.asList(4, 8, 10, 20, 50, 100);
        model.addAttribute("pageSizeOptions", pageSizeOptions);

        Page<Recipe> commentPage = null;
        if (busqueda.trim().isEmpty()) {
            commentPage = recetaService.paginacionRecetas(pageable);
            if (commentPage.getContent().isEmpty() && pageable.getPageNumber() > 0) {
                commentPage = recetaService.paginacionRecetas(
                        PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()));
            }
        } else {
            commentPage = recetaService.findByNameContainingIgnoreCase(busqueda, pageable);
            if (commentPage.getContent().isEmpty() && pageable.getPageNumber() > 0) {
                commentPage = recetaService.paginacionRecetas(
                        PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()));
            }
        }

        // for(Recipe recipe : commentPage.getContent()){
        // recipe.setAntiguedad(calcularAntiguedad());
        // }
        model.addAttribute("page", commentPage);
        var totalPages = commentPage.getTotalPages();
        var currentPage = commentPage.getNumber();
        var start = Math.max(1, currentPage);
        var end = Math.min(currentPage + 5, totalPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                pageNumbers.add(i);
            }

            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
