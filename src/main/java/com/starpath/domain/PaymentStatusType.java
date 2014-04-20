package com.starpath.domain;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public enum PaymentStatusType {
	ACTIVE("ACTIVE"),
	COMPLETE("COMPLETE"),
	INACTIVE("INACTIVE")
    ;
	
	private final String value;

	private PaymentStatusType(String value) {
		this.value = value;
	}

	public String toString() {
	    return value;
	}
	public int getValue(){
		return Integer.parseInt(value);
	}



}
