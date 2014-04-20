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

public class SearchUserController extends SimpleFormController {	  
	  
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = new User();

		// Start added by Swetha
		String email = request.getParameter("email");
		if (StringUtils.isNotEmpty(email)) {

			User memberUser = pledgeService.findUserByEmail(email);

			if (memberUser.getRoles().size() == 1) {
				return new ModelAndView("searchUsers", "users", memberUser);
			}
		}
		// End added by Swetha

		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		if (StringUtils.isNotEmpty(lastName))
			user.setLastName(lastName);
		if (StringUtils.isNotEmpty(phoneNumber))
			user.setPhoneNumber(phoneNumber);
		List<User> users = pledgeService.searchUsers(user);
		return new ModelAndView("searchUsers", "users", users);
	}  
	  
	  // injected
	  private PledgeService pledgeService;
	  public void setPledgeService(PledgeService pledgeService) {
	    this.pledgeService = pledgeService;
	  }
	}

