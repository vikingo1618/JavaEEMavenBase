package mx.com.cinepolis.scheduler.data.persistence.dao;

import mx.com.arquitectura.arquitectura.persistence.BaseDAOJPA;
import mx.com.arquitectura.base.model.UserDO;
import java.util.List;

public interface UserDAO extends BaseDAOJPA<UserDO, Long> {

    /**
     * Modificar bandera de activo del userDO.
     *
     * @param userId User ID
     */
    void deleteUserDO(Long userId);

    /**
     * Get an active User by its userName
     *
     * @param userName User username
     * @return Return an active user
     */
    UserDO findUserActive(String userName);

    /**
     * Get the list of the users for paging
     *
     * @param pageIndex Paginated list index
     * @param elements  Number of elements required
     */
    List<UserDO> getPagedUsers(Integer pageIndex, Integer elements, String filter);

    /**
     * Get the total number of users
     *
     * @return Total number of users.
     */
    Integer countUsersForPaging(String filter);
}
