/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;


import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.service.CategoryServiceUpdate;
import cr.ac.una.booleanKitchen.service.ICategoryService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josue
 */
@Controller
@RequestMapping("/categoriaIndex")
public class CategoryController {
      @Autowired
    private ICategoryService catRepo;
    public static String urlAct;

    public static String getUrlAct() {
        return urlAct;
    }

    public static void setUrlAct(String urlAct) {
        CategoryController.urlAct = urlAct;
    }
    
    
     
    @GetMapping({"/categoria"})
    public String categoryMain(Model model){
      
       // model.addAttribute("uploadsDir", uploadsDir);
       // model.addAttribute("ListaCategory", new CategoryServiceUpdate().obtenerCat());
        model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
        
        
        return "category/category_View";
    }
    
    @GetMapping({"/viewTable"})
    public String tableView(@PageableDefault(size = 4, page = 0) Pageable pageable,Model model){
     
        refreshTable(pageable, model);
        
        return "category/viewTable";
    }
    
    
     @PostMapping({"/returnMainCat"})
    public String catExit(Model model,@PageableDefault(size = 4, page = 0) Pageable pageable){
      
       // model.addAttribute("uploadsDir", uploadsDir);
       // model.addAttribute("ListaCategory", catRepo.getCategorias());
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
         //refreshTable(pageable, model);
        
        return "redirect:/categoriaIndex/categoria";
    }

  
    
    @PostMapping({"/agregarCat"})
    public String createCat(@RequestParam("img")MultipartFile file,@ModelAttribute Category cat,@RequestParam("Visibilidad")String status,
            @RequestParam("notaAdmin")String notaAdmin,Model model){
          LocalDate local= LocalDate.now();
          
            if(new CategoryServiceUpdate().validation(cat, status)){
            
            if(!file.isEmpty()){
                
          
             
              cat=CreateCategory( cat,  local, status, notaAdmin, file);
              
              int statusData=catRepo.guardar(cat);
                controlStatus(statusData, model,cat);
              
                if(statusData<0){
                    return "category/category_View";
                }else{
                 
                    new CategoryServiceUpdate().insertImg(file, cat.getUrlImagen());
                   
                   
                }
              
            }else{
                
                 showMessage ("adMessage", "file-regular","No se ha Seleccionado una imagen. por favor ingresa una.",model); 
               modelAction(model, cat);
            }
            
        }else{
               showMessage ("adMessage", "pen-to-square-regular","Faltan datos por llenar",model);      
                modelAction(model, cat);
            
            
        }
            
             model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
        return "category/category_View";
    }
 
   private void controlStatus(int status, Model model, Category cat) {
    switch (status) {
        case -1 -> {
            showMessage ("Error", "circle-xmark-regular","Identificador está siendo utilizado actualmente por otra categoría.",model);
            model.addAttribute("dateElement", cat);
                // Se agrega un break para salir del switch después de cada caso
            }
        case -2 -> {
            showMessage ("Error", "circle-xmark-regular","Nombre está siendo utilizado actualmente por otra categoría.",model);
            model.addAttribute("dateElement", cat);
            }
        case 1 -> {
            showMessage ("success", "square-check-regular","Categoría guardada correctamente.",model);
            }
        default -> {
             showMessage ("Error", "circle-xmark-regular","Error de conexion.",model);
              model.addAttribute("dateElement", cat);}
    }
  
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
}

    
   
    
    private Category CreateCategory(Category cat, LocalDate local,String status,String notaAdmin,MultipartFile file){
              cat.setId(0);
              cat.setCatVisible(new CategoryServiceUpdate().getVisibleCat(status));
              cat.setQuantity(0);
              cat.setUrlImagen(new CategoryServiceUpdate().getRoute(file));
              cat.setDate(local);
              cat.setComment(notaAdmin);//commit: 
              cat.setCreateBy("Josue Porras");
              
        return cat;
    }
    

    @GetMapping({"/ModicarDatos/{idCode}"})
    
    public String modifyCategory(@PathVariable String idCode, Model model){
        
           Category cat=catRepo.getCategory(idCode);
           model.addAttribute("selectedCat", cat);
           setUrlAct(cat.getUrlImagen());
            model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
         
       return "category/category_View";
    }
    
    
    @PostMapping({"/TerminarModificaciones"})
    public String ModifyTerm(@PageableDefault(size = 4, page = 0) Pageable pageable,@RequestParam("img")MultipartFile file,@ModelAttribute Category cat,@RequestParam("Visibilidad")String status,
            @RequestParam("notaAdmin")String notaAdmin,Model model){
        LocalDate local= LocalDate.now();
            
            if(new CategoryServiceUpdate().validation(cat, status)){
            
              cat.setCatVisible(new CategoryServiceUpdate().getVisibleCat(status));
              cat.setQuantity(0);
              
              cat.setDate(local);
              cat.setComment(notaAdmin);
              cat.setCreateBy("Josue Porras");
              
              if(file.isEmpty()){
                  cat.setUrlImagen(getUrlAct());
              if(catRepo.modifyCat(cat)){
                  
              }     
              }else{
               
              cat.setUrlImagen(new CategoryServiceUpdate().getRoute(file));    
             
              
              
              if(catRepo.modifyCat(cat)){
                   new CategoryServiceUpdate().insertImg(file, cat.getUrlImagen());
                   new CategoryServiceUpdate().deleteImage(getUrlAct());
                   
              }
              }
               
                showMessage("success", "square-check-regular", "Se ha modificado con exito la Categoría", model);
               setUrlAct("");
            }else{
                 showMessage("adMessage", "pen-to-square-regular","Faltan datos por llenar, error al modificar." , model);
                   model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
                 cat.setUrlImagen(getUrlAct());
                 model.addAttribute("selectedCat", cat);
                 
                 
                 return "category/category_View";
            }
              
       
           //model.addAttribute("ListaCategory", catRepo.getCategorias());
            refreshTable(pageable, model);
        
         return "category/category_View";
    }
    
    
    //11 05 0202 imagen.png
     @GetMapping({"/DeleteCategory/{idCode}/{url}"})
     
     public String EliminarCategory(@PathVariable String idCode,@PathVariable String url, Model model,@PageableDefault(size = 4, page = 0) Pageable pageable){
      
         if(catRepo.DeleteCategory(idCode)){
           
            if( !new CategoryServiceUpdate().deleteImage(url)){
                showMessage("Error", "circle-xmark-regular", "Error de conectividad", model);
            }
             showMessage("success", "square-check-regular", "Categoría eliminada con éxito.", model);
         }
         
           //model.addAttribute("ListaCategory", catRepo.getCategorias());
           refreshTable(pageable, model);
         
         return "category/tableCategory";
     }
     
     @GetMapping({"/returnAddCat"})
     public String returnAddCat (Model model){
          model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
         
         return "category/category_View" ;
     }
         
     @GetMapping({"/exitCat"})
     public String exiCat(Model model){
         return "index";
     }
    
    

    public void modelAction (Model model, Category cat){
        if (cat !=null){
         model.addAttribute("dateElement", cat);   
        }
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
          model.addAttribute("ListaCategory", catRepo.getCategorias());
    }
    
   
    public void showMessage (String type, String imgSelect,String message,Model model){
        model.addAttribute("class", type);
        model.addAttribute("type", imgSelect);
        model.addAttribute("message", message);
    }
    
    
    public void refreshTable(Pageable pageable, Model model) {
        Page<Category> page = catRepo.getPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        // model.addAttribute("ListaCategory", catRepo.getCategorias());
        model.addAttribute("numberOfElements", page.getNumberOfElements());
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

        List<Integer> pageSizeOptions = Arrays.asList( 4,10, 20, 50, 100);
        model.addAttribute("pageSizeOptions", pageSizeOptions);

    }
    
}


