package cr.ac.una.booleanKitchen.Controller;

import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cr.ac.una.booleanKitchen.domain.ShopList;
import cr.ac.una.booleanKitchen.service.IServiceShoplist;
import cr.ac.una.booleanKitchen.service.ServiceShopList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author BrayRPGs
 */
@Controller
@RequestMapping("/shoplist")
public class ShoplistController {

    @Autowired
    private IServiceShoplist jpa;

    @GetMapping("/panel")
    public String paneShoplist(Model model) {
        // contexto
        new ServiceShopList().setOptionsPane(model, null, jpa);

        // template
        return "shoplist/panel";
    }

    @GetMapping("/panel/{numPage}")
    public String paneShoplist(Model model, @PathVariable Integer numPage) {
        // contexto
        new ServiceShopList().setOptionsPane(model, numPage, jpa);
        // vista
        return "shoplist/panel";
    }

    @PostMapping("/insertShoplist")
    public String insertShopList(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam float amount, @RequestParam String notes,
            @RequestParam String brand, @RequestParam Boolean state, @RequestParam String date) {
        System.out.println(id);
        ShopList shopList = new ShopList(id, name, amount, notes, brand, state, Date.valueOf(date), 01);

        jpa.save(shopList);
        return "redirect:/shoplist/panel";
    }

    @PostMapping("/deleteById")
    public String deleteShopList(@RequestParam Integer id) {
        jpa.delete(id);
        return "redirect:/shoplist/panel";
    }

    @GetMapping("/update")
    public String updateView() {
        return "/shoplist/update";
    }

    @GetMapping("/find")
    public String find(@RequestParam String data, Model model) {
        //lista las validaciones de busqueda
        model.addAttribute("dataDB", jpa.search(data));
        return "shoplist/search";
    }

}
