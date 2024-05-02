package cr.ac.una.booleanKitchen.service;

import java.lang.reflect.Field;
import java.util.LinkedList;

import cr.ac.una.booleanKitchen.Data.AccesDataShoplist;
import cr.ac.una.booleanKitchen.domain.ShopList;

/**
 *
 * @author BrayRPGs
 */

public class ShoplistService {

    public LinkedList<ShopList> showAll() {
        return new AccesDataShoplist().showAll();
    }

    public LinkedList<String> dataTableCrud() {
        LinkedList<String> dataColum = new LinkedList<>();
        for (Field declaredField : ShopList.class.getDeclaredFields()) {
            dataColum.add(declaredField.getName().toUpperCase());
        }
        return dataColum;
    }

    public boolean insert(ShopList data) {
        return new AccesDataShoplist().insert(data);
    }

    public boolean delete(int id) {
        return new AccesDataShoplist().delete(id);
    }

    public ShopList select(int id) {
        return new AccesDataShoplist().select(id);
    }

    public boolean update(ShopList data) {
        return new AccesDataShoplist().update(data);
    }
}
