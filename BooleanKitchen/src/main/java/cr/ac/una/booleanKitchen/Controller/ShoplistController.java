package cr.ac.una.booleanKitchen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cr.ac.una.booleanKitchen.Data.AccesDataShoplist;
import cr.ac.una.booleanKitchen.bussines.ShoplistService;
import cr.ac.una.booleanKitchen.domain.ShopList;

/**
 *
 * @author BrayRPGs
 */
@Controller
@RequestMapping("/shoplist")
public class ShoplistController {

    @GetMapping("/panel")
    public String paneShoplist(Model model) {
        // contexto
        model.addAttribute("dataColum", new ShoplistService().dataTableCrud());
        model.addAttribute("dataDB", new AccesDataShoplist().showAll());
        model.addAttribute("btnInsert", "/shoplist/insert");
        // template
        return "shoplist/panel";
    }

    @GetMapping("/insert")
    public String paneShoplistAdd(Model model) {
        // contexto
        // datos de los placeholder
        String placeholders[] = { "nombre", "cantidad", "notas", "marca", "estado" };
        model.addAttribute("dataInputs", placeholders);

        // datos de los names de las etiquetas
        String names[] = { "name", "amount", "notes", "brand", "state" };
        model.addAttribute("namesTags", names);

        // datos de los tipos de inputs
        String inputs[] = { "text", "text", "text", "text", "checkbox" };
        model.addAttribute("inputsTypes", inputs);
        // template
        return "shoplist/insert";
    }

    /**
     *
     * @param name
     * @param amount
     * @param notes
     * @param brand
     * @param state
     * @param idUser
     * @return
     */
    @PostMapping("/insertShoplist")
    public String insertDB(@RequestParam(value = "name") String name,
            @RequestParam(value = "amount") Float amount,
            @RequestParam(value = "notes") String notes,
            @RequestParam(value = "brand") String brand,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam("idUser") Integer idUser) {
        // hay que arreglar el parameto state porque viene con on deberia hacerlo el
        // logica luego
        Boolean stateNew = true;
        if (state == null) {
            stateNew = false;
        }

        // llenamos los registros
        ShopList data = new ShopList(null, name, amount, notes, brand, stateNew, idUser);

        // validamos que esten en orden todos los campos
        if (!new ShoplistService().insert(data)) {
            return "redirect:/error";
        }
        // redireccionamos si todo salio bien
        return "redirect:/paneShoplist";

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = { "/deleteShoplist" }, method = { RequestMethod.POST })
    public String deleteDB(@RequestParam Integer id) {
        // validamos que se realizen los cambios con exito
        if (!new ShoplistService().delete(id)) {
            return "redirect:/error";
        }
        // redireccionamos si todo salio bien
        return "redirect:/paneShoplist";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = { "/selectShoplist" }, method = { RequestMethod.POST })
    public String selectDB(@RequestParam Integer id, Model model) {
        // recolectamos el id para consultar los datos en la base de datos y los campos
        // de la tab
        // contexto
        ShopList dataBD = new ShoplistService().select(id);
        model.addAttribute("dataColum", new ShoplistService().dataTableCrud());
        model.addAttribute("dataDB", dataBD);
        // redireccionamos si todo salio bien
        return "./paneShoplist/paneShoplistUpdate";
    }

    @PostMapping("/updateShoplist")
    public String UpdateDB(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "amount") Float amount,
            @RequestParam(value = "notes") String notes,
            @RequestParam(value = "brand") String brand,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam("idUser") Integer idUser) {
        // hay que arreglar el parameto state porque viene con on deberia hacerlo el
        // logica luego
        Boolean stateNew = true;
        if (state == null) {
            stateNew = false;
        }
        System.out.println(id);
        // llenamos los registros
        ShopList data = new ShopList(id, name, amount, notes, brand, stateNew, idUser);

        // validamos que esten en orden todos los campos para actualizar
        if (!new ShoplistService().update(data)) {
            return "redirect:/error";
        }
        // redireccionamos si todo salio bien
        return "redirect:/paneShoplist";
    }
}
