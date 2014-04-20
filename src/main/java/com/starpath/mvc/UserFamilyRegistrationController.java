package com.starpath.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.starpath.domain.InvalidEmailException;
import com.starpath.domain.User;
import com.starpath.domain.UserAlreadyExistsException;
import com.starpath.domain.UserFamilyMember;
import com.starpath.service.PledgeService;
import com.starpath.util.UserFamilyMemberValidator;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

public class UserFamilyRegistrationController extends AbstractWizardFormController {

	public UserFamilyRegistrationController() {
		setCommandClass(User.class);
		setCommandName("user");
	}

	@Override
	protected boolean isCancelRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.isCancelRequest(request);
	}

	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		User user = (User) command;
		Map refData = new HashMap();
		if (page == 1 && request.getParameter("_target1") != null) {
			refData.put("nextFamilyMember",	user.getUserFamilyMembers().size()-1);
		}
		return refData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		User user = new User();
		List<UserFamilyMember> members = new ArrayList<UserFamilyMember>();
		UserFamilyMember userFamilyMember = new UserFamilyMember();
		members.add(userFamilyMember);
		user.addUserFamilyMembers(members);
		return user;
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		User user = (User) command;
		
		//Code to add a new family member to the user.
		if (page == 1 && request.getParameter("_target1") != null) {
			UserFamilyMember userFamilyMember = new UserFamilyMember();
			user.getUserFamilyMembers().add(userFamilyMember);
		}
		//Code to remove the selected family member.
		if (page == 2 && request.getParameter("_target2") != null) {
			String firstName = request.getParameter("memberFirstName");
			Iterator it = user.getUserFamilyMembers().iterator();
			UserFamilyMember member = null;
			while (it.hasNext()) {
				member = (UserFamilyMember) it.next();
				if (null != member.getFirstName()
						&& !("".equals(member.getFirstName()))
						&& member.getFirstName().equals(firstName)) {
					break;
				}
			}
			user.getUserFamilyMembers().remove(member);
		}
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		User user = (User) command;
		for (UserFamilyMember userFamilyMember : user.getUserFamilyMembers()) {
			userFamilyMember.setUser(user);
		}
		user.getUserFamilyMembers().remove(user.getUserFamilyMembers().size() - 1);
		User dbUser = pledgeService.findUserByEmail(user.getEmail());
		if(null != dbUser){
			throw new UserAlreadyExistsException("User Exists");
		}
		try {
			pledgeService.addUser(user);
		} catch (Exception e) {
			throw new InvalidEmailException("Email Send Failure");
		}
		return new ModelAndView(getSuccessView(), "user", user);
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
		User validateUser = (User) command;
		UserFamilyMemberValidator validator = (UserFamilyMemberValidator) getValidator();

		if (page == 0) {
			validator.validateEmail(validateUser, errors);
			validator.validatePassword(validateUser, errors);
			validator.validateConfirmPassword(validateUser, errors);
			validator.validateFirstName(validateUser, errors);
			validator.validateLastName(validateUser, errors);
			validator.validatePhoneNumber(validateUser, errors);
			//validator.validateAddress(validateUser, errors);
			validator.validateCity(validateUser, errors);
			validator.validateState(validateUser, errors);
			validator.validateZip(validateUser, errors);
		} else if (page == 1) {
			 //validator.validateFamilyFirstName(validateUser, errors);

		}
	}
}