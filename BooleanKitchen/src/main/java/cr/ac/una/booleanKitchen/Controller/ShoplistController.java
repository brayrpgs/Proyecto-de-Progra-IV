package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.domain.ShopList;
import cr.ac.una.booleanKitchen.service.IServiceShoplist;
import cr.ac.una.booleanKitchen.service.ServiceShopList;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    ///////////api rest///////////
    @PostMapping("/guardar")
    public ResponseEntity<String> save(@RequestBody ShopList s){
        boolean status = jpa.save(s);
        String quest;
        if(!status){
            quest = "save fail";
            return new ResponseEntity<>(quest, HttpStatus.NOT_ACCEPTABLE);
        }
         quest = "save success";
        return new ResponseEntity<>(quest, HttpStatus.CREATED);
    }
    
    @GetMapping("/mostrar/{page}")
    public ResponseEntity<List<ShopList>> show(@PathVariable int page){
        return new ResponseEntity<>(jpa.getAll(page).toList(),HttpStatus.OK);
    }
    
    //TODO
    @DeleteMapping("/eliminareliminareliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        if(jpa.delete(id)){
            return new ResponseEntity<>("delete success",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("delete fail",HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> update(@RequestBody ShopList s){
        String quest;
        boolean status = jpa.save(s);
        if(!status){
            quest = "update fail";
            return new ResponseEntity<>(quest, HttpStatus.NOT_ACCEPTABLE);
        }
         quest = "update success";
        return new ResponseEntity<>(quest, HttpStatus.CREATED);
    }
}
