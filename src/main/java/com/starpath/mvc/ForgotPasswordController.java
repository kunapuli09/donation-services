package com.starpath.mvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.starpath.domain.InvalidEmailException;
import com.starpath.domain.User;
import com.starpath.service.PledgeService;

public class ForgotPasswordController extends CancellableFormController {	  
	  
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		User user = new User();
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(phoneNumber)) {
			try {
				pledgeService.sendForgotPasswordEmail(email, phoneNumber);
			} catch (Exception e) {
				System.out.println("Failed to send forgot password Email");
				throw new InvalidEmailException("Failed to send forgot password Email");				
			}
		}
		return user;
	} 
	@Override
	protected boolean isCancelRequest(HttpServletRequest request) {
		return super.isCancelRequest(request);
	}
	
	  // injected
	  private PledgeService pledgeService;
	  public void setPledgeService(PledgeService pledgeService) {
	    this.pledgeService = pledgeService;
	  }
	  
	  protected void validatePage(Object command, Errors errors, int page) {
			User validateUser = (User) command;
		}
	}

