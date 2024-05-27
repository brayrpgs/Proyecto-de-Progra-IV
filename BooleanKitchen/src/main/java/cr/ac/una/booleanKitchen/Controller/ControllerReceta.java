
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Logica.LogicaReceta;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            Model model) {

        LinkedList<Utensil> listaUtensilios = new LinkedList<>();
        for (String utensil : Utensilios) {
            Utensil u = new Utensil();
            u.setIdentificador(utensil);
            listaUtensilios.add(u);
            System.out.println(u.getIdentificador());
        }

        System.out.println("\nIngredientes:\n");
        LinkedList<Ingredient> listaIngredientes = new LinkedList<Ingredient>();
        for (String ingre : ingredientes) {
            Ingredient i = new Ingredient();
            i.setCode(ingre);
            listaIngredientes.add(i);
            System.out.println(i.getCode());
        }

        Category cat = categoryService.getCategoryOnly(categoria);

        Origin orige = new Origin();
        orige.setId(1);

        if (new PreparationService().validation(dificultad, tiempo, advertencias)) {

            Utilidades.preparacion.setIdSerial("PREP-" + dificultad + LocalDateTime.now());
            Utilidades.preparacion.setDifficulty(dificultad);
            try {
                Utilidades.preparacion.setTime(LocalTime.parse(tiempo, DateTimeFormatter.ofPattern("HH:mm")));
            } catch (Exception e) {

                // Remover todos los caracteres que no sean números
                tiempo = tiempo.replaceAll("[^\\d]", "");

                // Asegurarse de que el tiempo tenga al menos 4 caracteres, rellenando con ceros
                // a la izquierda si es necesario
                tiempo = String.format("%04d", Integer.parseInt(tiempo));

                // Insertar ":" en la posición adecuada
                tiempo = tiempo.substring(0, 2) + ":" + tiempo.substring(2);

                // Recortar a 5 caracteres si el tiempo es demasiado largo
                if (tiempo.length() > 5) {
                    tiempo = tiempo.substring(0, 5);
                }

                Utilidades.preparacion.setTime(LocalTime.parse(tiempo, DateTimeFormatter.ofPattern("HH:mm")));
            }

            Utilidades.preparacion.setWarnings(advertencias);
            Utilidades.preparacion.setNoteAuthor(nota);
    
            Recipe receta = new Recipe(0, "REC-" + nombre + LocalDateTime.now(), nombre, orige, cat, 0,
                    ("REC-" + nombre + LocalDateTime.now() + img_receta.getOriginalFilename())
                            .replaceAll("[^a-zA-Z0-9.-]", "-"),
                    Utilidades.user, 0);
            insertImg(img_receta, receta.getImage());
            Utilidades.preparacion
                    .setRouteImg((Utilidades.preparacion.getIdSerial() + img_preparacion.getOriginalFilename())
                            .replaceAll("[^a-zA-Z0-9.-]", "-"));
            insertImg(img_preparacion, Utilidades.preparacion.getRouteImg());

            Utilidades.preparacion.setIdRecipe(receta);
            Utilidades.preparacion.setPreparationList(Utilidades.stepsLis);
            // prep.setPreparationList(new PreparationService().getStepsByPrep(prep));

            prepService.guardar(Utilidades.preparacion);

            Utilidades.stepsLis.clear(); // Limpiamos las listas de pasos
            Utilidades.preparacion = new Preparation();
            // recetaService.Guardar(receta);
        } else {
            // MENSAJE DE ERROR
        }




        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "redirect:/c_inicio/index";
    }

    @GetMapping("/verDetallesDeReceta")
    public String verDetallesDeReceta(@RequestParam("id") String identificador, Model model) {
        System.out.println("\n\nIdReceta: " + identificador);
        Recipe re = recetaService.findByIdentificador(identificador);
        if(re != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            re.getPreparacion().setTimeStr(re.getPreparacion().getTime().format(formatter));
            model.addAttribute("receta", re);
        }
        
        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "detallesRecetas";
    }

    @GetMapping("/editarReceta")
    public String editar(@RequestParam("identificadorReceta") String identificador, Model model) {

        System.out.println("Receta a editar: " + identificador);
        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "redirect:/c_inicio/index";
    }

    @GetMapping("/eliminarReceta")
    public String eliminar(@RequestParam("identificadorReceta") String identificador, Model model) {

        Recipe receta = recetaService.findByIdentificador(identificador);
        if (receta != null) {
            recetaService.eliminar(receta);
        } else {
            System.out.println("No se puedo eliminar la receta con el identificador: " + identificador);
        }

        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "redirect:/c_inicio/index";
    }


    @GetMapping("/buscarReceta")
    public String verRecetas(@PageableDefault(size = 4, page = 0) Pageable pageable, @RequestParam("buscarReceta") String busqueda,Model model) {
        model.addAttribute("usuario", Utilidades.user);

        //Obtiene la paginación de la receta
        LogicaReceta.paginacionRecetas(model, busqueda, pageable, recetaService);



        model.addAttribute("busquedaReceta", busqueda);
        //Trae todos los datos necesarios para agregar una nueva receta
        LogicaReceta.datosParaAgregarReceta(model, categoryService);
        return "verRecetas";
    }



    public boolean insertImg(MultipartFile file, String route) {
        if (!file.isEmpty()) {
            try {

                Resource resource = new ClassPathResource("");
                String absolutePath = resource.getFile().getAbsolutePath();

                byte[] bytes = file.getBytes();
                Path path = Paths
                        .get(absolutePath.replace("\\target\\classes", "\\src\\main\\resources\\static\\assets") + "/"
                                + route.replaceAll("[^a-zA-Z0-9.-]", "-"));
                Files.write(path, bytes);
                return true;
            } catch (IOException e) {
                // por si da error
                return false;
            }
        }
        return false;
    }
}
