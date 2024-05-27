
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Recipe;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@Primary
public class serviceReceta implements IRecetaService {

    @Autowired
    private RecetaRepository repoReceta;

    @Override
    public void Guardar(Recipe receta) {
        receta.setImage(receta.getImage().replace(":", "-"));
        repoReceta.save(receta);
    }


    @Override
    public Recipe findByIdentificador(String identificador) {
        System.out.println("\niden: " + identificador);
        return repoReceta.findOneRecipe(identificador);
    }

    @Override
    public void eliminar(Recipe receta) {
        repoReceta.delete(receta);
    }

    @Override
    public boolean DeleteRecipe(String idSerial) {
        try {
            repoReceta.deleteStepsByRecetaIdentificador(idSerial);
            repoReceta.deletePreparacionByRecetaIdentificador(idSerial);
            repoReceta.deleteByIdentificador(idSerial);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Recipe> findTop10ByOrderByCalificacionDesc() {
        return repoReceta.findTop10ByOrderByCalificacionDesc();
    }

    @Override
    public Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return repoReceta.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Recipe> paginacionRecetas(Pageable pageable) {
        return repoReceta.findAll(pageable);
    }

}
