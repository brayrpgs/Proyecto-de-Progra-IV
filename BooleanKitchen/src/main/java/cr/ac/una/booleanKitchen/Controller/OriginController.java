/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;


import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.service.IOriginService;
import cr.ac.una.booleanKitchen.service.OriginServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
 * @author Ceasa
 */
@Controller
@RequestMapping("/c_origen")
public class OriginController {

    @Autowired
    private IOriginService orgServ;

    private int id;
    String aux;

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
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

    @GetMapping("/indexorigin")
    public String CrudOrigin() {
        return "origin/indexorigin";
    }

    @GetMapping("/indexorigin/Create")
    public String CreateOrigin(Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {
        findAll(pageable,model);
        return "origin/origingeneral";
    }

    @PostMapping({ "/CrearOrigen" })
    public String CrearOrigen(@RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("identificador") String identificador,
            @RequestParam("country") String idRecipeList,
            @RequestParam("img") MultipartFile file,
            @RequestParam("continent") String continent,
            @RequestParam("taste") String taste,
            Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {
        if (!file.isEmpty()) {
            String fileS = new OriginServices().getRoute(file);
            Origin org = new Origin(0, name, description, identificador, idRecipeList, fileS, continent, taste);
            System.out.println("Antes del try");
            try {
                orgServ.crearOrigen(org);
                System.out.println("Pasee");
                // mensaje
                model.addAttribute("mensaje", "Guardado con exito");

                new OriginServices().insertImg(file, fileS);
            } catch (Exception ex) {
                model.addAttribute("mensaje", "Faltan datos");
                Logger.getLogger(OriginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            model.addAttribute("mensaje", "Falta imagen");

        }
        findAll(pageable,model);

        return "origin/origingeneral";
    }

    @GetMapping("/CrearOrigen")
    public String ReadOrigin(Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {
        findAll(pageable,model);
        return "origin/origingeneral";
    }

    @GetMapping("indexorigin/Update")
    public String UpdateOrigin(Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {
        findAll(pageable,model);
        return "origin/form_originupdate";
    }

    @GetMapping("/DeleteOrigen/{identificador}/{url}")
    public String DeleteOrigin(
            @PathVariable String identificador, @PathVariable String url, Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {

        try {
            orgServ.EliminarOrigen(orgServ.getOrigen(identificador).getId());
            new OriginServices().deleteImage(url);
            model.addAttribute("mensaje", "Borrado con exito");

        } catch (Exception ex) {
            Logger.getLogger(OriginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        findAll(pageable,model);
        return "originAfterDelete";
    }

    @GetMapping("/SeleccionarOrigen/{Origen}")
    public String getOrigin(@PathVariable String Origen, Model model) {
        Origin org = orgServ.getOrigen(Origen);
        setId(org.getId());
        setAux(org.getIdImg());
       
        model.addAttribute("selectOrigin", org);

        return "origin/origingeneral";
    }

    @PostMapping({ "/updateOrigen" })
    public String updateOrigen(@RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("identificador") String identificador,
            @RequestParam("country") String idRecipeList,
            @RequestParam("img") MultipartFile file,
            @RequestParam("continent") String continent,
            @RequestParam("taste") String taste,
            Model model,@PageableDefault(size = 4, page = 0) Pageable pageable) {
        String fileS = (!file.isEmpty()) ? new OriginServices().getRoute(file) : getImage();
        if (!file.isEmpty()) {
            new OriginServices().deleteImage(getImage());
            new OriginServices().insertImg(file, fileS);
        }
        Origin org = new Origin(getId(), name , description, identificador, idRecipeList,fileS,continent, taste);
        orgServ.crearOrigen(org);

        setId(0);
        setImage("");

        findAll(pageable,model);
        return "origin/origingeneral";
    }

	public void findAll(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model) {
		Page<Origin> page = orgServ
				.getPageOrigin(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
		
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
		
		
		List<Integer> pageSizeOptions = Arrays.asList(10,20, 50, 100);
		model.addAttribute("pageSizeOptions", pageSizeOptions);
	}
}
