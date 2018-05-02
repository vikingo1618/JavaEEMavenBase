package mx.com.arquitectura.base.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "K_USER")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "DS_NAME")
    private String dsName;
    @Column(name = "DS_USERNAME")
    private String dsUsername;
    @Column(name = "DS_EMAIL")
    private String dsEmail;
    @Column(name = "FG_ACTIVE")
    private boolean activeU;
    @Column(name = "DT_LAST_MODIFICATION")
    private Date lastModificationDate;
    @Column(name = "ID_LAST_USER_MODIFIER")
    private Long lastModificationUserId;

    /**
     * Getter for idUser.
     *
     * @return
     */

    public Long getIdUser() {
        return idUser;
    }

    /**
     * Setter for idUser.
     *
     * @param idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * Getter for dsName.
     *
     * @return
     */

    public String getDsName() {
        return dsName;
    }

    /**
     * Setter for dsName.
     *
     * @param dsName
     */
    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    /**
     * Getter for dsUsername.
     *
     * @return
     */

    public String getDsUsername() {
        return dsUsername;
    }

    /**
     * Setter for dsUsername.
     *
     * @param dsUsername
     */
    public void setDsUsername(String dsUsername) {
        this.dsUsername = dsUsername;
    }

    /**
     * Getter for dsEmail.
     *
     * @return
     */

    public String getDsEmail() {
        return dsEmail;
    }

    /**
     * Setter for dsEmail.
     *
     * @param dsEmail
     */
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }
}
