/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.data.ShoplistJPA;
import cr.ac.una.booleanKitchen.domain.ShopList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author BrayRPGs
 */
@Service
@Primary
public class ServiceShopList implements IServiceShoplist {

    @Autowired
    private ShoplistJPA jpa;

    @Override
    public Boolean save(ShopList s) {
        jpa.save(s);
        return true;
    }

    public ServiceShopList() {
    }

    @Override
    public Page<ShopList> getAll(Integer numPage) {
        Pageable pageable = PageRequest.of(numPage, 10);
        return jpa.findAll(pageable);
    }

    public void setOptions(Model model, Integer numPage, IServiceShoplist jpa) {
        //nombres de las columnas 
        String[] dataColum = {"Nombre","Cantidad","Notas","Marca","Estado","Fecha"};
        model.addAttribute("dataColum",dataColum);
        Page<ShopList> page;
        int totalPages;
        if (numPage != null) {
            //datos paginados
            page = jpa.getAll(numPage);
            model.addAttribute("dataDB", jpa.getAll(numPage).toList());
            //la pagina
            model.addAttribute("page", page);
            //cantidad paginas
            totalPages = page.getTotalPages();
            model.addAttribute("totalPages", totalPages);
            //pagina actual
            model.addAttribute("numPage", numPage);
        }
        else {
            //datos paginados
            page = jpa.getAll(0);
            model.addAttribute("dataDB", jpa.getAll(0).toList());
            model.addAttribute("page", page);
            //cantidad paginas
            totalPages = page.getTotalPages();
            model.addAttribute("totalPages", totalPages);
            //pagina actual
            model.addAttribute("numPage", 0);
        }
    }

}
