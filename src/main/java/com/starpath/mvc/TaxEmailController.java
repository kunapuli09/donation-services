package com.starpath.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.starpath.service.PledgeService;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class TaxEmailController extends SimpleFormController {	  
	  
	protected ModelAndView handleRequestInternal(
		      HttpServletRequest request, HttpServletResponse response) 
		      throws Exception {	
			String id = request.getParameter("id");			
		    pledgeService.sendEmailForPledge(Long.parseLong(id)); 
		    return new ModelAndView("home");
		  }  
	  
	  // injected
	  private PledgeService pledgeService;
	  public void setPledgeService(PledgeService pledgeService) {
	    this.pledgeService = pledgeService;
	  }
	}

