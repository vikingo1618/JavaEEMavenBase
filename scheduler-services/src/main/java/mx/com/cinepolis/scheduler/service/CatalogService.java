package mx.com.cinepolis.scheduler.service;

import mx.com.cinepolis.scheduler.commons.to.CatalogsTO;
import mx.com.cinepolis.scheduler.commons.to.UserTO;

import java.util.List;

/**
 * @author jrodriguez
 */
public interface CatalogService {

    /**
     *
     * @return User
     */
    UserTO getUser();
    /**
     * Retorna la lista de estados de un pa�s
     * @param pais acepta pa�s como string
     * @return Lista de estados
     */
    List<CatalogsTO> getEstados(String pais);

}
