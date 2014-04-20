package com.starpath.domain;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

public enum PaymentFrequencyTerms {
	ZERO("0"),
	ONE("1"),
    TWO("2"),
    THREE("3"), 
    FOUR("4"), 
    FIVE("5"), 
    SIX("6"), 
    SEVEN("7"), 
    EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11"), TWELEVE("12"), TWOYEARS("24"), THREEYEARS("36"), FOURYEARS("48"), FIVEYEARS("60");
	
	private final String value;

	private PaymentFrequencyTerms(String value) {
		this.value = value;
	}

	public String toString() {
	    return value;
	}
	public int getValue(){
		return Integer.parseInt(value);
	}



}
