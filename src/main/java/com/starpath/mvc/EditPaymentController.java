package com.starpath.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.starpath.domain.PaymentDetail;
import com.starpath.service.PledgeService;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 * <p>
 *   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
 * <br>
 */

public class EditPaymentController extends CancellableFormController {
	public EditPaymentController() {
		setCommandClass(PaymentDetail.class);
		setCommandName("paymentDetail");
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			return pledgeService.findPaymentDetail(Long.parseLong(id));
		} else {
			return new PaymentDetail();
		}
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		return referenceData;
	}

	@Override
	protected void doSubmitAction(Object command) throws Exception {
		PaymentDetail paymentDetail = (PaymentDetail) command;
		if (paymentDetail.getId() != null) {
			pledgeService.editPaymentDetail(paymentDetail);
		} else {
			pledgeService.makePayment(paymentDetail);
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
