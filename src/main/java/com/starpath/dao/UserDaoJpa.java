package com.starpath.dao;

import java.util.List;

import javax.persistence.Query;

import com.starpath.domain.User;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class UserDaoJpa extends GenericDaoJpa<User, Long> implements UserDao {

	public List<User> getUser(String lastName, String phoneNumber) {
		Query q = null;
		if (null != lastName && null != phoneNumber) {
			q = entityManager
					.createQuery("select d from User d where d.phoneNumber=?1 AND d.lastName like :lastName");
			q.setParameter(1, phoneNumber);
			q.setParameter("lastName",lastName +"%");
		} else if (null != lastName && null == phoneNumber) {
			q = entityManager
					.createQuery("select d from User d where d.lastName like :lastName");
			q.setParameter("lastName",lastName +"%");
		} else if (null == lastName && null != phoneNumber) {
			q = entityManager
					.createQuery("select d from User d where d.phoneNumber=?1");
			q.setParameter(1, phoneNumber);
		} else {
			q = entityManager.createQuery("select d from User d");
		}
		List matches = q.getResultList();
		System.out.println(matches.size());
		return matches;
	}

	public User getUserByEmail(String email) {

		Query q = entityManager
				.createQuery("select d from User d where d.email=?1");
		q.setParameter(1, email);
		List matches = q.getResultList();
		return (matches.size() > 0) ? (User) matches.get(0) : null;

	}

}
