package com.starpath.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.starpath.domain.User;

public class EditUserValidator implements Validator{
	private static final String EMAIL_REQUIRED = "Email is required";
	private static final String PASSWORD_REQUIRED = "Password is required";
	private static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
	private static final String FIRSTNAME_REQUIRED = "First Name is required";
	private static final String LASTNAME_REQUIRED = "Last Name is required";
	private static final String PHONENUMBER_REQUIRED = "Phone Number is required";
	private static final String EMAIL_INVALID = "Email is not valid. Ex: john@john.com";
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
	
	private static final String OTHER_INVALID = "Other data is not valid.";
	private static final String GOTHRAM_INVALID = "Gothram is not valid.";
	private static final String NAKSHATHRAM_INVALID = "Nakshathram is not valid.";
	
	public boolean supports(Class clazz) {

		return clazz.equals(User.class);
	}

	public void validate(Object command, Errors errors) {
		User user = (User)command;	
		
		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		String phoneNumber = user.getPhoneNumber();
		String address = user.getAddress().getAddress();
		String city = user.getAddress().getCity();
		String state = user.getAddress().getProvince();
		String zip = user.getAddress().getPostalCode();
		ValidationUtils.rejectIfEmpty(errors, "email",
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

}
