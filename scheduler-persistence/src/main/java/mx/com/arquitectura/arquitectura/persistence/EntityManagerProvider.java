package mx.com.arquitectura.arquitectura.persistence;



import javax.persistence.EntityManager;

/**
 * The Interface EntityManagerProvider.
 *
 * @author jcorona
 * @since 9/11/2015 - 12:46:50 PM
 */
public interface EntityManagerProvider {

	/**
	 * Method signature set entity manager implementation 
	 *
	 * @param entityManager the new entity manager
	 */
	void setEntityManager(EntityManager entityManager);

	
}
