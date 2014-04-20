package com.starpath.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springmodules.cache.annotations.Cacheable;

import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.User;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public interface PaymentDetailDao extends GenericDao<PaymentDetail,Long>{
	//@Cacheable(modelId="userCacheModel")
	  public List<PaymentDetail> getPaymentDetailsForDay(Date day);
	  public List<PaymentDetail> getPaymentDetailsForUser(String lastName);
}
 