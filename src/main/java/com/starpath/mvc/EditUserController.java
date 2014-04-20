package com.starpath.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.starpath.domain.User;
import com.starpath.service.PledgeService;
import com.starpath.util.UserValidator;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class EditUserController extends CancellableFormController {
	public EditUserController() {
		setCommandClass(User.class);
		setCommandName("user");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		if (StringUtils.isNotEmpty(email)) {
			User user = new User();
			user.setEmail(email);
			return pledgeService.findUserByEmail(email);
		} else if (StringUtils.isNotEmpty(id)) {
			return pledgeService.findUser(Long.parseLong(id));
		} else {
			return new User();
		}
	}
	
	@Override
	protected ModelAndView onCancel(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {		
		return new ModelAndView(getCancelView());
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		//referenceData.put("user", userForm);
		return referenceData;
	}

	@Override
	protected void doSubmitAction(Object command) throws Exception {
		User user = (User) command;
		if (user.getId() != null) {
			pledgeService.editUser(user);
		} else {
			pledgeService.addUser(user);
		}
	}

	// injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}

}
