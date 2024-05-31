package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import cr.ac.una.booleanKitchen.service.IngredientService;
import cr.ac.una.booleanKitchen.service.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/c_ingrediente")
public class IngredientController {

    @Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private IngredientService IngredientService;

    @GetMapping(value = "/ingredientes")
	public String findAll(@PageableDefault(size = 4, page = 0) Pageable pageable, Model model) {
		Page<Ingredient> page = ingredientRepository
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        
		model.addAttribute("page", page);
		var totalPages = page.getTotalPages();
		var currentPage = page.getNumber();

		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);

		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}

			model.addAttribute("pageNumbers", pageNumbers);
		}

        

		return "ingredient/ingredient_index";
	}

    /*
     * El metodo siguiente funciona para redireccionar al administrador y devolver
     * una vista que agrega ingredientes
     */

    @GetMapping({ "ingredientes/agregarIngrediente" })
    public String ingredientAdd(Model model) throws SQLException {

        LinkedList<Category> categories = IngredientService.getCategories();
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

        Optional<Ingredient> existingIngredientByCode = IngredientService.findByIdentificador(myIngredient.getCode());
		Optional<Ingredient> existingIngredientByNOMBRE = IngredientService.findByNOMBRE(myIngredient.getName());

        if (existingIngredientByCode.isPresent()) {

            model.addAttribute("message",
                    "El código del ingrediente ingresado ya se encuentra registrado \n" +
                            "!Por favor intenta nuevamente con otro código! en prueba");
            return "ingredient/messageIngredient";

        } else if (existingIngredientByNOMBRE.isPresent()) {

            model.addAttribute("message",
                    "El nombre del ingrediente ingresado ya se encuentra registrado \n" +
                            "!Por favor intenta nuevamente con otro nombre!");
            return "ingredient/messageIngredient";

        } else {

            

                if (myIngredient.getDescription().trim().equals("")) {
                    myIngredient.setDescription("Sin descripción");
                }

                String imageToIngredient = IngredientService.uploadImage(img);
                myIngredient.setImage(imageToIngredient);

                model.addAttribute("message",
                        "!El ingrediente ingresado se insertó correctamente!");
                IngredientService.saveIngredient(myIngredient);

            

            return "ingredient/messageIngredient";

        }

    }

    /*
     * El postMapping de delete es para agarrar el codigo del ingrediente que se
     * desea eliminar.
     */

    @PostMapping("/delete")
    public String Delete(@PageableDefault(size = 4, page = 0) Pageable pageable
        ,@RequestParam("code") String code,@RequestParam("image") String url, Model model) {
        
        
            IngredientService.deleteIngredientByIDENTIFICADOR(code);
            IngredientService.deleteImage(url);
        

        Page<Ingredient> page = ingredientRepository
        				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

		model.addAttribute("page", page);
		var totalPages = page.getTotalPages();
		var currentPage = page.getNumber();

		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);

		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}

			model.addAttribute("pageNumbers", pageNumbers);
		}

        return "ingredient/resultTable";

    }

    /*
     * El postMapping de update es para agarrar el codigo del ingrediente que se
     * desea actualizar.
     */

    @GetMapping("ingredientes/actualizarIngrediente")
    public String Update(@RequestParam("code") String code, Model model) throws SQLException {

        Ingredient data = IngredientService.findByIdentificador(code).get();
        LinkedList<Category> categories = IngredientService.getCategories();
        model.addAttribute("ingredient", data);
        model.addAttribute("categories", categories);

        return "ingredient/ingredient_update";

    }

    @PostMapping("/updateIngredient")
    public String UpdateIngredient(@RequestParam("imageInput") MultipartFile img,
        @ModelAttribute Ingredient ingredient, Model model) throws SQLException {

		Ingredient myIngredient = IngredientService.findByIdentificador(ingredient.getCode()).get();
		
		Optional<Ingredient> existingIngredientByName = IngredientService.findByNOMBRE(ingredient.getName());
        
		if(existingIngredientByName.isPresent() && !existingIngredientByName.get().equals(myIngredient)){

			model.addAttribute("message", "El nombre del ingrediente ingresado ya se encuentra registrado \n" +
                    "!Por favor intenta nuevamente con otro nombre!");
            return "ingredient/messageIngredient";

		} else {

			//Obtengo el url del ingrediente viejo
			String oldImageName = myIngredient.getImage();
			//Elimino la imagen de la carpeta
			IngredientService.deleteImage(oldImageName);

			//Seteo los nuevos datos de mi registro 
            myIngredient.setName(ingredient.getName());
            myIngredient.setQuantity(ingredient.getQuantity());
            myIngredient.setWeight(ingredient.getWeight());
            myIngredient.setCategory(ingredient.getCategory());
            myIngredient.setCalories(ingredient.getCalories());

            if (ingredient.getDescription().trim().equals("")) {
                myIngredient.setDescription("Sin descripción");
            } else {
                myIngredient.setDescription(ingredient.getDescription());
            }

            String newRouteImg = IngredientService.uploadImage(img);
			myIngredient.setImage(newRouteImg);

			//Actualizo el registro
			IngredientService.saveIngredient(myIngredient);

            model.addAttribute("message", "!El ingrediente ingresado se actualizo correctamente!");
            return "ingredient/messageIngredient";

		}

    }

    @PostMapping("/show")
    public String showIngredient(@RequestParam ("code") String code, Model model) throws SQLException{

        Ingredient myIngredient = IngredientService.findByIdentificador(code).get();
        LinkedList<Category> categories = IngredientService.getCategories();

        //Obtener el nombre de la categoria del producto
        for (Category category : categories) {
            
            if(myIngredient.getCategory() == category.getId()){
                
                myIngredient.setCategoryName(category.getName());
                break;
            }

        }

        model.addAttribute("Ingredient", myIngredient);

        return "ingredient/messageIngredient";
    }

}