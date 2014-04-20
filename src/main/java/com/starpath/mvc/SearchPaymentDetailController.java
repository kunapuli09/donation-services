package com.starpath.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.User;
import com.starpath.service.PledgeService;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class SearchPaymentDetailController extends SimpleFormController {

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Start Code Added by Swetha
		User user = new User();
		String email = request.getParameter("email");
		if (StringUtils.isNotEmpty(email)) {

			User memberUser = pledgeService.findUserByEmail(email);

			if (memberUser.getRoles().size() == 1) {
				List<PaymentDetail> paymentDetails = pledgeService
						.searchPaymentDetails(memberUser.getLastName());
				return new ModelAndView("searchPaymentDetail",
						"paymentDetails", paymentDetails);
			}
		}
		// End Code Added by Swetha

		String sLastName = request.getParameter("lastName");
		List<PaymentDetail> paymentDetails = pledgeService
				.searchPaymentDetails(sLastName);
		return new ModelAndView("searchPaymentDetail", "paymentDetails",
				paymentDetails);

	}

	// injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}
}

