package mx.com.cinepolis.scheduler.facade;

import mx.com.arquitectura.base.model.UserDO;
import mx.com.cinepolis.scheduler.commons.to.CatalogsTO;
import mx.com.cinepolis.scheduler.commons.to.GitHubUserTO;
import mx.com.cinepolis.scheduler.commons.to.UserTO;

import mx.com.cinepolis.scheduler.service.CatalogService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.List;

@Stateless
@LocalBean
public class CatalogFacadeEJB {

    @Inject
    CatalogService catalogService;

    public UserTO getSimpleUser()
    {
        return catalogService.getUser();
    }
    
    public List<CatalogsTO> getEstadosList(String pais){
    	
    	return catalogService.getEstados(pais);
    	
    }
    
    
    public GitHubUserTO getSimpleGitHubUser(String login)
    {
        return catalogService.getGitHubUser(login);
    }
    
    
    public GitHubUserTO postRequest(GitHubUserTO gitHubUserTO)
    {
        return catalogService.postRequest(gitHubUserTO);
    }

	public List<UserTO> getAllUsers() {
		return catalogService.getAllUsers();
	}

    public void registerGit(UserTO userTO)
    {
        catalogService.registerGit(userTO);
    }

}
