package mx.com.cinepolis.scheduler.commons.to;

/**
 * @author ohurtado
 */

public class UserTO {


    private Long idUser;

    private String name;

    private String userName;

    private String email;

    /**
     * @return
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
