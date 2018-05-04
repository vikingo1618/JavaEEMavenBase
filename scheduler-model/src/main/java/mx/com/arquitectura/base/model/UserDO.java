package mx.com.arquitectura.base.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CH_USER")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;
    
	@Column(name = "LOGIN")
    private String login;
    
    @Column(name = "AVATAR_URL")
    private String avatarUrl;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "FOLLOWERS")
    private int followers;
    
    @Column(name = "FOLLOWING")
    private int following;
    
    public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}
    
}

   