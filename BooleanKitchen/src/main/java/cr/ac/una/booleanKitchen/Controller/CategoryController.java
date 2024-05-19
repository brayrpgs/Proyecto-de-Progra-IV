<<<<<<< HEAD
package cr.ac.una.booleanKitchen.Controller;



import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.service.CategoryService;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;


import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.service.CategoryServiceUpdate;
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
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
<<<<<<< HEAD
    //para permanencia de las url de imagenes a la hora de modificar
=======

>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
    public static void setUrlAct(String urlAct) {
        CategoryController.urlAct = urlAct;
    }
    
    
     
    @GetMapping({"/categoria"})
    public String categoryMain(Model model){
      
       // model.addAttribute("uploadsDir", uploadsDir);
       // model.addAttribute("ListaCategory", new CategoryServiceUpdate().obtenerCat());
<<<<<<< HEAD
        model.addAttribute("Labels", new CategoryService().getListLabel());
=======
        model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
        
        
        return "category/category_View";
    }
    
    @GetMapping({"/viewTable"})
    public String tableView(@PageableDefault(size = 4, page = 0) Pageable pageable,Model model){
<<<<<<< HEAD
        //realizo paginacion
=======
     
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
        refreshTable(pageable, model);
        
        return "category/viewTable";
    }
    
    
     @PostMapping({"/returnMainCat"})
    public String catExit(Model model,@PageableDefault(size = 4, page = 0) Pageable pageable){
      
       // model.addAttribute("uploadsDir", uploadsDir);
       // model.addAttribute("ListaCategory", catRepo.getCategorias());
<<<<<<< HEAD
         model.addAttribute("Labels", new CategoryService().getListLabel());
=======
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
         //refreshTable(pageable, model);
        
        return "redirect:/categoriaIndex/categoria";
    }

  
    
    @PostMapping({"/agregarCat"})
    public String createCat(@RequestParam("img")MultipartFile file,@ModelAttribute Category cat,@RequestParam("Visibilidad")String status,
            @RequestParam("notaAdmin")String notaAdmin,Model model){
          LocalDate local= LocalDate.now();
<<<<<<< HEAD
          //validacion de datos vacios
            if(new CategoryService().validation(cat, status)){
            
            if(!file.isEmpty()){
            //creo la categoria
              cat=CreateCategory( cat,  local, status, notaAdmin, file);
              //espero la respuesta de la Base de datos 
              int statusData=catRepo.guardar(cat);
                controlStatus(statusData, model,cat);
              //si ocurre un error en el camino
                if(statusData<0){
                    return "category/category_View";
                }else{
                 //si todo bien, ingreso la imagen a la carpeta respectiva
                    new CategoryService().insertImg(file, cat.getImage());
=======
          
            if(new CategoryServiceUpdate().validation(cat, status)){
            
            if(!file.isEmpty()){
                
          
             
              cat=CreateCategory( cat,  local, status, notaAdmin, file);
              
              int statusData=catRepo.guardar(cat);
                controlStatus(statusData, model,cat);
              
                if(statusData<0){
                    return "category/category_View";
                }else{
                 
                    new CategoryServiceUpdate().insertImg(file, cat.getUrlImagen());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
                   
                   
                }
              
            }else{
                
                 showMessage ("adMessage", "file-regular","No se ha Seleccionado una imagen. por favor ingresa una.",model); 
               modelAction(model, cat);
            }
            
        }else{
               showMessage ("adMessage", "pen-to-square-regular","Faltan datos por llenar",model);      
                modelAction(model, cat);
            
            
        }
            
<<<<<<< HEAD
             model.addAttribute("Labels", new CategoryService().getListLabel());
=======
             model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
        return "category/category_View";
    }
 
   private void controlStatus(int status, Model model, Category cat) {
<<<<<<< HEAD
       //me traduce el error de base de datos
=======
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
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
  
<<<<<<< HEAD
         model.addAttribute("Labels", new CategoryService().getListLabel());
=======
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
}

    
   
    
    private Category CreateCategory(Category cat, LocalDate local,String status,String notaAdmin,MultipartFile file){
<<<<<<< HEAD
             String note=(notaAdmin.trim().isEmpty())?"NS":notaAdmin;
              cat.setId(0);
              cat.setCatVisible(new CategoryService().getVisibleCat(status));
              cat.setQuantity(0);
              cat.setImage(new CategoryService().getRoute(file));
=======
              String note=(notaAdmin.trim().isEmpty())?"NS":notaAdmin;
              cat.setId(0);
              cat.setCatVisible(new CategoryServiceUpdate().getVisibleCat(status));
              cat.setQuantity(0);
              cat.setUrlImagen(new CategoryServiceUpdate().getRoute(file));
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
              cat.setDate(local);
              cat.setComment(note);//commit: 
              cat.setCreateBy("Josue Porras");
              
        return cat;
    }
    

    @GetMapping({"/ModicarDatos/{idCode}"})
<<<<<<< HEAD
    //selecciona la categoria que deseo modificar
    public String modifyCategory(@PathVariable String idCode, Model model){
        
           Category cat=catRepo.getCategoryOnly(idCode);
           if(cat.getComment().trim().equalsIgnoreCase("NS")){
           cat.setComment("");     
           }
          
           model.addAttribute("selectedCat", cat);
           setUrlAct(cat.getImage());
            model.addAttribute("Labels", new CategoryService().getListLabel());
=======
    
    public String modifyCategory(@PathVariable String idCode, Model model){
        
           Category cat=catRepo.getCategory(idCode);
            if(cat.getComment().trim().equalsIgnoreCase("NS")){
           cat.setComment("");     
           }
           model.addAttribute("selectedCat", cat);
           setUrlAct(cat.getUrlImagen());
            model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
         
       return "category/category_View";
    }
    
    
    @PostMapping({"/TerminarModificaciones"})
    public String ModifyTerm(@PageableDefault(size = 4, page = 0) Pageable pageable,@RequestParam("img")MultipartFile file,@ModelAttribute Category cat,@RequestParam("Visibilidad")String status,
            @RequestParam("notaAdmin")String notaAdmin,Model model){
        LocalDate local= LocalDate.now();
<<<<<<< HEAD
            //valido que los datos no este vacios
            if(new CategoryService().validation(cat, status)){
            String note=(notaAdmin.trim().isEmpty()|| notaAdmin.trim().equalsIgnoreCase("Sin nota de autor"))?"NS":notaAdmin;
              cat.setCatVisible(new CategoryService().getVisibleCat(status));
=======
            
            if(new CategoryServiceUpdate().validation(cat, status)){
             String note=(notaAdmin.trim().isEmpty()|| notaAdmin.trim().equalsIgnoreCase("Sin nota de autor"))?"NS":notaAdmin;
              cat.setCatVisible(new CategoryServiceUpdate().getVisibleCat(status));
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
              cat.setQuantity(0);
              
              cat.setDate(local);
              cat.setComment(note);
              cat.setCreateBy("Josue Porras");
              
              if(file.isEmpty()){
<<<<<<< HEAD
                  cat.setImage(getUrlAct());
=======
                  cat.setUrlImagen(getUrlAct());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
              if(catRepo.modifyCat(cat)){
                  
              }     
              }else{
               
<<<<<<< HEAD
              cat.setImage(new CategoryService().getRoute(file));    
=======
              cat.setUrlImagen(new CategoryServiceUpdate().getRoute(file));    
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
             
              
              
              if(catRepo.modifyCat(cat)){
<<<<<<< HEAD
                   new CategoryService().insertImg(file, cat.getImage());
                   new CategoryService().deleteImage(getUrlAct());
=======
                   new CategoryServiceUpdate().insertImg(file, cat.getUrlImagen());
                   new CategoryServiceUpdate().deleteImage(getUrlAct());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
                   
              }
              }
               
                showMessage("success", "square-check-regular", "Se ha modificado con exito la Categoría", model);
               setUrlAct("");
            }else{
                 showMessage("adMessage", "pen-to-square-regular","Faltan datos por llenar, error al modificar." , model);
<<<<<<< HEAD
                   model.addAttribute("Labels", new CategoryService().getListLabel());
                 cat.setImage(getUrlAct());
=======
                   model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
                 cat.setUrlImagen(getUrlAct());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
                 model.addAttribute("selectedCat", cat);
                 
                 
                 return "category/category_View";
            }
              
       
           //model.addAttribute("ListaCategory", catRepo.getCategorias());
<<<<<<< HEAD
            //refreshTable(pageable, model);
           model.addAttribute("Labels", new CategoryService().getListLabel());
=======
           // refreshTable(pageable, model);
            model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
        
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
         return "category/category_View";
    }
    
    
    //11 05 0202 imagen.png
     @GetMapping({"/DeleteCategory/{idCode}/{url}"})
     
     public String EliminarCategory(@PathVariable String idCode,@PathVariable String url, Model model,@PageableDefault(size = 4, page = 0) Pageable pageable){
      int status=catRepo.DeleteCategory(idCode);
<<<<<<< HEAD
      //si elimina la cat, elimino todo lo asociado
          switch (status) {
              case 1 -> {
                  if( !new CategoryService().deleteImage(url)){
=======
      
          switch (status) {
              case 1 -> {
                  if( !new CategoryServiceUpdate().deleteImage(url)){
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
                      showMessage("Error", "circle-xmark-regular", "Error de conectividad", model);
                  }     showMessage("success", "square-check-regular", "Categoría eliminada con éxito.", model);
              }
              case -1 -> showMessage("Error", "circle-xmark-regular", "La categoria no se puede eliminar por que ya esta asociada a otro", model);
              case -2 -> showMessage("Error", "circle-xmark-regular", "Error de conexion", model);
              default -> {
              }
          }
<<<<<<< HEAD
         
=======
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
           //model.addAttribute("ListaCategory", catRepo.getCategorias());
           refreshTable(pageable, model);
         
         return "category/tableCategory";
     }
     
     @GetMapping({"/returnAddCat"})
     public String returnAddCat (Model model){
<<<<<<< HEAD
          model.addAttribute("Labels", new CategoryService().getListLabel());
=======
          model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
         
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
<<<<<<< HEAD
         model.addAttribute("Labels", new CategoryService().getListLabel());
          model.addAttribute("ListaCategory", catRepo.getCategory());
    }
    
   //metodo de mensajes en la vista
=======
         model.addAttribute("Labels", new CategoryServiceUpdate().getListLabel());
          model.addAttribute("ListaCategory", catRepo.getCategorias());
    }
    
   
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
    public void showMessage (String type, String imgSelect,String message,Model model){
        model.addAttribute("class", type);
        model.addAttribute("type", imgSelect);
        model.addAttribute("message", message);
    }
    
<<<<<<< HEAD
    //metodo de paginacion
=======
    
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
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
<<<<<<< HEAD
=======


>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
