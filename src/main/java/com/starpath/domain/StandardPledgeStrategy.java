package com.starpath.domain;

import java.util.Date;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class StandardPledgeStrategy implements PledgeStrategy {

	public void choosePledge(Pledge pledge) {
		//Add Just one paymentdetail
		PaymentDetail paymentDetails = new PaymentDetail();
		paymentDetails.setCreated(new Date());
		paymentDetails.setPaymentDate(pledge.getStartDate());
		paymentDetails.setPaymentAmount(pledge.getPledgeAmount());
		pledge.addPaymentDetails(paymentDetails);
	}

}
