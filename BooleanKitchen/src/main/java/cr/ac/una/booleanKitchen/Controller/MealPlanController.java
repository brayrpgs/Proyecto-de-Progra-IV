package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.domain.MealPlan;
import cr.ac.una.booleanKitchen.service.IServiceMealplan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BrayRPGs
 */
@Controller
@RequestMapping("/mealplan")
public class MealPlanController {
    
    @Autowired
    private IServiceMealplan jpa;
    
    @GetMapping("/pane")
    public String pane(Model model){
        //datos paginados
        Page<MealPlan> page = jpa.getAll(0);
        //saco los registros de la pagina actual
        model.addAttribute("data", page.toList());
        //la pagina actual
        model.addAttribute("page", page);
        //pregunta cuantas paginas hay
        model.addAttribute("totalPages", page.getTotalPages());
        //indico la pagina actual
        model.addAttribute("numPage", 0);
        //verifico que no este vacio el registro
        model.addAttribute("if", !page.isEmpty());
        //para contabilizar las paginas que hay
        List<Integer> pageableCollection = new ArrayList<>();
        for (int i = 0; i < page.getTotalPages(); i++) {
            pageableCollection.add(i);
        }
        model.addAttribute("pageableCollection", pageableCollection);
        return "mealplan/pane";
    }
    
    @GetMapping("/pane/{numPage}")
    public String pane(Model model, @PathVariable Integer numPage){
        
        System.out.println(numPage);
        //traigo una pagina
        Page<MealPlan> page = jpa.getAll(numPage);
        //indico la pagina actual
        model.addAttribute("numPage", numPage);
        //saco los registros de la pagina actual
        model.addAttribute("data", page.toList());
        //verifico que no este vacio el registro
        model.addAttribute("if", !page.isEmpty());
        //pregunta cuantas paginas hay
        model.addAttribute("totalPages", page.getTotalPages());
        //la pagina actual
        model.addAttribute("page", page);
        //para contabilizar las paginas que hay
        List<Integer> pageableCollection = new ArrayList<>();
        for (int i = 0; i < page.getTotalPages(); i++) {
            pageableCollection.add(i);
        }
        model.addAttribute("pageableCollection", pageableCollection);
        return "mealplan/pane";
    }
    
    @GetMapping("/insert")
    public String insert(){
        return "mealplan/insert";
    }
    
    @PostMapping("/insertMealplan")
    public String save(@RequestBody MealPlan m){
        System.out.println(m.toString());
        jpa.save(m);
        return "redirect:/mealplan/pane";
    }
    
    @PostMapping("/deleteMealplan")
    public String save(@RequestParam Integer Id){
        jpa.delete(Id);
        return "redirect:/mealplan/pane";
    }
    
}
