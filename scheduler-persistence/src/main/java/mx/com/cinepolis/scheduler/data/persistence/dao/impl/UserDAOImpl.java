package mx.com.cinepolis.scheduler.data.persistence.dao.impl;

import mx.com.arquitectura.arquitectura.persistence.impl.BaseDAOImpl;
import mx.com.arquitectura.base.model.UserDO;
import mx.com.cinepolis.scheduler.data.persistence.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @author vikingo.leal
 */
public class UserDAOImpl extends BaseDAOImpl<UserDO, Long> implements UserDAO {


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

}
