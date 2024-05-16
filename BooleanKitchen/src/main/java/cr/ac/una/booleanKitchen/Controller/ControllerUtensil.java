/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;


import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Utensil;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import cr.ac.una.booleanKitchen.service.IUtensilioService;
import cr.ac.una.booleanKitchen.service.UtensilioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ceasar Fecha de Creación:5/10/2024
 *
 * Fecha de Modificacion:6/10/2024
 */
@Controller
@RequestMapping("/c_utensilio")
public class ControllerUtensil {

    private int id;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Autowired
    private ICategoryService catSer;
    @Autowired
    private IUtensilioService utnServ;

    @GetMapping("/index")
    public String buscar() {
        return "index";
    }

    @GetMapping("/utensilioindex")
    public String CrudUtensilio(Model model) {
        model.addAttribute("selectCat", catSer.getCategory());
        List<Utensil> utnl = utnServ.leerUtensilio();
        model.addAttribute("TablaUtensilio", utnl);
        return "utensilio/utensilioindex";
    }

    @PostMapping("/guardarUtensilio")
    public String crearUtensilio(@RequestParam("identificador") String identificador,
            @RequestParam("nombre") String nombre,
            @RequestParam("material") String material,
            @RequestParam("categoria") String categoria,
            @RequestParam("rutaDeImagen") MultipartFile file,
            @RequestParam("precio") float precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("descripcion") String descripcion,
            Model model) {

        if (!file.isEmpty()) {
            String fileS = new UtensilioService().getRoute(file);
            Category categoriaFinal = catSer.getCategoryOnly(categoria);

            Utensil utn = new Utensil(0, identificador, nombre, material, categoriaFinal, fileS, precio, cantidad, descripcion);

            try {
                utnServ.crearUtensilio(utn);
                System.out.println("copia" + utn.toString());
                //mensaje
                model.addAttribute("mensaje", "Guardado con éxito");

                new UtensilioService().insertImg(file, fileS);
            } catch (Exception e) {
                model.addAttribute("mensaje", "Faltan datos");
            }

        } else {
            model.addAttribute("mensaje", "Falta imagen");

        }
        List<Utensil> utnl = utnServ.leerUtensilio();
        model.addAttribute("TablaUtensilio", utnl);

        return "utensilio/utensilioindex";
    }

    @GetMapping("/modificarUtensilio/{identificador}")
    public String modificarUtensilio(@PathVariable String identificador,
            Model model) {
        model.addAttribute("selectCat", catSer.getCategory());

        Utensil utn = utnServ.getUtensilio(identificador);
        setId(utn.getId());
        setImage(utn.getRutaDeImagen());
        //selectUtensil

        model.addAttribute("selectUtensil", utn);

        List<Utensil> utnl = utnServ.leerUtensilio();
        model.addAttribute("TablaUtensilio", utnl);

        return "utensilio/utensilioindex";
    }

    @PostMapping("/updateUtensilio")
    public String updateUtensilio(@RequestParam("identificador") String identificador,
            @RequestParam("nombre") String nombre,
            @RequestParam("material") String material,
            @RequestParam("categoria") String categoria,
            @RequestParam("rutaDeImagen") MultipartFile file,
            @RequestParam("precio") float precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("descripcion") String descripcion,
            Model model) {

        String fileS = (!file.isEmpty()) ? new UtensilioService().getRoute(file) : getImage();
        if(!file.isEmpty()){
            new UtensilioService().deleteImage(getImage());
            new UtensilioService().insertImg(file, fileS);
        }
        Category categoriaFinal = catSer.getCategoryOnly(categoria);
        Utensil utn = new Utensil(getId(), identificador, nombre, material, categoriaFinal, fileS, precio, cantidad, descripcion);

        utnServ.crearUtensilio(utn);

        setId(0);
        setImage("");
        
        model.addAttribute("selectCat", catSer.getCategory());
        List<Utensil> utnl = utnServ.leerUtensilio();
        model.addAttribute("TablaUtensilio", utnl);

        return "utensilio/utensilioindex";
    }
    
    @GetMapping("/BorrarUtensilio/{identificador}/{rutaDeImagen}")
    public String eliminarUtensilio(@PathVariable String identificador,@PathVariable String rutaDeImagen,Model model){
        
        utnServ.EliminarUtensilio(utnServ.getUtensilio(identificador).getId());
        new UtensilioService().deleteImage(rutaDeImagen);
        model.addAttribute("selectCat", catSer.getCategory());
        List<Utensil> utnl = utnServ.leerUtensilio();
        model.addAttribute("TablaUtensilio", utnl);
        
    
        return "utensilio/afterdeleteutensil";
    }

}