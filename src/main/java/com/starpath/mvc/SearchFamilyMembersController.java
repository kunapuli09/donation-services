package com.starpath.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.starpath.domain.User;
import com.starpath.domain.UserFamilyMember;
import com.starpath.service.PledgeService;


/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Swetha Sankar
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

public class SearchFamilyMembersController extends SimpleFormController {	  
	  
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		String email = request.getParameter("email");
				
		if (StringUtils.isNotEmpty(email)) {
			User memberUser = pledgeService.findUserByEmail(email);
			if (memberUser.getRoles().size() == 1) {
				List<UserFamilyMember> familyMembers = pledgeService.searchFamilyMembers(memberUser.getId());
				return new ModelAndView("searchFamilyMembers", "familyMembers", familyMembers);
			}
		}
		String lastName = request.getParameter("lastName");
		List<UserFamilyMember> familyMembers = pledgeService.searchFamilyMembers(lastName);
		return new ModelAndView("searchFamilyMembers", "familyMembers", familyMembers);
	}  
	
	  // injected
	  private PledgeService pledgeService;
	  public void setPledgeService(PledgeService pledgeService) {
	    this.pledgeService = pledgeService;
	  }
	}

