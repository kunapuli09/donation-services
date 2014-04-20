package com.starpath.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import com.starpath.domain.PaymentDetail;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class PaymentDetailDaoJpa extends GenericDaoJpa<PaymentDetail, Long>
		implements PaymentDetailDao {
	@SuppressWarnings("unchecked")
	public List<PaymentDetail> getPaymentDetailsForDay(Date day) {
		Query q = entityManager
				.createQuery("select r from PaymentDetail r where r.postedDate=?1");
		q.setParameter(1, day);
		return q.getResultList();
	}

	public List<PaymentDetail> getPaymentDetailsForUser(String lastName) {
		Query q = null;
		if (null != lastName && StringUtils.isNotEmpty(lastName)) {
			q = entityManager.createQuery("select pd from PaymentDetail pd, Pledge p, User u where pd.pledge=p.id and p.user=u.id and u.lastName like :lastName");
			q.setParameter("lastName",lastName +"%");
		} else {
			q = entityManager.createQuery("select pd from PaymentDetail pd");
		}

		return q.getResultList();
	}

}
