package mx.com.arquitectura.arquitectura.persistence.impl;

import mx.com.arquitectura.arquitectura.persistence.BaseDAOJPA;
import mx.com.arquitectura.arquitectura.persistence.DAOError;
import mx.com.arquitectura.arquitectura.persistence.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Class template for Base dao impl.
 *
 * @author jcorona
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 * @since 9/11/2015 - 12:45:22 PM
 */

public abstract class BaseDAOImpl<T, I extends Serializable> // NOSONAR
		implements BaseDAOJPA<T, I> {// NOSONAR

	/** The logger. */
	public Logger logger = null;// NOSONAR

	/** Variable entity type. */
	private Class<T> entityType;
	

	/**
	 * Constructor base dao impl.
	 */
	
	public BaseDAOImpl() {
		entityType = this.getEntityClass();
		logger = LogManager.getLogger(entityType);

	}

	/*
	 * Metodo encargado de recuperar el entityManagerDefault
	 */

	/** Variable entity manager. */
	public EntityManager entityManager;// NOSONAR

	/**
	 * Method set entity manager.
	 *
	 * @param entityManager
	 *            the new entity manager
	 */
	// @Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Creates o persist entity.
	 *
	 * @param entity
	 *            the entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	public void create(T entity) throws DAOException {
		try {
			entityManager.persist(entity);						
		} catch (EntityExistsException e) {
			throw new DAOException(e, DAOError.ERR_DAO_RECORD_DUPLICATE, entity.getClass());
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, entity.getClass());
		}
	}
	
	/**
	 * Creates o persist entity.
	 *
	 * @param entitys
	 *            the entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	public void createBulk(List<T> entitys) throws DAOException {
		try {
			for (T t : entitys) {
				entityManager.persist(t);
			}
		} catch (EntityExistsException e) {
			throw new DAOException(e, DAOError.ERR_DAO_RECORD_DUPLICATE, this.getEntityClass());
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, this.getEntityClass());
		}
	}

	/**
	 * Delete by ID.
	 *
	 * @param id
	 *            the id
	 * @throws DAOException
	 *             the DAO exception
	 */

	public void delete(I id) throws DAOException {
		try {
			T reference = entityManager.getReference(entityType, id);
			entityManager.remove(reference);
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, entityType);
		}
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */

	public List<T> findAll() throws DAOException {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteria.createQuery(entityType);
		Root<T> root = query.from(entityType);
		query = query.select(root);
		TypedQuery<T> typeQ = entityManager.createQuery(query);
		return (List<T>) typeQ.getResultList();
	}

	
	/**
	 * Find all.
	 *
	 * @param nameColumn
	 *            the first result
	 * @param classId
	 *            the max result
	 * @param value
	 *            the order by
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public <T> T getByColumn(String nameColumn, Class<?> classId, Object value) throws DAOException {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery query = criteria.createQuery(entityType);
		Root<T> root = query.from(entityType);		
		query.where(criteria.equal(root.get(nameColumn), criteria.parameter(classId, nameColumn)));		
		TypedQuery<T> typeQ = entityManager.createQuery(query);
		typeQ.setParameter(nameColumn, value);
		return  typeQ.getSingleResult();
	}

	/**
	 * Find all.
	 *
	 * @param nameColumn
	 *            the first result
	 * @param classId
	 *            the max result
	 * @param value
	 *            the order by
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public <T> List<T> findByColumn(String nameColumn, Class<?> classId, Object... value) throws DAOException {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery query = criteria.createQuery(entityType);
		Root<T> root = query.from(entityType);		
		query.where(root.get(nameColumn).in(value));		
		TypedQuery<T> typeQ = entityManager.createQuery(query);
		//typeQ.setParameter(nameColumn, value);
		return typeQ.getResultList();
	}
	
	/**
	 * Find all.
	 *
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @param orderBy
	 *            the order by
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public List<T> findAll(Integer firstResult, Integer maxResult, String... orderBy) throws DAOException {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteria.createQuery(entityType);
		Root<T> root = query.from(entityType);
		query = query.select(root);
		TypedQuery<T> typeQ = entityManager.createQuery(query);
		typeQ.setFirstResult(firstResult);
		typeQ.setMaxResults(maxResult);
		return (List<T>) typeQ.getResultList();
	}

	/**
	 * Method get by id.
	 *
	 * @param id
	 *            the id
	 * @return by id
	 * @throws DAOException
	 *             the DAO exception
	 */

	public T getById(I id) throws DAOException {
		try {
			return entityManager.find(entityType, id);
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, entityType);
		}

	}
	
	/**
	 * Gets the catalog by id.
	 *
	 * @return the catalog by id
	 * @throws DAOException the DAO exception
	 */
	public T getCatalogById(I id)throws DAOException{
		
		try {
			return entityManager.getReference(entityType, id);
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, entityType);
		}
		
	}

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 * @throws DAOException
	 *             the DAO exception
	 */
	public T update(T entity) throws DAOException {
		try {
			return entityManager.merge(entity);
		} catch (IllegalArgumentException e) {
			throw new DAOException(e, DAOError.ERR_ENTITY_NOT_FOUND_GENERAL, entity.getClass());
		}

	}
	
	/* (non-Javadoc)
	 * @see mx.gob.arquitectura.arquitectura.persistence.BaseDAOJPA#flush()
	 */
	@Override
	public void flush() throws DAOException {
		entityManager.flush();
	}

	/* (non-Javadoc)
	 * @see mx.gob.arquitectura.arquitectura.persistence.BaseDAOJPA#clear()
	 */
	@Override
	public void clear() throws DAOException {
		entityManager.clear();
	}

	/* (non-Javadoc)
	 * @see mx.gob.arquitectura.arquitectura.persistence.BaseDAOJPA#flush()
	 */
	@Override
	public void refresh(T entity)  {
		entityManager.refresh(entity);
	}

	/* (non-Javadoc)
	 * @see mx.gob.arquitectura.arquitectura.persistence.BaseDAOJPA#reference()
	 */
	@Override
	public T getReference(Long id)  {
		return entityManager.getReference(entityType, id);
	}

	/* (non-Javadoc)
	 * @see mx.gob.arquitectura.arquitectura.persistence.BaseDAOJPA#reference()
	 */
	@Override
	public T getReference(Integer id)  {
		return entityManager.getReference(entityType, id);
	}

	
	/**
	 * Method get entity class.
	 *
	 * @return entity class
	 */
	private Class<T> getEntityClass() {

		Class<T> entityClass = null;
		try {
			ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			entityClass = (Class<T>) type.getActualTypeArguments()[0];
		} catch (Exception err) {
			logger.error("Error al encontrar el tipo parametrizado", err);
			entityClass = null;
		}

		return entityClass;
	}

	/**
	 * Throw if null.
	 *
	 * @param object
	 *            the object
	 * @param error
	 *            the error
	 * @param object
	 *            the mensaje
	 */
	protected void throwIfNull(Object object, DAOError error) {
		if (object == null) {
			throw new DAOException(error);
		}
	}
}
