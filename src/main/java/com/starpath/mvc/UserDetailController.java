package com.starpath.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.starpath.domain.User;
import com.starpath.domain.UserFamilyMember;
import com.starpath.service.PledgeService;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

public class UserDetailController extends AbstractController {
	public UserDetailController() {
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Long userId = Long.parseLong(id);
      
		String searchPageName = request.getParameter("name");
		if ("FamilyMembers".equalsIgnoreCase(searchPageName) && userId != null) {
			UserFamilyMember ufm = pledgeService.findByUserFamilyMemberId(userId);
			userId = ufm.getUser().getId();
		}
		User user = pledgeService.findUser(userId);
		return new ModelAndView("userDetail", "user", user);
	}

	// injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}
}
