package com.starpath.dao;

import java.util.List;

import javax.persistence.Query;

import com.starpath.domain.UserFamilyMember;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Swetha Sankar
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */
public class UserFamilyMemberDaoJpa extends	GenericDaoJpa<UserFamilyMember, Long> implements UserFamilyMemberDao {

	public List<UserFamilyMember> findMembersByUserId(Long id) {
		Query q = null;
		if (null != id) {
			q = entityManager
					.createQuery("select uf from UserFamilyMember uf where uf.user.id=?1");
			q.setParameter(1, id);
		} else {
			q = entityManager.createQuery("select uf from UserFamilyMember uf");
		}
		return q.getResultList();
	}

	public List<UserFamilyMember> getUserFamilyMembers(String lastName) {
		Query q = null;
		if (null != lastName) {
			q = entityManager
					.createQuery("select ufm from UserFamilyMember ufm where ufm.user.lastName like :lastName");
			q.setParameter("lastName", lastName + "%");
		} else {
			q = entityManager
					.createQuery("select ufm from UserFamilyMember ufm");
		}
		List matches = q.getResultList();
		return matches;
	}

	public UserFamilyMember findFamilyMemberByMemberId(Long id) {
		Query q = null;
		if (null != id) {
			q = entityManager
					.createQuery("select ufm from UserFamilyMember ufm where ufm.id=?1");
			q.setParameter(1, id);
		} else {
			q = entityManager
					.createQuery("select ufm from UserFamilyMember ufm");
		}
		UserFamilyMember userFamilyMember = (UserFamilyMember) q.getSingleResult();
		return userFamilyMember;
	}
}