package com.starpath.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.starpath.domain.Pledge;
import com.starpath.service.PledgeService;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class EditPledgeController extends AbstractCommandController  {
	public EditPledgeController(){
		setCommandClass(Pledge.class);
		setCommandName("pledge");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse arg1, Object command, BindException errors) throws Exception {
		Pledge pledge = (Pledge)command;
		pledgeService.editPledge(pledge);
		Map model = errors.getModel();
		model.put("pledges", pledgeService.getPledges());		
		return new ModelAndView("searchPledges",model);
	}
	
//	 injected
	   private PledgeService pledgeService;

	   public void setPledgeService(PledgeService pledgeService) {
	      this.pledgeService = pledgeService;
	   }
}
