/**
 * 
 */
package com.starpath.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.starpath.domain.User;
import com.starpath.domain.UserFamilyMember;

/**
 * @author Swetha Sankar
 *
 */
public class UserFamilyMemberValidator implements Validator {
	private static final String EMAIL_REQUIRED = "Email is required";
	private static final String PASSWORD_REQUIRED = "Password is required";
	private static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
	private static final String FIRSTNAME_REQUIRED = "First Name is required";
	private static final String LASTNAME_REQUIRED = "Last Name is required";
	private static final String PHONENUMBER_REQUIRED = "Phone Number is required";
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

		return clazz.equals(User.class);
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
	
	
	public void validateEmail(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email",
				"required.email", EMAIL_REQUIRED);
		User user = (User)command;		
		String email = user.getEmail();
		if(StringUtils.isNotEmpty(email))
		{
			if (!RegExValidator.validateEmail(email))
				errors.rejectValue("email",
						"invalid.email", EMAIL_INVALID);
		}
	}
	
	public void validatePassword(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "password",
				"required.password", PASSWORD_REQUIRED);		
	}

	public void validateConfirmPassword(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword",
				"required.confirmPassword", CONFIRM_PASSWORD_REQUIRED);
		User user = (User)command;	
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword))
		{
			if (!RegExValidator.validateConfirmpassword(password, confirmPassword))
				errors.rejectValue("confirmPassword",
						"invalid.confirmpassword", CONFIRM_PASSWORD_INVALID);
		}
	}

	public void validateFirstName(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstName",
				"required.firstName", FIRSTNAME_REQUIRED);
		User user = (User)command;		
		String firstName = user.getFirstName();
		if(StringUtils.isNotEmpty(firstName))
		{
			if (!RegExValidator.validateFirstName(firstName))
				errors.rejectValue("firstName",
						"invalid.firstName", FIRSTNAME_INVALID);
		}
	}

	public void validateLastName(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "lastName",
				"required.lastName", LASTNAME_REQUIRED);
		User user = (User)command;	
		String lastName = user.getLastName();
		if(StringUtils.isNotEmpty(lastName))
		{
			if (!RegExValidator.validateLastName(lastName))
				errors.rejectValue("lastName",
					"invalid.lastName", LASTNAME_INVALID);
		}
	}

	public void validatePhoneNumber(Object command, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				"required.phoneNumber", PHONENUMBER_REQUIRED);
		User user = (User)command;		
		String phoneNumber = user.getPhoneNumber();
		if(StringUtils.isNotEmpty(phoneNumber))
		{
			if (!RegExValidator.validatePhone(phoneNumber))
				errors.rejectValue("phoneNumber",
						"invalid.phoneNumber", PHONE_INVALID);
		}
	}
	public void validateAddress(Object command, Errors errors) {
		
		User user = (User)command;		
		String address = user.getAddress().getAddress();
		if(StringUtils.isNotEmpty(address))
		{
			if (!RegExValidator.validateAddress(address))
				errors.rejectValue("address.address",
						"invalid.address", ADDRESS_INVALID);
		}
	}
	public void validateCity(Object command, Errors errors) {
		
		User user = (User)command;	
		String city = user.getAddress().getCity();
		if(StringUtils.isNotEmpty(city))
		{
			if (!RegExValidator.validateCity(city))
				errors.rejectValue("address.city",
						"invalid.city", CITY_INVALID);
		}
	}
	public void validateState(Object command, Errors errors) {
		
		User user = (User)command;			
		String state = user.getAddress().getProvince();
		if(StringUtils.isNotEmpty(state))
		{
			if (!RegExValidator.validateState(state))
				errors.rejectValue("address.province",
						"invalid.state", STATE_INVALID);
		}
	}
	public void validateZip(Object command, Errors errors) {
	
		User user = (User)command;			
		String zip = user.getAddress().getPostalCode();
		if(StringUtils.isNotEmpty(zip))
		{
			if (!RegExValidator.validateZip(zip))
				errors.rejectValue("address.postalCode",
						"invalid.postalCode", ZIPCODE_INVALID);
		}
	}
	
}
