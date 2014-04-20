package com.starpath.mvc;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/25 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class UserDetailDecorator implements DisplaytagColumnDecorator {
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {		
		String sValue = "<a class=\"tablelink\" href=\"userDetail.htm?&id="+ columnValue.toString()+"&name="+ pageContext.getAttribute("action")
				+"\">Details</a>";		
		
		return sValue;

	}
	
}
