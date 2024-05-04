/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.service.IngredientService;

@Controller
public class IngredientController {

    /*
     * /ingredientes es para llegar al lugar donde se muestra la tabla con el
     * contenido de la base de datos.
     */

    @GetMapping({ "/ingredientes" })
    public String ingredientIndex(Model model) {

        LinkedList<Ingredient> ingredient = IngredientService.getIngredients();
        model.addAttribute("data", new Ingredient());
        model.addAttribute("db", ingredient);

        return "ingredient/ingredient_index";

    }

    /*
     * El metodo siguiente funciona para redireccionar al administrador y devolver
     * una vista que agrega ingredientes
     */

    @GetMapping({ "ingredientes/agregarIngrediente" })
    public String ingredientAdd(Model model) throws SQLException {

        LinkedList<Category> categories = IngredientService.getCategory();
        model.addAttribute("categories", categories);

        return "ingredient/ingredient_add";

    }

    /*
     * El postMapping de get es para obtener los datos enviados desde el
     * formulario.
     */

    @PostMapping("/Get")
    public String getInformationFromForm(@RequestParam("imageInput") MultipartFile img,
            @ModelAttribute Ingredient myIngredient, Model model) throws SQLException {

       

        if (IngredientService.duplicatedIdIngredient(myIngredient)) {

            model.addAttribute("message",
                    "El código del ingrediente ingresado ya se encuentra registrado \n" +
                            "!Por favor intenta nuevamente con otro código!");
            return "ingredient/messageIngredient";

        } else if (IngredientService.duplicatedNameIngredient(myIngredient)) {

            model.addAttribute("message",
                    "El nombre del ingrediente ingresado ya se encuentra registrado \n" +
                            "!Por favor intenta nuevamente con otro nombre!");
            return "ingredient/messageIngredient";

        } else {

            try {

                if (myIngredient.getDescription().trim().equals("")) {
                    myIngredient.setDescription("Sin descripción");
                }

                String imageToIngredient = new IngredientService().uploadImage(img);
                myIngredient.setImage(imageToIngredient);

                model.addAttribute("message",
                        "!El ingrediente ingresado se insertó correctamente!");
                IngredientService.addIngredient(myIngredient);

            } catch (SQLException ex) {
                Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE,
                        null, ex);

            }

            return "ingredient/messageIngredient";

        }

    }

    /*
     * El postMapping de delete es para agarrar el codigo del ingrediente que se
     * desea eliminar.
     */

    @PostMapping("/delete")
    public String Delete(@RequestParam("code") String code,@RequestParam("image") String url, Model model) {
        
        try {
            IngredientService.deleteIngredient(code);
            new IngredientService().deleteImage(url);
        } catch (SQLException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        LinkedList<Ingredient> aux = IngredientService.getIngredients();
        model.addAttribute("db", aux);

        return "ingredient/resultTable";

    }

    /*
     * El postMapping de update es para agarrar el codigo del ingrediente que se
     * desea actualizar.
     */

    @GetMapping("ingredientes/actualizarIngrediente")
    public String Update(@RequestParam("code") String code, Model model) throws SQLException {

        Ingredient data = IngredientService.getIngredientByCode(code);
        LinkedList<Category> categories = IngredientService.getCategory();
        model.addAttribute("ingredient", data);
        model.addAttribute("categories", categories);

        return "ingredient/ingredient_update";

    }

    @PostMapping("/updateIngredient")
    public String UpdateIngredient(@RequestParam("imageInput") MultipartFile img,
        @ModelAttribute Ingredient myIngredient, Model model) throws SQLException {

        if (myIngredient.getDescription().trim().equals("")) {
            myIngredient.setDescription("Sin descripción");
        }
        //Recuperar el nombre de la imagen vieja
        Ingredient aux = IngredientService.getIngredientByCode(myIngredient.getCode());
        //Setear el nuevo nombre de la imagen
        String imageToIngredient = new IngredientService().uploadImage(img);
        myIngredient.setImage(imageToIngredient);

        //Actualizar el ingrediente
        if(IngredientService.UpdateIngredient(myIngredient)){

            //Eliminar imagen vieja
            new IngredientService().deleteImage(aux.getImage());

            //Inserto la nueva imagen
            myIngredient.setImage(imageToIngredient);
            model.addAttribute("message", "!El ingrediente ingresado se actualizo correctamente!");
            return "ingredient/messageIngredient";

        } else {

            model.addAttribute("message", "El nombre del ingrediente ingresado ya se encuentra registrado \n" +
                    "!Por favor intenta nuevamente con otro nombre!");
            return "ingredient/messageIngredient";

        }

    }

    @PostMapping("/show")
    public String showIngredient(@RequestParam ("code") String code, Model model) throws SQLException{

        Ingredient myIngredient = IngredientService.getIngredientByCode(code);
        LinkedList<Category> categories = IngredientService.getCategory();

        //Obtener el nombre de la categoria del producto
        for (Category category : categories) {
            
            if(myIngredient.getCategory() == category.getId()){
                
                myIngredient.setCategoryName(category.getNameCategory());
                break;
            }

        }

        model.addAttribute("Ingredient", myIngredient);

        return "ingredient/messageIngredient";
    }

}
