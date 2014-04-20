package com.starpath.dao;
import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.starpath.dao.GenericDao;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

/**
 * Implementation of the Generic DAO abstract class.  Provides DAO functionality
 * utilizing JPA as the underlying persitence provider.
 *
 * Note: The Entity Manager in this class would typically be injected for use
 * by the runtime container environment.  Spring provides an API for maintenance
 * and transactional correctness of injected entity managers.  Please read the
 * documentation on the Spring framework for details on how to correctly
 * inject and use entity managers in this context
 * 
 * @param <T> The class of the DVO this DAO supports       
 * @param <ID> The class of the primary key for the DVO       
 */
public abstract class GenericDaoJpa<T, ID extends Serializable>
	implements GenericDao<T, ID>, ApplicationContextAware
{
	//~ Instance fields --------------------------------------------------------------------

	//maintains the class of the associated DVO
	protected Class<T> persistentClass;

	//maintains the JPA entity Manager to be used for persistence calls.
	//Typically this item would be injected from the runtime container.  It
	//is assumed that the injected Entity Manager is thread safe and transactionally
	//correct
	protected EntityManager entityManager;
	
	//This will be injected by Spring if run in the Spring Framework 
	protected ApplicationContext applicationContext;
	
	private Log log = LogFactory.getLog(GenericDaoJpa.class);
	
	//~ Constructors -----------------------------------------------------------------------

	/**
	 * Creates a new GenericHibernateDAO object.
	 */
	public GenericDaoJpa()
	{
		//store the persistent class of the associated DVO
		this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	//~ Methods ----------------------------------------------------------------------------

	/**
	 * Sets the Entity Manager
	 *
	 * @param em the entity manager object
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	/**
	 * Returns the entity manager
	 *
	 * @return the entity manager associated with this DAO
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	//required for ApplicationContextAware interface
	public void setApplicationContext(ApplicationContext context) {
		this.applicationContext = context;
	}
	
	/**
	 * Returns application context
	 *
	 * @return the application context associated with this DAO
	 */
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}
	
	/**
	 * Returns the persisten class of the associated DVO for this DAO
	 *
	 * @return the class of the associated DVO       
	 */
	public Class<T> getPersistentClass()
	{
		return this.persistentClass;
	}

	/**
	 * Returns a DVO based on a primary key lookup
	 *
	 * @param id the primary key to look up     
	 *
	 * @return a DVO if a record with the primary key is found       
	 */
	@SuppressWarnings("unchecked")
	public T findById(ID id)
	{
		T entity;

		//use the entity manager to lookup the object by primary key
		entity = entityManager.find(getPersistentClass(), id);
		
		return entity;
	}

	/**
	 * Returns a list of all objects in the database of this particular DVO type
	 *
	 * @return a list of all objects retreived from the database       
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		String shortClassName = getPersistentClass().getSimpleName();
		String query = "select o from " + shortClassName + " o ";
		
		if (log.isDebugEnabled()) {
			log.debug("Looking up all " + persistentClass.getSimpleName() 
					+ " objects using query: " + query);
		}
		
		return (entityManager.createQuery(query).getResultList());
	}

	/**
	 * registers the entity with the entity manager (calls persist)
	 * see EntityManager.persist() JPA documentation
	 *
	 * @return the entity 
	 */
	@SuppressWarnings("unchecked")
	public T makePersistent(T entity)
	{
		entityManager.persist(entity);

		return entity;
	}

	/**
	 * deletes the entity from the underlying datastore
	 * see EntityManager.remove() JPA documentation
	 */
	public void makeTransient(T entity)
	{
		entityManager.remove(entity);
	}

	/**
	 * synchronizes the entity manager with the underlying database(s)
	 * see EntityManager.flush() JPA documentation
	 */
	public void flush()
	{
		entityManager.flush();
	}

	/**
	 * clears all entities from the underlying datastore
	 * see EntityManager.clear() JPA documentation
	 */
	public void clear()
	{
		entityManager.clear();
	}

}
