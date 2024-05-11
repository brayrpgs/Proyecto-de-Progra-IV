package cr.ac.una.booleanKitchen.Controller;

import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        // model.addAttribute("dataColum",);
        // model.addAttribute("dataDB", new AccesDataShoplist().showAll());
        // template
        return "shoplist/panel";
    }

    @GetMapping("/insert")
    public String paneShoplistAdd(Model model) {
        // template
        return "shoplist/insert";
    }

    @PostMapping("/insertShoplist")
    public String insertShopList(@RequestParam String name, @RequestParam float amount, @RequestParam String notes,
            @RequestParam String brand, @RequestParam Boolean state, @RequestParam String date) {
        ShopList shopList = new ShopList();
        shopList.setName(name);
        shopList.setAmount(amount);
        shopList.setNotes(notes);
        shopList.setBrand(brand);
        shopList.setState(state);
        shopList.setDate(Date.valueOf(date));
        System.out.println(shopList.getDate().toString());
        return "redirect: /shoplist/panel";
    }
}