package com.starpath.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.User;
import com.starpath.service.PledgeService;
import com.starpath.util.UserValidator;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

public class UserRegistrationController extends AbstractWizardFormController {
	public UserRegistrationController() {
		setCommandClass(User.class);
		setCommandName("user");
	}

	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		User user = (User) command;
		Map refData = new HashMap();
		if (page == 1 && request.getParameter("_target1") != null) {
			refData.put("nextPledge", user.getPledges().size() - 1);
			refData.put("nextPayment", user.getPledges().size() - 1);
		}
		return refData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest arg0)
			throws Exception {
		User userForm = new User();
		Set<Pledge> pledges = new HashSet<Pledge>();
		Pledge pledge = new Pledge();
		Set<PaymentDetail> paymentDetails = new HashSet<PaymentDetail>();
		paymentDetails.add(new PaymentDetail());
		pledge.setPaymentDetails(paymentDetails);
		pledges.add(pledge);
		userForm.addPledge(pledge);
		return userForm;
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		/*
		 * User user = (User)command; if(page ==1 &&
		 * request.getParameter("_target1") != null){ Set<PaymentDetail>
		 * paymentDetails = new HashSet<PaymentDetail>(); paymentDetails.add(new
		 * PaymentDetail()); Pledge pledge = new Pledge();
		 * pledge.setPaymentDetails(paymentDetails);
		 * user.getPledges().add(pledge);
		 * 
		 * }
		 */
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		User user = (User) command;
		pledgeService.addUser(user);
		return new ModelAndView(getSuccessView(), "user", user);
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return new ModelAndView("home", null);
	}

	private String getSuccessView() {
		return getPages()[getPages().length - 1];
	}

	// injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}

	protected void validatePage(Object command, Errors errors, int page) {
		User validateUser = (User) command;
		UserValidator validator = (UserValidator) getValidator();
		if (page == 0) {
			validator.validateEmail(validateUser, errors);
			validator.validatePassword(validateUser, errors);
			validator.validateConfirmPassword(validateUser, errors);
			validator.validateFirstName(validateUser, errors);
			validator.validateLastName(validateUser, errors);
			validator.validatePhoneNumber(validateUser, errors);
			validator.validateAddress(validateUser, errors);
			validator.validateCity(validateUser, errors);
			validator.validateState(validateUser, errors);
			validator.validateZip(validateUser, errors);
		} else if (page == 1) {
			validator.validatePledgeAmount(validateUser, errors);
			validator.validatePaymentAmount(validateUser, errors);
		}
	}

}
