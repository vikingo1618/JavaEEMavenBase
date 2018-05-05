package mx.com.cinepolis.scheduler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.Entity;

import mx.com.arquitectura.base.model.UserDO;
import mx.com.cinepolis.scheduler.commons.to.CatalogsTO;
import mx.com.cinepolis.scheduler.commons.to.GitHubUserTO;
import mx.com.cinepolis.scheduler.commons.to.UserTO;
import mx.com.cinepolis.scheduler.data.persistence.dao.UserDAO;
import mx.com.cinepolis.scheduler.service.CatalogService;

public class CatalogServiceImpl implements CatalogService{
	
	@Inject
	private UserDAO userDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserTO getUser() {
        UserTO userTO = new UserTO();
        userTO.setIdUser(1L);
        userTO.setName("Javier");
        userTO.setAvatarUrl("");
        userTO.setLogin("jrodriguez");
        userTO.setFollowers(3);
        userTO.setFollowing(3);
        return userTO;
    }

	@Override
	public List<CatalogsTO> getEstados(String pais) {
		
		List<CatalogsTO> catalogsTOList=new ArrayList<>();
		
		if(pais.equals("Mexico")) {
			catalogsTOList.add(new CatalogsTO() {{
				setId(1);
				setName("Puebla");
			}});
			
			catalogsTOList.add(new CatalogsTO() {{
				setId(2);
				setName("Tlaxcala");
			}});
			
			catalogsTOList.add(new CatalogsTO() {{
				setId(3);
				setName("CDMX");
			}});
					
		}else if (pais.equals("USA")){
			
			catalogsTOList.add(new CatalogsTO() {{
				setId(1);
				setName("California");
			}});
			
			catalogsTOList.add(new CatalogsTO() {{
				setId(2);
				setName("New York");
			}});
			
			catalogsTOList.add(new CatalogsTO() {{
				setId(3);
				setName("Texas");
			}});
			
		}
		
		return catalogsTOList;
	}
	
    @Override
    public GitHubUserTO getGitHubUser(String login) {
        GitHubUserTO gitHubUserTO = new GitHubUserTO();
        
        if(login.equals("vikingo1618")) {
            gitHubUserTO.setAvatar("https://i.pinimg.com/originals/b4/80/af/b480af323093c05c4076563b790f7520.jpg");
            gitHubUserTO.setName("Victor");
            gitHubUserTO.setLogin("vikingo1618");
            gitHubUserTO.setFollowers(6);
            gitHubUserTO.setFollowing(3);
        }else if(login.equals("ponchito2012")){
            gitHubUserTO.setAvatar("");
            gitHubUserTO.setName("Maximiliano");
            gitHubUserTO.setLogin("ponchito2012");
            gitHubUserTO.setFollowers(123);
            gitHubUserTO.setFollowing(456);
        }
        return gitHubUserTO;
    }
    
    @Override
    public GitHubUserTO postRequest(GitHubUserTO gitHubUserTO) {
    	GitHubUserTO ghUserTO = new GitHubUserTO();
    	
    	ghUserTO.setAvatar(gitHubUserTO.getAvatar());
    	ghUserTO.setName(gitHubUserTO.getName());
    	ghUserTO.setLogin(gitHubUserTO.getLogin());
    	ghUserTO.setFollowers(gitHubUserTO.getFollowers());
    	ghUserTO.setFollowing(gitHubUserTO.getFollowing());
    	
    	return ghUserTO;
    	
    }

	@Override
	public List<UserTO> getAllUsers() {
		List<UserDO> userDOList = userDAO.findAll();
		
		return userDOList.stream().map(x -> new UserTO(){{
				setLogin(x.getLogin());
				setFollowing(x.getFollowing());
				setFollowers(x.getFollowers());
				setAvatarUrl(x.getAvatarUrl());
				setName(x.getName());
				setIdUser(x.getIdUser());	
			}}).collect(Collectors.toList());

	}
	
    @Override
    public void registerGit(UserTO ghUserTO) {
    	
    	UserDO ghUserDO = new UserDO();
    	
    	ghUserDO.setAvatarUrl(ghUserTO.getAvatarUrl());
    	ghUserDO.setName(ghUserTO.getName());
    	ghUserDO.setLogin(ghUserTO.getLogin());
    	ghUserDO.setFollowers(ghUserTO.getFollowers());
    	ghUserDO.setFollowing(ghUserTO.getFollowing());
    	
    	userDAO.create(ghUserDO);	
    }

}
