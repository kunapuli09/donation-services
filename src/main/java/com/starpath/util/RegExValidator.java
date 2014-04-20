package com.starpath.util;


public class RegExValidator {
	public static boolean validateFirstName(String firstName) {
		return firstName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	} 
	
	public static boolean validateLastName(String lastName) {
		return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	} 
	
	public static boolean validateAddress(String address) {
		return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	} 
	
	public static boolean validateCity(String city) {
		return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	} 

	
	public static boolean validateState(String state) {
		return state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	} 
	
	public static boolean validateZip(String zip) {
		return zip.matches("\\d{5}");
	} 
	
	public static boolean validatePhone(String phone) {
		return phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
	} 
	public static boolean validateEmail(String email){
		return email.matches(".+@.+\\.[a-z]+");		
	}
	
	public static boolean validateGothram(String gothram) {
		return gothram.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	} 
	
	public static boolean validateNakshathram(String nakshathram) {
		return nakshathram.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	} 
	public static boolean validateOther(String other) {
		return other.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	} 

	public static boolean validateConfirmpassword(String password,
			String confirmPassword) {
		if(password.equals(confirmPassword)){
			return true;
		}
		return false;
	}
}
