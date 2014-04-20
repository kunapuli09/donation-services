package com.starpath.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.starpath.service.PledgeService;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class PaymentDetailsController extends AbstractController {
  public PaymentDetailsController() {}
  
  protected ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    
    List paymentDetails = pledgeService.getRecentPaymentDetails();
    
    return new ModelAndView("paymentDetails", "paymentDetails", paymentDetails);
  }
  
  // injected
  private PledgeService pledgeService;
  public void setPledgeService(PledgeService pledgeService) {
    this.pledgeService = pledgeService;
  }
}
