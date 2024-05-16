/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.domain.Preparation;
import cr.ac.una.booleanKitchen.domain.Step;
import cr.ac.una.booleanKitchen.service.PreparationService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Daniel Briones
 */
@Controller
@RequestMapping("/PreparacionIndex")
public class PreparationController {
    private PreparationService prep = new PreparationService();
    // proximamente disponible

      
    //obtengo el tiempo de receta
    public LocalTime getHour(String time){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
          try {
            // Parsear la cadena de hora y minutos a LocalTime
            LocalTime localTime = LocalTime.parse(time, formatter);
        
            // Puedes hacer más operaciones con la hora y minutos aquí, como guardar en una base de datos, etc.
         
            return localTime;
        } catch (Exception e) {
            // Manejar errores de parsing de la hora
            return null;
        }
 
    }
    
    @PostMapping({"/cancelPrep"})
    public String cancelPrep(Model model){
        
        if(!prep.getPrep().getRouteImg().isEmpty()){
            model.addAttribute("selectedPrep", prep.getPrep());
           // prep.deleteImage(prep.getPrep().getRouteImg());
           
             model.addAttribute("difLis", new PreparationService().getDifficulty());
        }
         return "Preparation/PreparationView";
    }
    
    
    @PostMapping({"/addStep"})
    public String addStep(@RequestParam(value = "img", required = false) MultipartFile img,@RequestParam String texto,@RequestParam String paso,Model model){
        System.out.println("Entrando al metodo: " + texto + " : " + paso);
        //Valido que esten llenos los pasos
        if(prep.ValidationStep(texto, paso)){
            System.out.println("Entrando a la validacion Step");
            //Valido que el nombre del paso no contenga numero ni caracteres especiales
            if(prep.validarCadena(paso) ){
                System.out.println("Entrando a la validacion");
                //valido que el nombre no se repita
                if(!prep.verifyNameTitle(paso)){
                    System.out.println("Verifica el nombre");
                     if (img != null && !img.isEmpty()) {
                        System.out.println("Imagen llena");
                    createSteps(img, texto, paso);
                } else {
                    System.out.println("Imagen vacia al metodo");
                    createSteps(null, texto, paso); // Tratamiento cuando la imagen está ausente o es nula
                }
                       showMessage ("success", "square-check-regular","Paso guardado correctamente.",model);
                }else{
                     Step step= new Step(0, null, paso, "", texto);
            
                   model.addAttribute("dateElement",step );
                    showMessage ("adMessage", "pen-to-square-regular","El nombre del paso ya se encuentra registrado.",model);   
                }
            }else{
                 Step step= new Step(0, null, paso, "", texto);
            
                   model.addAttribute("dateElement",step );
                   showMessage ("adMessage", "triangle-exclamation-solid","No se permiten numeros ni caracteres especiales en las casillas de titulo.",model);      
            }
        }else{
             System.out.println("No entro a la validacion Step");
            Step step= new Step(0, null, paso, "", texto);
            
            model.addAttribute("dateElement",step );
            showMessage ("adMessage", "pen-to-square-regular","Hay datos vacios, por favor verifica.",model);      
            
        }
        
        if(!Utilidades.stepsLis.isEmpty()){
             model.addAttribute("ListStep", Utilidades.index);
        }
      
        model.addAttribute("flag", true);
        
        return "resultPrep";
    }
    
   
    
    
    //metodo encargado de agarrar una posicion de la lista para modificar.
    @GetMapping({"/modifyStep/{url}"})
    public String selectIndexOfSteps(Model model, @PathVariable String url){
        model.addAttribute("flag", true);
        
        int index=prep.getIndexSteps(url);
        if(index!= -1){
        
        Step stepSlect=Utilidades.stepsLis.get(index);
        model.addAttribute("selectedStep",stepSlect );
        Utilidades.index = index;
            
        }
        return "Preparation/secondPre";
    }
    
    
    @PostMapping({"/finishModify"})
    public String finishModify(Model model,@RequestParam(value = "img", required = false) MultipartFile img,@RequestParam String texto,@RequestParam String paso){
        model.addAttribute("flag", true);
        
     
        if(!(texto.trim().isEmpty()||paso.trim().isEmpty())){
             if(!prep.verifyNameTitleModify(paso)){
         
            ModifyStep( img,  texto, paso);

            model.addAttribute("ListStep", Utilidades.stepsLis);
            showMessage ("success", "square-check-regular","Se ha modificado con exito..",model);
             }else{
                  Step step=createObjectStep(texto, paso);
            showMessage ("adMessage", "triangle-exclamation-solid","No se puede repetir el nombre del paso.",model);      
        //   model.addAttribute("messageStep", "No se puede repetir el nombre del paso.");
           model.addAttribute("dateElement", step);
             }
             
             }else{
            Step step=createObjectStep(texto, paso);
          // model.addAttribute("messageStep", "No se puede dejar espacios de textos vacios.");
           model.addAttribute("dateElement", step);
            showMessage ("adMessage", "triangle-exclamation-solid","No se puede dejar espacios de textos vacios.",model);      
        }
        
        
        return  "Preparation/resultPrep";
    }
    
    @GetMapping({"/deletePas/{paso}"})
    public String DeleteStep(@PathVariable String paso,Model model){
        int index=prep.getIndexSteps(paso);
        Step step=Utilidades.stepsLis.get(index);
         if( step.getRouteImg() !=null && !step.getRouteImg().trim().isEmpty()){
            prep.deleteImage(step.getRouteImg());
        }
        Utilidades.stepsLis.remove(index);
        
        Utilidades.index = 0;
        
        if(!Utilidades.stepsLis.isEmpty()){
        model.addAttribute("ListStep", Utilidades.stepsLis);    
        }
        
        model.addAttribute("flag", true);
        showMessage ("success", "square-check-regular","Se ha eliminado correctamente el paso.",model);
        return  "Preparation/resultPrep";
    }
    
    
    @GetMapping({"/CancelModify"})
    public String cancelPost(Model model){
        
         model.addAttribute("ListStep", Utilidades.stepsLis);
        model.addAttribute("flag", true);
        return  "Preparation/secondPre";
    }
    
    @PostMapping("/modificationPrep")
    public String modifyPreparation(@RequestParam("img")MultipartFile file,@RequestParam String label, @RequestParam String time,@RequestParam String note,@RequestParam String warnings  ,Model model){
        
//        if(prep.validation(label, time, warnings)){
//            LocalTime local=getHour(time);
//            Preparation preparation=getPreparation(label, local, file, note, warnings );
//            if(!file.isEmpty()){
//                String date=prep.getPrep().getRouteImg();
//                System.out.println(date);
//                prep.deleteImage(date);
//                prep.insertImg(file, preparation.getRouteImg());
//            }
//   
//              prep.setPrep(preparation);
//              model.addAttribute("flag", true);
//               model.addAttribute("ListStep", Utilidades.stepsLis);
//              
//        }else{
//             model.addAttribute("difLis", new PreparationService().getDifficulty());
//            model.addAttribute("message", "Faltan datos por llenar, por favor llenalo.");
//             showMessage ("adMessage", "pen-to-square-regular","Faltan datos por llenar",model);  
//        }
        
        
        
        return  "Preparation/PreparationView";
    }
    
    
    
    @PostMapping({"/sendPrep"})
    public String SendInformacion (Model model){
        
        
//        if(!prep.getStepsLis().isEmpty()){
//            LocalDate localDate = LocalDate.parse("2024-05-10");
//                    
//            Usuario usuario = new Usuario(2, localDate, "4321", "s", "s", "s", "s", "s");
//            //NULL, '434', 'f', 'f', 'f', 'f', '2024-05-10', '1', 'f', 'f'
//           Category category = new Category(4, "434", "f", "f", "f",
//                "f", "f", true, 0, "f", localDate);
//           // '432', 'd', 'd', 'd', 'd', 'd', 'd'
//             Origin origin = new Origin(2, "d", "d", "432", "d", "d", "d", "d");
//             Receta receta = new Receta(0, "REC007", "Cantones", origin, category, 10, "imagen_receta10.jpg");
//             receta.setTotalCali(4);
//             receta.setUser(usuario);
//            Preparation prepa=prep.getPrep();
//            prepa.setId(0);
//            prepa.setIdSerial(prep.getCodePrep());
//            prepa.setIdRecipe(receta);
//            prepa.setPreparationList(prep.getStepsLis());
//            prepServ.guardar(prepa);
//            System.out.println("enviadoooo");
//            prep.setPrep(new Preparation());
//            prep.setStepsLis(new ArrayList());
//           
//        }else{
//            showMessage ("adMessage", "pen-to-square-regular","No hay pasos para enviar",model); 
//            model.addAttribute("flag", true);
//        }
       
        
        
        return  "Preparation/PreparationView";
    }
    
//    @GetMapping({"/viewPreparations"})
//    public String viewPrep(Model model){
//        model.addAttribute("listPrep", prepServ.getPreparations());
//        return "Preparation/viewPreparations";
//    }
    
//    @GetMapping({"/viewprepNow/{ident}"})
//    public String viewPrepNow(@PathVariable String ident,Model model){
//        
//        Preparation prepa=prepServ.getPreparationSelect(ident);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        prepa.setTimeStr(prepa.getTime().format(formatter));
//        model.addAttribute("preps", prepa);
//        return "Preparation/viewPrep";
//    }
//    
     
    
    
    public void showMessage (String type, String imgSelect,String message,Model model){
        model.addAttribute("class", type);
        model.addAttribute("type", imgSelect);
        model.addAttribute("message", message);
    }
    
    
    public void createSteps(MultipartFile file, String text, String title){
      System.out.println("Creo el paso");
       Step step=createObjectStep(text, title);
        step.setRouteImg((file!=null &&  !file.isEmpty())?prep.getRoute(file):"NS");
       if( file!=null &&  !file.isEmpty()){
           prep.insertImg(file, step.getRouteImg());
       }
      
      
      Utilidades.stepsLis.add(step);
      System.out.println("Entrando al paso");
        System.out.println(Utilidades.stepsLis.get(0).getTitle());
    }
    
    public void ModifyStep(MultipartFile file, String text, String title){
        
         Step step=createObjectStep(text, title);
         
          String url=Utilidades.stepsLis.get(Utilidades.index).getRouteImg();
          if( file != null && !file.isEmpty()){
                prep.deleteImage(url);
                step.setRouteImg(prep.getRoute(file));
                prep.insertImg(file, step.getRouteImg());
            }else{
                  step.setRouteImg(url);
            }
           step.setPreparation(prep.getPrep());
            Utilidades.stepsLis.remove(prep.getIndex());
            Utilidades.stepsLis.add(prep.getIndex(), step);
            
            prep.setIndex(0);
          
    }
    
    public Step createObjectStep(String text, String title){
        Step step= new Step();
       step.setTitle(title);
       step.setId(0);
       step.setPreparation(Utilidades.preparacion);
       step.setTextStep(text);
       return step;
    }



}
