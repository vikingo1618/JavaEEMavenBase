package mx.com.cinepolis.scheduler.data.persistence.dao.impl;

import mx.com.arquitectura.arquitectura.persistence.impl.BaseDAOImpl;
import mx.com.arquitectura.base.model.UserDO;
import mx.com.cinepolis.scheduler.data.persistence.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author jorge.lopez
 */
public class UserDAOImpl extends BaseDAOImpl<UserDO, Long> implements UserDAO {

    private static final String QUERY_USER_DO_FIND_ACTIVE_BY_DS_USERNAME = "UserDO.findActiveByDsUsername";
    private static final String QUERY_COUNT_USERS = "UserDO.countUsers";
    private static final String QUERY_FIND_USERS = "UserDO.findUsers";

    private static final String PARAM_USER_NAME = "userName";
    private static final String PARAM_FILTER = "filter";

    private static final String PERCENTAGE = "%";

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.arquitectura.arquitectura.persistence.impl.BaseDAOImpl#
     * setEntityManager(javax.persistence.EntityManager)
     */
    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUserDO(Long userId) {

        UserDO userDO = this.getById(userId);
        this.update(userDO);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDO findUserActive(String userName) {
        Query q = entityManager.createNamedQuery(QUERY_USER_DO_FIND_ACTIVE_BY_DS_USERNAME);
        q.setParameter(PARAM_USER_NAME, userName);
        UserDO response = new UserDO();
        return response;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<UserDO> getPagedUsers(Integer pageIndex, Integer elements, String filter) {
        Query q = entityManager.createNamedQuery(QUERY_FIND_USERS);
        q.setFirstResult(elements * pageIndex);
        q.setMaxResults(elements);

        if (filter != null) {
            filter = PERCENTAGE + filter + PERCENTAGE;
        } else {
            filter = PERCENTAGE + PERCENTAGE;
        }
        q.setParameter(PARAM_FILTER, filter);

        return q.getResultList();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Integer countUsersForPaging(String filter) {
        TypedQuery<Long> q = entityManager.createNamedQuery(QUERY_COUNT_USERS, Long.class);

        if (filter != null) {
            filter = PERCENTAGE + filter + PERCENTAGE;
        } else {
            filter = PERCENTAGE + PERCENTAGE;
        }
        q.setParameter(PARAM_FILTER, filter);

        return q.getSingleResult().intValue();
    }
}
