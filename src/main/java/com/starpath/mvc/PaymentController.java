package com.starpath.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.starpath.domain.InvalidEmailException;
import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.PledgeType;
import com.starpath.domain.User;
import com.starpath.domain.UserAlreadyExistsException;
import com.starpath.service.PledgeService;
import com.starpath.util.UserValidator;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

public class PaymentController extends AbstractWizardFormController {
	public PaymentController() {
		setCommandClass(PaymentDetail.class);
		setCommandName("paymentDetail");
	}

	@Override
	protected boolean isCancelRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.isCancelRequest(request);
	}

	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		PaymentDetail paymentDetail = (PaymentDetail) command;
		Map refData = new HashMap();
		String line2 = "";
		String line1 = "";
		String line3 = "";
		String line4 = "";
		if (page == 2) {
			if (paymentDetail.getPledge().getPaymentFrequency() == 0) {

				line1 = "Total Pledge Amount is "
						+ paymentDetail.getPledge().getPledgeAmount() + "\n";
				if (paymentDetail.getPaymentAmount() == null
						|| (paymentDetail.getPaymentAmount().doubleValue() <= 0)) {
					line2 = "You have chosen to pay the balance of "
							+ paymentDetail.getPledge().getPledgeAmount()
							+ " at a later time";
				} else {
					line2 = "Payment by cheque today is "
							+ paymentDetail.getPaymentAmount() + "\n";
				}
				refData.put("message", line1 + line2);
			} else {
				line1 = "Total Pledge Amount is "
						+ paymentDetail.getPledge().getPledgeAmount() + "\n";
				if (paymentDetail.getPaymentAmount() == null
						|| (paymentDetail.getPaymentAmount().doubleValue() <= 0)) {
					line2 = "You have chosen to pay the balance of "
							+ paymentDetail.getPledge().getPledgeAmount()
							+ " at a later time";
					line4 = " in "
							+ (paymentDetail.getPledge().getPaymentFrequency())
							+ "Monthly Terms";
				} else {
					line2 = "First payment by cheque today is "
							+ paymentDetail.getPaymentAmount() + "\n";
					line3 = "Your have chosen to pay the balance of "
							+ (paymentDetail.getPledge().getPledgeAmount() - paymentDetail
									.getPaymentAmount()) + "\n";
					line4 = " in "
							+ (paymentDetail.getPledge().getPaymentFrequency() - 1)
							+ "Monthly Terms";
				}

				refData.put("message", line1 + line2 + line3 + line4);
			}
		}
		return refData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest arg0)
			throws Exception {
		User user = new User();
		Pledge pledge = new Pledge();
		pledge.setUser(user);
		PaymentDetail paymentDetail = new PaymentDetail();
		paymentDetail.setPledge(pledge);
		return paymentDetail;
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		PaymentDetail paymentDetail = (PaymentDetail) command;
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		PaymentDetail paymentDetail = (PaymentDetail) command;
		User user = pledgeService.findUserByEmail(paymentDetail.getPledge().getUser().getEmail());
		if(null != user){
			throw new UserAlreadyExistsException("User Exists");
		}
		try {
			pledgeService.addPaymentDetail(paymentDetail);
		} catch (Exception e) {
			throw new InvalidEmailException("Email Send Failure");
		}
		return new ModelAndView(getSuccessView(), "paymentDetail",
				paymentDetail);
	}
	@Override  
    protected ModelAndView processCancel(HttpServletRequest request,   
            HttpServletResponse response, Object command, BindException errors)   
            throws Exception {            
        return new ModelAndView("login", null);   
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
		PaymentDetail payment = (PaymentDetail) command;

		UserValidator validator = (UserValidator) getValidator();
		if (page == 0) {
			validator.validateEmail(payment, errors);
			validator.validatePassword(payment, errors);
			validator.validateConfirmPassword(payment, errors);
			validator.validateFirstName(payment, errors);
			validator.validateLastName(payment, errors);
			validator.validatePhoneNumber(payment, errors);
			validator.validateAddress(payment, errors);
			validator.validateCity(payment, errors);
			validator.validateState(payment, errors);
			validator.validateZip(payment, errors);
			
		} else if (page == 1) {
			validator.validatePledgeAmount(payment, errors);
			validator.validatePaymentAmount(payment, errors);
		}
	}

}
