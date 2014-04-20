package com.starpath.dao;

import java.util.List;


import com.starpath.domain.UserFamilyMember;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Swetha Sankar
 * <p>
 *   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
 * <br>
 */
public interface UserFamilyMemberDao extends GenericDao<UserFamilyMember, Long> {
	public List<UserFamilyMember> findMembersByUserId(Long id);
	public List<UserFamilyMember> getUserFamilyMembers(String lastName);
	public UserFamilyMember findFamilyMemberByMemberId(Long id);
	}
