
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import cr.ac.una.booleanKitchen.domain.Comment;
import cr.ac.una.booleanKitchen.domain.Recipe;
import cr.ac.una.booleanKitchen.service.IComentariosService;
import cr.ac.una.booleanKitchen.service.IRecetaService;

import java.time.Duration;
import java.time.LocalDateTime;
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

    @GetMapping({ "/comentarios" })
    public String comentarios(@PageableDefault(size = 4, page = 0) Pageable pageable,
            @RequestParam("identificadorReceta") String identificador, Model model) {
        model.addAttribute("usuario", Utilidades.user);
        model.addAttribute("idReceta", identificador);

        // Cargar comentarios
        paginacionComentarios(model, identificador, pageable);

        return "comentar";
    }

    @GetMapping("/cargarFormularioComentar")
    public String cargarFormularioComentar(@RequestParam("identificadorReceta") String identificadorReceta,
            Model model) {

        model.addAttribute("idReceta", identificadorReceta);
        return "Comentarios/NuevoComentario";
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
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            Model model) {

        if (!validarDatos(identificador, calificacion, titulo, comentario, dificultad, claridadInstrucciones, modifico,
                recomendaria)) {

            model.addAttribute("message", "Debe completar todos los campos");
            model.addAttribute("error", true);

            return "Notificaciones/notificaciones";
        }

        Recipe recipe = recetaService.findByIdentificador(identificador);
        Comment cm = new Comment(0, "COM-00" + titulo + LocalDateTime.now(), recipe, Utilidades.user, titulo,
                comentario, dificultad, claridadInstrucciones, modifico, sugerencias, recomendaria, LocalDateTime.now(),
                calificacion, 0, "");
        try {
            comentarioService.guardar(cm);
            model.addAttribute("message", "Comentario enviado con exito");
            model.addAttribute("error", false);
        } catch (Exception e) {
            model.addAttribute("message", "Error al enviar comentario ");
            model.addAttribute("error", true);
        }

        model.addAttribute("idReceta", identificador);
        System.out.println("IdRecetaAlGuardar: " + model.getAttribute("idReceta"));

        // Refrescar comentarios
        paginacionComentarios(model, identificador, PageRequest.of(page, size));

        return "cargarComentarios";
    }

    @GetMapping("/like")
    public String likes(@RequestParam("identificadorComentario") String identificador,
            @RequestParam("aumentar") String aumentar, Model model) {

        Comment comentario = comentarioService.findByIdentificador(identificador);
        if (comentario != null) {
            if (aumentar.equals("1")) {
                comentario.setReaccionesPositivas(comentario.getReaccionesPositivas() + 1);
                model.addAttribute("message", "Me gusta");
                model.addAttribute("error", false);
            } else {
                comentario.setReaccionesPositivas(comentario.getReaccionesPositivas() - 1);
                model.addAttribute("message", "No me gusta");
                model.addAttribute("error", true);
            }

            comentarioService.guardar(comentario);
        }

        return "Notificaciones/notificaciones";
    }

    @GetMapping("/cargarFormularioEditar")
    public String cargarFormulario(@RequestParam("identificadorComentario") String identificador,
            @RequestParam("identificadorReceta") String identificadorReceta, Model model) {

        model.addAttribute("comment", comentarioService.findByIdentificador(identificador));

        System.out.println("\n\nIdReceta para cargarEnFormulario: " + identificadorReceta);
        model.addAttribute("idReceta", identificadorReceta);
        return "Comentarios/EditarComentario";
    }

    @GetMapping("/editarComentario")
    public String editar(
            @RequestParam("identificadorComentario") String identificador,
            @RequestParam("identificadorReceta") String identificadorReceta,
            @RequestParam("tCalificacion") int calificacion,
            @RequestParam("tTitulo") String titulo,
            @RequestParam("tComentario") String comentario,
            @RequestParam("tDificultad") String dificultad,
            @RequestParam("tClaridadDeInstrucciones") int claridadInstrucciones,
            @RequestParam("tModificoLaReceta") boolean modifico,
            @RequestParam("tSugerencias") String sugerencias,
            @RequestParam("tRecomendaria") boolean recomendaria,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            Model model) {


        System.out.println("\n\n\n");
        System.out.println("Identificador Comentario: " + identificador);
    System.out.println("Identificador Receta: " + identificadorReceta);
    System.out.println("Calificación: " + calificacion);
    System.out.println("Título: " + titulo);
    System.out.println("Comentario: " + comentario);
    System.out.println("Dificultad: " + dificultad);
    System.out.println("Claridad de Instrucciones: " + claridadInstrucciones);
    System.out.println("Modificó la Receta: " + modifico);
    System.out.println("Sugerencias: " + sugerencias);
    System.out.println("Recomendaría: " + recomendaria);
    System.out.println("Page: " + page);
    System.out.println("Size: " + size);

        

            
        if (!validarDatos(identificador, calificacion, titulo, comentario, dificultad, claridadInstrucciones, modifico,
                recomendaria) || identificadorReceta.trim().isEmpty()) {

            model.addAttribute("message", "Debe completar todos los campos");
            model.addAttribute("error", true);

            return "Notificaciones/notificaciones";
        }

        Comment cm = comentarioService.findByIdentificador(identificador);
        if(cm == null){
            model.addAttribute("message", "Error al modificar el comentario");
            model.addAttribute("error", true);

            return "Notificaciones/notificaciones";
        }

        //actualizar los campos
        cm.setPuntuation(calificacion);
        cm.setTitle(titulo);
        cm.setComment(comentario);
        cm.setDificultad(dificultad);
        cm.setClaridadInstrucciones(claridadInstrucciones);
        cm.setRealizoUnaModificacion(modifico);
        cm.setSugerencia(sugerencias);
        cm.setRecomendariaLaReceta(recomendaria);



        try {
            comentarioService.guardar(cm);
            model.addAttribute("message", "Comentario modificado con exito");
            model.addAttribute("error", false);
        } catch (Exception e) {
            model.addAttribute("message", "Error al modificar el comentario");
            model.addAttribute("error", true);
        }

        model.addAttribute("idReceta", identificadorReceta);
        // Refrescar comentarios
        paginacionComentarios(model, identificadorReceta, PageRequest.of(page, size));
        return "cargarComentarios";
    }

    @GetMapping("/eliminarComentario")
    public String eliminar(
            @RequestParam("identificadorComentario") String identificador,
            @RequestParam("idReceta") String idReceta,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            Model model) {

        System.out.println("\n\nIdReceta1: " + idReceta);
        Comment comentario = comentarioService.findByIdentificador(identificador);
        if (comentario != null) {
            comentarioService.eliminar(comentario);
            model.addAttribute("message", "Comentario eliminado con exito!!");
            model.addAttribute("error", false);

        } else {
            model.addAttribute("message", "No se pudo eliminar el comentario");
            model.addAttribute("error", true);
        }
        model.addAttribute("idReceta", idReceta);
        System.out.println("\n\nIdReceta2: " + idReceta);
        // Refrescar comentarios
        paginacionComentarios(model, idReceta, PageRequest.of(page, size));
        return "cargarComentarios";
    }

    private void paginacionComentarios(Model model, String idReceta, Pageable pageable) {
        List<Integer> pageSizeOptions = Arrays.asList(4, 8, 10, 20, 50, 100);
        model.addAttribute("pageSizeOptions", pageSizeOptions);
        Page<Comment> commentPage = comentarioService.findByRecipeId(idReceta, pageable);
        if (commentPage.getContent().isEmpty() && pageable.getPageNumber() > 0) {
            commentPage = comentarioService.findByRecipeId(
                    idReceta,
                    PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()));
        }

        for (Comment comment : commentPage.getContent()) {
            comment.setAntiguedad(calcularAntiguedad(comment.getDate()));
        }
        model.addAttribute("page", commentPage);
        var totalPages = commentPage.getTotalPages();
        var currentPage = commentPage.getNumber();
        var start = Math.max(1, currentPage);
        var end = Math.min(currentPage + 5, totalPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                pageNumbers.add(i);
            }

            model.addAttribute("pageNumbers", pageNumbers);
        }
        System.out.println("\n\nIdReceta4: " + idReceta);
    }

    private String calcularAntiguedad(LocalDateTime fechaPublicacion) {
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaPublicacion, ahora);

        if (duracion.toDays() < 1) {
            long horas = duracion.toHours();
            if (horas < 1) {
                long minutos = duracion.toMinutes();
                if (minutos < 1) {
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

    private boolean validarDatos(String identificador, int calificacion, String titulo, String comentario,
            String dificultad, int claridadInstrucciones, boolean modifico,
            boolean recomendaria) {
        if (identificador == null || identificador.trim().isEmpty() ||
                calificacion == 0 ||
                titulo == null || titulo.trim().isEmpty() ||
                comentario == null || comentario.trim().isEmpty() ||
                dificultad == null || dificultad.trim().isEmpty() ||
                claridadInstrucciones == 0) {
            return false;
        }
        return true;
    }

}
