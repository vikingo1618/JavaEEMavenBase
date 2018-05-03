package mx.com.cinepolis.schedule.rest;

import mx.com.cinepolis.scheduler.commons.to.CatalogsTO;
import mx.com.cinepolis.scheduler.commons.to.UserTO;
import mx.com.cinepolis.scheduler.facade.CatalogFacadeEJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author jrodriguez
 */
@Path("/catalog")
public class CatalogRest {

    @EJB
    private CatalogFacadeEJB catalogFacadeEJB;

    @GET
    @Produces("application/json")
    @Path("/user")
    public Response getSimpleCatalog()
    {
        UserTO userTO = catalogFacadeEJB.getSimpleUser();
        return Response.ok().entity(userTO).build();
    }
    
    
    @GET
    @Produces("application/json")
    @Path("/country")
    public Response getEstados(@Context UriInfo ui) {
    	//country?pais={valor}, 
    	
    	MultivaluedMap<String,String> queryParams = ui.getQueryParameters();
    	String pais = queryParams.getFirst("pais");
    	
    	List<CatalogsTO> catalogsTOList=catalogFacadeEJB.getEstadosList(pais);
    	
    	GenericEntity<List<CatalogsTO>> entity =new GenericEntity<List<CatalogsTO>>(catalogsTOList){};
    	
    	return Response.ok().entity(entity).build();
    }

}

