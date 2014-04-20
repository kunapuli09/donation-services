package com.starpath.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class TermPledgeStrategy implements PledgeStrategy {
	//DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

	public void choosePledge(Pledge pledge) {
		PaymentDetail paymentDetail = null;
		List<PaymentDetail> detailList = new ArrayList<PaymentDetail>();
		GregorianCalendar gc = new GregorianCalendar();		
		gc.setTime(pledge.getStartDate());
		int numberOfTimes = pledge.getPaymentFrequency();
		double amountPerTerm = pledge.getPledgeAmount() / numberOfTimes;
		for (int i = 0; i < numberOfTimes; i++) {
			if (pledge.getPaymentFrequencyType().equals(
					PaymentFrequencyType.MONTHLY.name())) {
				gc.add(Calendar.MONTH, i);				
			} else if (pledge.getPaymentFrequencyType().equals(
					PaymentFrequencyType.YEARLY.name())) {
				gc.add(Calendar.YEAR, i);
			}
			paymentDetail = new PaymentDetail();
			paymentDetail.setPaymentDate(gc.getTime());
			paymentDetail.setCreated(new Date());
			paymentDetail.setPaymentAmount(amountPerTerm);
			detailList.add(paymentDetail);
		}
		pledge.addMorePaymentDetails(detailList);
	}

}
