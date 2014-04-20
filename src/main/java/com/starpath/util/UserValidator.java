package com.starpath.util;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Swetha Kunapuli
 * <p>
 *   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
 * <br>
 */

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.User;

public class UserValidator implements Validator {

	private static final String EMAIL_REQUIRED = "Email is required";
	private static final String PASSWORD_REQUIRED = "Password is required";
	private static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
	private static final String FIRSTNAME_REQUIRED = "First Name is required";
	private static final String LASTNAME_REQUIRED = "Last Name is required";
	private static final String PHONENUMBER_REQUIRED = "Phone Number is required";
	private static final String PAYMENTAMOUNT_REQUIRED = "Partial Payment is not allowed for ONE-TIME Payment Term";
	private static final String PLEDGEAMOUNT_LARGE_INVALID = "Large Pledge Amount is not Acceptable through Online System";
	private static final String PLEDGEAMOUNT_SMALL_INVALID = "Negative Or Zero Pledge Amount is not Acceptable";
	private static final String EMAIL_INVALID = "Email is not valid. Ex: john@john.com";
	private static final String PASSWORD_INVALID = "Password is not valid.";
	private static final String CONFIRM_PASSWORD_INVALID = "Confirm Password should be same as password.";
	private static final String FIRSTNAME_INVALID = "First Name is not valid.Ex: John";
	private static final String LASTNAME_INVALID = "Last Name is not valid.";
	private static final String ADDRESS_INVALID = "Address is not valid.";
	private static final String CITY_INVALID = "City is not valid. ";
	private static final String STATE_INVALID = "State is not valid.";
	private static final String ZIPCODE_INVALID = "Zipcode is not valid";
	private static final String PHONE_INVALID = "Phone Number is not valid.xxx-xxx-xxxx";
	private static final String STATE_REQUIRED = "State is required";
	private static final String ADDRESS_REQUIRED = null;
	public boolean supports(Class clazz) {

		return clazz.equals(PaymentDetail.class);
	}

	public void validate(Object command, Errors errors) {
		
		User user = (User)command;		
		String email = user.getEmail();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String phoneNumber = user.getPhoneNumber();
		String address = user.getAddress().getAddress();
		String city = user.getAddress().getCity();
		String state = user.getAddress().getProvince();
		String zip = user.getAddress().getPostalCode();
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.email",
				"required.email", EMAIL_REQUIRED);
		if(StringUtils.isNotEmpty(email))
		{
			if (!RegExValidator.validateEmail(email))
				errors.rejectValue("email",
					"invalid.email", EMAIL_INVALID);
		}
		
		ValidationUtils.rejectIfEmpty(errors, "password",
				"required.password", PASSWORD_REQUIRED);
		
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword",
				"required.confirmPassword", CONFIRM_PASSWORD_REQUIRED);
		
		if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword))
		{	
			if (!RegExValidator.validateConfirmpassword(password, confirmPassword))
				errors.rejectValue("confirmPassword",
					"invalid.confirmpassword", CONFIRM_PASSWORD_INVALID);
		}

		ValidationUtils.rejectIfEmpty(errors, "firstName",
				"required.firstName", FIRSTNAME_REQUIRED);
		
		if(StringUtils.isNotEmpty(firstName))
		{
			if (!RegExValidator.validateFirstName(firstName))
				errors.rejectValue("firstName",
						"invalid.firstName", FIRSTNAME_INVALID);
		}
		
		ValidationUtils.rejectIfEmpty(errors, "lastName",
				"required.lastName", LASTNAME_REQUIRED);
	
		if(StringUtils.isNotEmpty(lastName))
		{
			if (!RegExValidator.validateLastName(lastName))
				errors.rejectValue("lastName",
						"invalid.lastName", LASTNAME_INVALID);
		}
		
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				"required.phoneNumber", PHONENUMBER_REQUIRED);
		
		if(StringUtils.isNotEmpty(phoneNumber))
		{	
			if (!RegExValidator.validatePhone(phoneNumber))
				errors.rejectValue("phoneNumber",
						"invalid.phoneNumber", PHONE_INVALID);
		}
		if(StringUtils.isNotEmpty(address))
		{
			if (!RegExValidator.validateAddress(address))
				errors.rejectValue("address.address",
					"invalid.address", ADDRESS_INVALID);
		}
		if(StringUtils.isNotEmpty(city))
		{
			if (!RegExValidator.validateCity(city))
				errors.rejectValue("address.city",
						"invalid.city", CITY_INVALID);
		}
		if(StringUtils.isNotEmpty(state))
		{
			if (!RegExValidator.validateState(state))
				errors.rejectValue("address.province",
					"invalid.state", STATE_INVALID);
		}
		if(StringUtils.isNotEmpty(zip))
		{
			if (!RegExValidator.validateZip(zip))
			errors.rejectValue("address.postalCode",
					"invalid.zip", ZIPCODE_INVALID);
		}
	}

	public void validateEmail(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.email",
				"required.email", EMAIL_REQUIRED);
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String email = paymentDetail.getPledge().getUser().getEmail();
		if(StringUtils.isNotEmpty(email))
		{
			if (!RegExValidator.validateEmail(email))
				errors.rejectValue("pledge.user.email",
						"invalid.email", EMAIL_INVALID);
		}
	}	
	public void validatePassword(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.password",
				"required.password", PASSWORD_REQUIRED);		
	}

	public void validateConfirmPassword(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.confirmPassword",
				"required.confirmPassword", CONFIRM_PASSWORD_REQUIRED);
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String password = paymentDetail.getPledge().getUser().getPassword();
		String confirmPassword = paymentDetail.getPledge().getUser().getConfirmPassword();
		if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword))
		{
			if (!RegExValidator.validateConfirmpassword(password, confirmPassword))
				errors.rejectValue("pledge.user.confirmPassword",
						"invalid.confirmpassword", CONFIRM_PASSWORD_INVALID);
		}
	}

	public void validateFirstName(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.firstName",
				"required.firstName", FIRSTNAME_REQUIRED);
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String firstName = paymentDetail.getPledge().getUser().getFirstName();
		if(StringUtils.isNotEmpty(firstName))
		{
			if (!RegExValidator.validateFirstName(firstName))
				errors.rejectValue("pledge.user.firstName",
						"invalid.firstName", FIRSTNAME_INVALID);
		}
	}

	public void validateLastName(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.lastName",
				"required.lastName", LASTNAME_REQUIRED);
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String lastName = paymentDetail.getPledge().getUser().getLastName();
		if(StringUtils.isNotEmpty(lastName))
		{
			if (!RegExValidator.validateLastName(lastName))
				errors.rejectValue("pledge.user.lastName",
					"invalid.lastName", LASTNAME_INVALID);
		}
	}

	public void validatePhoneNumber(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pledge.user.phoneNumber",
				"required.phoneNumber", PHONENUMBER_REQUIRED);
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String phoneNumber = paymentDetail.getPledge().getUser().getPhoneNumber();
		if(StringUtils.isNotEmpty(phoneNumber))
		{
			if (!RegExValidator.validatePhone(phoneNumber))
				errors.rejectValue("pledge.user.phoneNumber",
						"invalid.phoneNumber", PHONE_INVALID);
		}
	}
	public void validateAddress(Object command, Errors errors) {
		
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String address = paymentDetail.getPledge().getUser().getAddress().getAddress();
		if(StringUtils.isNotEmpty(address))
		{
			if (!RegExValidator.validateAddress(address))
				errors.rejectValue("pledge.user.address.address",
						"invalid.address", ADDRESS_INVALID);
		}
	}
	public void validateCity(Object command, Errors errors) {
		
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String city = paymentDetail.getPledge().getUser().getAddress().getCity();
		if(StringUtils.isNotEmpty(city))
		{
			if (!RegExValidator.validateCity(city))
				errors.rejectValue("pledge.user.address.city",
						"invalid.city", CITY_INVALID);
		}
	}
	public void validateState(Object command, Errors errors) {
		
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String state = paymentDetail.getPledge().getUser().getAddress().getProvince();
		if(StringUtils.isNotEmpty(state))
		{
			if (!RegExValidator.validateState(state))
				errors.rejectValue("pledge.user.address.province",
						"invalid.state", STATE_INVALID);
		}
	}
	public void validateZip(Object command, Errors errors) {
	
		PaymentDetail paymentDetail = (PaymentDetail)command;		
		String zip = paymentDetail.getPledge().getUser().getAddress().getPostalCode();
		if(StringUtils.isNotEmpty(zip))
		{
			if (!RegExValidator.validateZip(zip))
				errors.rejectValue("pledge.user.address.postalCode",
						"invalid.postalCode", ZIPCODE_INVALID);
		}
	}
	public void validatePledgeAmount(Object command, Errors errors) {
		double amount = 0.0d;
		ValidationUtils.rejectIfEmpty(errors, "pledge.pledgeAmount",
				"required.pledgeAmount", "Pledge Amount is required");
		PaymentDetail paymentDetail = (PaymentDetail) command;
		Pledge pledge = paymentDetail.getPledge();
		if (null != pledge && pledge.getPledgeAmount() != null)
			amount = pledge.getPledgeAmount().doubleValue();
		if (amount <= 0.0) {
			errors.rejectValue("pledge.pledgeAmount",
					"invalid.nagativePledgeAmount", PLEDGEAMOUNT_SMALL_INVALID);
		}
		if (amount > 25000.0) {
			errors.rejectValue("pledge.pledgeAmount",
					"invalid.largePledgeAmount", PLEDGEAMOUNT_LARGE_INVALID);
		}

	}

	public void validatePaymentAmount(Object command, Errors errors) {
		PaymentDetail paymentDetail = (PaymentDetail) command;
		if (paymentDetail.getPledge().getPaymentFrequency() == 0) {
			if (null != paymentDetail.getPaymentAmount()
					&& paymentDetail.getPaymentAmount().doubleValue() != paymentDetail
							.getPledge().getPledgeAmount().doubleValue()) {
				errors.rejectValue("paymentAmount", "invalid.pledge",
						PAYMENTAMOUNT_REQUIRED);
			}
		}
	}	
	
}
