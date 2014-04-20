package com.starpath.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.starpath.domain.Pledge;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class PledgeDaoJpa extends GenericDaoJpa<Pledge, Long> implements PledgeDao {

   public PledgeDaoJpa() {}   

   public List<Pledge> findPledgeByUser(String lastName) {
	   Query q = null;
	   if(null != lastName && StringUtils.isNotEmpty(lastName)){
		   q = entityManager.createQuery("select p from Pledge p where p.user.lastName like :lastName");
		   q.setParameter("lastName",lastName +"%");
	   }else{
		   q = entityManager.createQuery("select p from Pledge p");
	   }
      return q.getResultList();
   } 

}
