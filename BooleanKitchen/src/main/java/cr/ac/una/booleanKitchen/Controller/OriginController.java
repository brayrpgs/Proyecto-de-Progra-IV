/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;



import cr.ac.una.booleanKitchen.domain.Origin;
import cr.ac.una.booleanKitchen.service.OriginServices;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    String aux;

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
    

    @GetMapping("/indexorigin")
    public String CrudOrigin(){
        return "origin/indexorigin";
    }
     @GetMapping("/indexorigin/Create")
    public String CreateOrigin(Model model){
         LinkedList<Origin> org = OriginServices.getOrigin();
         model.addAttribute("Tabla",org);
        return "origin/origingeneral";
    }
   
      @PostMapping({"/CrearOrigen"})
      public String CrearOrigen(@RequestParam("name")String name,
              @RequestParam("description")String description,
              @RequestParam("identificador")String identificador,
              @RequestParam("country")String idRecipeList,
              @RequestParam("img")MultipartFile file,
              @RequestParam("continent")String continent,
              @RequestParam("taste")String taste,
              Model model
              ){
              if(!file.isEmpty()){
              String fileS=new OriginServices().getRoute(file);
              Origin org=new Origin(0,name,description,identificador,idRecipeList,fileS,continent,taste);
                  try {
                      new OriginServices().createOrigin(org);
                      
                      //mensaje
                      model.addAttribute("mensaje", "Guardado con exito");
                      
                     new OriginServices().insertImg(file,fileS);
                  } catch (SQLException ex) {
                      model.addAttribute("mensaje", "Faltan datos");
                      Logger.getLogger(OriginController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }else{
                  model.addAttribute("mensaje", "Falta imagen");
                 
              }
          LinkedList<Origin> orig = OriginServices.getOrigin();
          model.addAttribute("Tabla",orig);
          
         return "origin/origingeneral"; 
      }
     @GetMapping("/CrearOrigen")
    public String ReadOrigin(Model model){
        LinkedList<Origin> org = OriginServices.getOrigin();
        model.addAttribute("Tabla",org);
        return "origin/origingeneral";
    }

    
     @GetMapping("indexorigin/Update")
    public String UpdateOrigin(Model model){
        LinkedList<Origin> org = OriginServices.getOrigin();
        model.addAttribute("Tabla",org);
        
        return "origin/form_originupdate";
    }
    
    @GetMapping("/DeleteOrigen/{identificador}/{url}")
     public String DeleteOrigin(
              @PathVariable String identificador,@PathVariable String url,Model model){
         
          try {
              
            if(new OriginServices().deleteOrigin(identificador)){
                new OriginServices().deleteImage(url);
                model.addAttribute("mensaje", "Borrado con exito");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OriginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LinkedList<Origin> orig = OriginServices.getOrigin();
        model.addAttribute("Tabla",orig);
        return "originAfterDelete";
     }   

     
     @GetMapping("/SeleccionarOrigen/{Origen}")
     public String getOrigin(@PathVariable String Origen ,Model model){
     
         Origin org = new OriginServices().obtenerOrigen(Origen);
         setAux(org.getIdImg());
         model.addAttribute("selectOrigin",org);
         
     
     return "origin/origingeneral";
     }
     
     @PostMapping({"/updateOrigen"})
      public String updateOrigen(@RequestParam("name")String name,
              @RequestParam("description")String description,
              @RequestParam("identificador")String identificador,
              @RequestParam("country")String idRecipeList,
              @RequestParam("img")MultipartFile file,
              @RequestParam("continent")String continent,
              @RequestParam("taste")String taste,
              Model model
              ){
              if(new OriginServices().validar(name, description, identificador, idRecipeList, continent, taste)){
                 Origin org= new Origin();
                 
                 org.setIdentificador(identificador);
                 org.setName(name);
                 org.setDescription(description);
                 org.setCountry(idRecipeList);
                 org.setIdImg("");
                 org.setContinent(continent);
                 org.setTaste(taste);
                 
                 if(!file.isEmpty()){
                 
                     new OriginServices().deleteImage(getAux());
                     org.setIdImg(new OriginServices().getRoute(file));
                     new OriginServices().insertImg(file, org.getIdImg());
                     
                     
                 
                 }else{
                 
                     org.setIdImg(getAux());
                 
                 }
                  try {
                      if(new OriginServices().updateOrigin(org)){
                      
                      
                      //mensaje
                      
                      model.addAttribute("mensaje", "Actualizado con exito");
                      
                          setAux(null);
                      }
                  } catch (SQLException ex) {
                      model.addAttribute("mensaje", "Faltan datos");
                      Logger.getLogger(OriginController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                  
              
          LinkedList<Origin> orig = OriginServices.getOrigin();
          model.addAttribute("Tabla",orig);
          
         return "origin/origingeneral"; 
      }
     
     
}
