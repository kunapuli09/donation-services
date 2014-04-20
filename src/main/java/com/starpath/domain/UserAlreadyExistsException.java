package com.starpath.domain;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
