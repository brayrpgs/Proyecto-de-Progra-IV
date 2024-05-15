/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

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

    @GetMapping({ "/preparationMain" })
    public String initializerPrep(Model model) {

        model.addAttribute("difLis", new PreparationService().getDifficulty());

        return "Preparation/PreparationView";
    }

    @PostMapping({ "/initPreparation" })
    public String initPreparation(@RequestParam("img") MultipartFile file, @RequestParam String label,
            @RequestParam String time, @RequestParam String note, @RequestParam String warnings, Model model) {

        LocalTime local = getHour(time);
        if (prep.validation(label, time, warnings)) {
            // si no esta vacio

            if (!file.isEmpty()) {

                if (local != null) {
                    Preparation preparation = getPreparation(label, local, file, note, warnings);
                    prep.setPrep(preparation);
                    model.addAttribute("flag", true);
                    prep.insertImg(file, prep.getPrep().getRouteImg());
                    /*
                     * if(!prep.getStepsLis().isEmpty()){
                     * model.addAttribute("ListStep", prep.getStepsLis());
                     * }
                     */
                }

            } else {
                Preparation preparation = getPreparation(label, local, file, note, warnings);
                model.addAttribute("dateElement", preparation);
                model.addAttribute("difLis", new PreparationService().getDifficulty());
                model.addAttribute("message", "No se ha insertado una imagen, por favor, agregala.");

            }
        } else {
            Preparation preparation = getPreparation(label, local, file, note, warnings);
            model.addAttribute("dateElement", preparation);
            model.addAttribute("difLis", new PreparationService().getDifficulty());
            model.addAttribute("message", "Faltan datos por llenar, por favor llenalo.");
        }

        return "Preparation/PreparationView";
    }

    // obtengo el tiempo de receta
    public LocalTime getHour(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            // Parsear la cadena de hora y minutos a LocalTime
            LocalTime localTime = LocalTime.parse(time, formatter);

            // Puedes hacer más operaciones con la hora y minutos aquí, como guardar en una
            // base de datos, etc.

            return localTime;
        } catch (Exception e) {
            // Manejar errores de parsing de la hora
            return null;
        }

    }

    public Preparation getPreparation(String label, LocalTime local, MultipartFile file, String note, String warings) {
        Preparation preparation = new Preparation();
        preparation.setNoteAuthor(note);
        preparation.setDifficulty(label);
        preparation.setTime(local);
        preparation.setWarnings(warings);
        preparation.setRouteImg(new PreparationService().getRoute(file));

        return preparation;
    }

    @PostMapping({ "/cancelPrep" })
    public String cancelPrep(Model model) {

        if (!prep.getPrep().getRouteImg().isEmpty()) {
            model.addAttribute("selectedPrep", prep.getPrep());
            // prep.deleteImage(prep.getPrep().getRouteImg());

            model.addAttribute("difLis", new PreparationService().getDifficulty());
        }
        return "Preparation/PreparationView";
    }

    @PostMapping({ "/addStep" })
    public String addStep(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam String texto,
            @RequestParam String paso, Model model) {

        if (prep.ValidationStep(texto, paso)) {

            if (prep.validarCadena(paso)) {

                if (!prep.verifyNameTitle(paso)) {

                    if (img != null && !img.isEmpty()) {
                        createSteps(img, texto, paso);
                        System.out.println("Si agrega");
                    } else {
                        createSteps(null, texto, paso); // Tratamiento cuando la imagen está ausente o es nula
                    }
                    showMessage("success", "square-check-regular", "Paso guardado correctamente.", model);
                } else {
                    Step step = new Step(0, null, paso, "", texto);

                    model.addAttribute("dateElement", step);
                    showMessage("adMessage", "pen-to-square-regular", "El nombre del paso ya se encuentra registrado.",
                            model);
                }
            } else {
                Step step = new Step(0, null, paso, "", texto);

                model.addAttribute("dateElement", step);
                showMessage("adMessage", "triangle-exclamation-solid",
                        "No se permiten numeros ni caracteres especiales en las casillas de titulo.", model);
            }
        } else {
            Step step = new Step(0, null, paso, "", texto);

            model.addAttribute("dateElement", step);
            showMessage("adMessage", "pen-to-square-regular", "Hay datos vacios, por favor verifica.", model);

        }

        if (!prep.getStepsLis().isEmpty()) {
            model.addAttribute("ListStep", prep.getStepsLis());
        }

        model.addAttribute("flag", true);

        return "login"; // Cuidado
    }

    public void createSteps(MultipartFile file, String text, String title) {

        Step step = new Step();
        step.setTitle(title);
        step.setTextStep(text);
        if (file != null && !file.isEmpty()) {
            step.setRouteImg(prep.getRoute(file));
            prep.insertImg(file, step.getRouteImg());
        }

        prep.getStepsLis().add(step);

    }

    // metodo encargado de agarrar una posicion de la lista para modificar.
    @GetMapping({ "/modifyStep/{url}" })
    public String selectIndexOfSteps(Model model, @PathVariable String url) {
        model.addAttribute("flag", true);

        int index = prep.getIndexSteps(url);
        if (index != -1) {

            Step stepSlect = prep.getStepsLis().get(index);
            model.addAttribute("selectedStep", stepSlect);
            prep.setIndex(index);

        }
        return "Preparation/secondPre";
    }

    @PostMapping({ "/finishModify" })
    public String finishModify(Model model, @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam String texto, @RequestParam String paso) {
        model.addAttribute("flag", true);

        Step step = new Step();
        /*
         * if(img != null && !img.isEmpty()){
         * step.setRouteImg(prep.getRoute(img));
         * }
         */
        if (!(texto.trim().isEmpty() || paso.trim().isEmpty())) {

            step.setTextStep(texto);
            step.setTitle(paso);

            if (img != null && !img.isEmpty()) {
                String url = prep.getStepsLis().get(prep.getIndex()).getRouteImg();
                prep.deleteImage(url);
                step.setRouteImg(prep.getRoute(img));
                prep.insertImg(img, step.getRouteImg());
            }

            prep.getStepsLis().remove(prep.getIndex());
            prep.getStepsLis().add(prep.getIndex(), step);

            prep.setIndex(0);

            model.addAttribute("ListStep", prep.getStepsLis());
            showMessage("success", "square-check-regular", "Se ha modificado con exito..", model);
        } else {
            model.addAttribute("messageStep", "No se puede dejar espacios de textos vacios.");
            model.addAttribute("dateElement", step);
            showMessage("adMessage", "triangle-exclamation-solid", "No se puede dejar espacios de textos vacios.",
                    model);
        }

        return "Preparation/resultPrep";
    }

    @GetMapping({ "/deletePas/{paso}" })
    public String DeleteStep(@PathVariable String paso, Model model) {

        Step step = prep.getStepsLis().get(prep.getIndex());
        if (step.getRouteImg() != null && !step.getRouteImg().trim().isEmpty()) {
            prep.deleteImage(step.getRouteImg());
        }
        prep.getStepsLis().remove(prep.getIndex());

        prep.setIndex(0);

        if (!prep.getStepsLis().isEmpty()) {
            model.addAttribute("ListStep", prep.getStepsLis());
        }

        model.addAttribute("flag", true);
        showMessage("success", "square-check-regular", "Se ha eliminado correctamente el paso.", model);
        return "Preparation/resultPrep";
    }

    @GetMapping({ "/CancelModify" })
    public String cancelPost(Model model) {

        model.addAttribute("ListStep", prep.getStepsLis());
        model.addAttribute("flag", true);
        return "Preparation/secondPre";
    }

    @PostMapping("/modificationPrep")
    public String modifyPreparation(@RequestParam("img") MultipartFile file, @RequestParam String label,
            @RequestParam String time, @RequestParam String note, @RequestParam String warnings, Model model) {

        if (prep.validation(label, time, warnings)) {
            LocalTime local = getHour(time);
            Preparation preparation = getPreparation(label, local, file, note, warnings);
            if (!file.isEmpty()) {
                String date = prep.getPrep().getRouteImg();
                System.out.println(date);
                prep.deleteImage(date);
                prep.insertImg(file, preparation.getRouteImg());
            }

            prep.setPrep(preparation);
            model.addAttribute("flag", true);
            model.addAttribute("ListStep", prep.getStepsLis());

        } else {
            model.addAttribute("difLis", new PreparationService().getDifficulty());
            model.addAttribute("message", "Faltan datos por llenar, por favor llenalo.");
            showMessage("adMessage", "pen-to-square-regular", "Faltan datos por llenar", model);
        }

        return "Preparation/PreparationView";
    }

    @PostMapping({ "/sendPrep" })
    public String SendInformacion(Model model) {

        if (!prep.getStepsLis().isEmpty()) {
            // simulo que todo se fue a la base de datos
            System.out.println("se fue a la base de datos");
        } else {
            model.addAttribute("messageStep", "No hay pasos para enviar");
            model.addAttribute("flag", true);
        }

        return "Preparation/PreparationView";
    }

    public void showMessage(String type, String imgSelect, String message, Model model) {
        model.addAttribute("class", type);
        model.addAttribute("type", imgSelect);
        model.addAttribute("message", message);
    }
}
