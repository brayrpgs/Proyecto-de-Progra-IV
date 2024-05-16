
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.domain.Comment;
import cr.ac.una.booleanKitchen.domain.Recipe;
import cr.ac.una.booleanKitchen.service.IComentariosService;
import cr.ac.una.booleanKitchen.service.IRecetaService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/c_comentarios")
public class ControllerComentarios {
    @Autowired
    private IComentariosService comentarioService;
    @Autowired
    private IRecetaService recetaService;
    
    
    
    
    @GetMapping({"/comentarios"})
    public String comentarios(@RequestParam("identificadorReceta") String identificador, Model model){
        System.out.println("Comentarios a ver");
        model.addAttribute("usuario", Utilidades.user);
        
        System.out.println("Identificador 1: " + identificador);
        model.addAttribute("comentarios", comentarios(identificador));
        model.addAttribute("receta", recetaService.findByIdentificador(identificador));
        return "comentar";
    }
    
    
    @GetMapping("/comentar")
    public String comentar(
            @RequestParam("id") String identificador,
            @RequestParam("tCalificacion") int calificacion,
            @RequestParam("tTitulo") String titulo,
            @RequestParam("tComentario") String comentario,
            @RequestParam("tDificultad") String dificultad,
            @RequestParam("tClaridadDeInstrucciones") int claridadInstrucciones,
            @RequestParam("tModificoLaReceta") boolean modifico,
            @RequestParam("tSugerencias") String sugerencias,
            @RequestParam("tRecomendaria") boolean recomendaria,
            Model model
    ){
        System.out.println("IDentificador: " + identificador);
        Recipe recipe = recetaService.findByIdentificador(identificador);
        Comment cm = new Comment(0, "COM-00" + titulo + LocalDateTime.now(), recipe, Utilidades.user, titulo, comentario, dificultad, claridadInstrucciones, modifico, sugerencias, recomendaria, LocalDateTime.now(), calificacion, 0, "");
        comentarioService.guardar(cm);
        
        model.addAttribute("comentarios", comentarios(identificador));
        model.addAttribute("receta", recetaService.findByIdentificador(identificador));
        return "cargarComentarios";
    }
    
        @GetMapping("/like")
    public String likes(@RequestParam("identificadorComentario") String identificador, @RequestParam("aumentar") String aumentar, Model model){
        
        Comment comentario = comentarioService.findByIdentificador(identificador);
        if(comentario != null){
            if(aumentar.equals("1")){
                comentario.setReaccionesPositivas(comentario.getReaccionesPositivas() + 1);
            } else {
                comentario.setReaccionesPositivas(comentario.getReaccionesPositivas() - 1);
            }
            
            comentarioService.guardar(comentario);
        }
        
        return "cargarComentarios";
    }
    
    
    @GetMapping("/editarComentario")
    public String editar(@RequestParam("identificadorComentario") String identificador, Model model){
        
        System.out.println("Comentario a editar: " + identificador);
        return "redirect:/c_inicio/index";
    }
    
    @GetMapping("/eliminarComentario")
    public String eliminar(@RequestParam("identificadorComentario") String identificador, @RequestParam("idReceta") String idReceta, Model model){
        
        System.out.println("Comentario a eliminar: " + identificador);
        
        Comment comentario = comentarioService.findByIdentificador(identificador);
        if(comentario != null){
            comentarioService.eliminar(comentario);
        } else {
            System.out.println("No se puedo eliminar el comentario con el identificador: " + identificador);
        }
        Recipe recipe = new Recipe();
        recipe.setIdentificador(idReceta);
        model.addAttribute("receta", recipe);
        model.addAttribute("comentarios", comentarioService.findByRecetaId(idReceta));
        return "cargarComentarios";
    }
    
    
    private String calcularAntiguedad(LocalDateTime fechaPublicacion) {
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaPublicacion, ahora);
        
        if (duracion.toDays() < 1) {
            long horas = duracion.toHours();
            if (horas < 1) {
                long minutos = duracion.toMinutes();
                if(minutos < 1){
                    return "Justo ahora";
                } else {
                    return "Hace " + minutos + " minuto" + (minutos == 1 ? "" : "s");
                }
            } else {
                return "Hace " + horas + " hora" + (horas == 1 ? "" : "s");
            }
        } else if (duracion.toDays() < 7) {
            long dias = duracion.toDays();
            return "Hace " + dias + " día" + (dias == 1 ? "" : "s");
        } else if (duracion.toDays() < 30) {
            long semanas = duracion.toDays() / 7;
            return "Hace " + semanas + " semana" + (semanas == 1 ? "" : "s");
        } else {
            long meses = duracion.toDays() / 30;
            return "Hace " + meses + " mes" + (meses == 1 ? "" : "es");
        }
    }
    

    
    private List<Comment> comentarios(String identificador){
        List<Comment> comentarios = comentarioService.findByRecetaId(identificador);
        for(int i = 0; i < comentarios.size(); i++){
            
            comentarios.get(i).setAntiguedad(calcularAntiguedad(comentarios.get(i).getDate()));
        }
        System.out.println("Comentario: " + comentarios.size());
        return comentarios;
    }
}
