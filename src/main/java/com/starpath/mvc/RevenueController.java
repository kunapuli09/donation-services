package com.starpath.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.starpath.domain.Report;
import com.starpath.service.PledgeService;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class RevenueController extends SimpleFormController {
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(null != request.getParameter("startDate") && null != request.getParameter("endDate")){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");		
		Date startDate = df.parse(request.getParameter("startDate"));
		Date endDate = df.parse(request.getParameter("endDate"));		
		Report report = pledgeService.reportSummary(startDate, endDate);
		return new ModelAndView("templeRevenue", "report", report);
		}else{
			double totalRevenueAsOfDate = pledgeService.totalRevenueAsOfDate(new Date());
			Report report  = new Report();
			report.setTotalRevenueAsOfDate(totalRevenueAsOfDate);
			return new ModelAndView("templeRevenue", "report", report);
		}		
	}

	//	 injected
	private PledgeService pledgeService;

	public void setPledgeService(PledgeService pledgeService) {
		this.pledgeService = pledgeService;
	}
}
