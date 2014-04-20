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
public class EditUserFamilyMemberValidator implements Validator {
	private static final String FIRSTNAME_REQUIRED = "First Name is required";
	private static final String LASTNAME_REQUIRED = "Last Name is required";
	private static final String FIRSTNAME_INVALID = "First Name is not valid.Ex: John";
	private static final String LASTNAME_INVALID = "Last Name is not valid.";
	private static final String OTHER_INVALID = "Other data is not valid.";
	private static final String GOTHRAM_INVALID = "Gothram is not valid.";
	private static final String NAKSHATHRAM_INVALID = "Nakshathram is not valid.";
	public boolean supports(Class clazz) {

		return clazz.equals(UserFamilyMember.class);
	}
	public void validate(Object command, Errors errors) {
		UserFamilyMember userFamilyMember = (UserFamilyMember)command;	
		
	
		String firstName = userFamilyMember.getFirstName();
		String lastName = userFamilyMember.getLastName();

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
		
	}	
}