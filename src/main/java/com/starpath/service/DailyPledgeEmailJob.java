package com.starpath.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

/**
 * A Quartz job bean to call the RantService's sendDailyRantEmails() method.
 *
 * As shown in Listing 12.4, page 460.
 *
 * @author craig.walls
 */
public class DailyPledgeEmailJob extends QuartzJobBean {
  public DailyPledgeEmailJob() {}

  protected void executeInternal(JobExecutionContext jobContext)
      throws JobExecutionException {
    pledgeService.sendDailyPaymentDetailEmails();
  }

  // injected
  private PledgeService pledgeService;
  public void setRantService(PledgeService pledgeService) {
    this.pledgeService = pledgeService;
  }
}
