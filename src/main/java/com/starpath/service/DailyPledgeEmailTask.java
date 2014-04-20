package com.starpath.service;

import java.util.TimerTask;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

/**
 * Java timer task to call the RantService's sendDailyRantEmails.
 *
 * As shown in Listing 12.3, page 458.
 *
 * @author craig.walls
 */
public class DailyPledgeEmailTask extends TimerTask {
  public DailyPledgeEmailTask() {}

  public void run() {
	  pledgeService.sendDailyPaymentDetailEmails();
  }

//injected
  private PledgeService pledgeService;
  public void setRantService(PledgeService pledgeService) {
    this.pledgeService = pledgeService;
  }
}
