/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;
import cr.ac.una.booleanKitchen.domain.ShopList;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author BrayRPGs
 */
public interface IServiceShoplist {

    public Boolean save(ShopList s);
    public Page<ShopList> getAll(Integer numPage);
    public Boolean delete(Integer id);
    public List<ShopList> search(String data);
    
    
}
