package mx.com.cinepolis.scheduler.service.impl;

import mx.com.cinepolis.scheduler.commons.to.UserTO;
import mx.com.cinepolis.scheduler.service.CatalogService;

public class CatalogServiceImpl implements CatalogService{

    /**
     * {@inheritDoc}
     */
    @Override
    public UserTO getUser() {
        UserTO userTO = new UserTO();
        userTO.setIdUser(1L);
        userTO.setName("Javier");
        userTO.setEmail("francisco.rodriguez@axity.com.mx");
        userTO.setUserName("jrodriguez");
        return userTO;
    }
}
