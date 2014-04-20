package com.starpath.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

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

public class AddUserController extends SimpleFormController {
	   

	   public AddUserController() {
	      setCommandClass(User.class);
	      setCommandName("user");
	   }

	   @Override
	   protected Object formBackingObject(HttpServletRequest request)
	                     throws Exception {
	      User userForm = (User) super.formBackingObject(request);
	      return userForm;
	   }
	   
	   @Override
	   protected Map referenceData(HttpServletRequest request) throws Exception {
	      Map referenceData = new HashMap();
	      return referenceData;
	   }
	   @Override
	   protected ModelAndView onSubmit(Object command, BindException bindException)
	                     throws Exception {
		  User pledge = (User) command;
	      pledgeService.addUser(pledge);
	      return new ModelAndView(getSuccessView());
	   }
	   // injected
	   private PledgeService pledgeService;

	   public void setPledgeService(PledgeService pledgeService) {
	      this.pledgeService = pledgeService;
	   }
	}

