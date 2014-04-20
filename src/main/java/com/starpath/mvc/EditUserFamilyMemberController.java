package com.starpath.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.starpath.domain.UserFamilyMember;
import com.starpath.service.PledgeService;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Swetha Sankar
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */
public class EditUserFamilyMemberController extends CancellableFormController {
	static String userId;

	public EditUserFamilyMemberController() {
		setCommandClass(UserFamilyMember.class);
		setCommandName("userFamilyMember");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		userId = request.getParameter("userId");
		if (StringUtils.isNotEmpty(id)) {
			return pledgeService.findByUserFamilyMemberId(Long.parseLong(id));
		} else {
			return new UserFamilyMember();
		}
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		return referenceData;
	}

	@Override
	protected void doSubmitAction(Object command) throws Exception {
		UserFamilyMember userFamilyMember = (UserFamilyMember) command;
		if (userFamilyMember.getId() != null) {
			pledgeService.editUserFamilyMember(userFamilyMember);
		} else {
			// TODO : add a new family member.
		}
	}

	@Override
	protected ModelAndView onCancel(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		return new ModelAndView(getCancelView());
	}

	// injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}
}