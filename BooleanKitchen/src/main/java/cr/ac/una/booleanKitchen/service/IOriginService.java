package cr.ac.una.booleanKitchen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import cr.ac.una.booleanKitchen.domain.Origin;

public interface IOriginService {
    
    public void crearOrigen(Origin origin);

    public List<Origin> leerOrigen();
    
    public Origin getOrigen(String identificador);

    public void EliminarOrigen(int codigo);

    public Page<Origin> getPageOrigin(Pageable pageable);

    
    
}

