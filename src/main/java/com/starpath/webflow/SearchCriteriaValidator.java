package com.starpath.webflow;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.starpath.domain.User;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class SearchCriteriaValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User query = (User) obj;
		if (!StringUtils.hasText(query.getPhoneNumber()) && !StringUtils.hasText(query.getLastName())) {
			errors.reject("noCriteria", "Please ** provide valid search criteria");
		}
	}
}