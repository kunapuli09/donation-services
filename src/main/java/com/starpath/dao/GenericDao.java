package com.starpath.dao;
import java.io.Serializable;

import java.util.List;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/


/**
 * An interface for Data Access Objects.  Provides the interface for the minimum 
 * functionality for all DAOs*
    
 */
public interface GenericDao<T, ID extends Serializable>
{
	//~ Methods ----------------------------------------------------------------------------

	/**
	 * Returns a DVO based on a primary key lookup
	 *
	 * @param id the primary key to look up
	 *
	 * @return a DVO if a record with the primary key is found
	 */
	public T findById(ID id);

	/**
	 * Returns a list of all objects in the database of this particular DVO type
	 *
	 * @return a list of all objects retreived from the database
	 */
	public List<T> findAll();

	/**
	 * A utility function to make a DVO persistent.  The DVO will now be managed
	 * by the in scope persistence mechanism.  When flushed() or closed, the underlying
	 * persistence mechanism will store the persitent state of the object to the 
	 * associated table(s) in the underlying relational database.  See
	 * documentation on object states and persistence for the chosen persistence provider
	 *
	 * @param entity the entity to register with the persietence mechanism     
	 *
	 * @return a reference to the persistence managed entity       
	 */
	public T makePersistent(T entity);
	
	
	/**
	 * deletes the entity from the underlying datastore
	 * see EntityManager.remove() JPA documentation
	 */
	public void makeTransient(T entity);

	/**
	 * forces the underlying persistence mechanism to sync up with the database.  See
	 * documentation for the chosen persitence provider.
	 */
	public void flush();

	/**
	 * clears managed objects from the underlying persitence provider.  See documentation
	 * for the chosen persistence provider.  Warning:  And objects not persisted to the
	 * database will be cleared without saving their persistent state.
	 */
	public void clear();

}
