package com.starpath.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.starpath.domain.Address;
import com.starpath.domain.PaymentDetail;
import com.starpath.domain.PaymentFrequencyTerms;
import com.starpath.domain.Pledge;
import com.starpath.domain.User;
import com.starpath.domain.UserPrivilege;

public class PledgeTest extends
		TestCase {
	//test the business logic
	Pledge pledge = null;
	public PledgeTest() {
	}
	@Override
	protected void setUp() throws Exception {
		pledge = new Pledge();		
	}		
	public void testIsPledge100Up(){
		pledge.setPledgeAmount(new Double(1000));
		boolean result = pledge.isPledge100Up();
		assertTrue(result);
		}	
	public void testIsPledgeNot100Up(){
		pledge.setPledgeAmount(new Double(2500));
		boolean result = pledge.isPledge100Up();
		assertFalse(result);
	}
	public void testRoundPledge(){
		pledge.setPledgeAmount(new Double(2500.999999));
		pledge.roundPledgeAmount();
		double d = 2501.0d;
		assertEquals(pledge.getPledgeAmount().doubleValue(), d);
	}
	public void testIsPledge2500Up(){
		pledge.setPledgeAmount(new Double(2600));
		boolean result = pledge.isPledge2500Up();
		assertTrue(result);
		}
	public void testIsPledgeNot2500Up(){
		pledge.setPledgeAmount(new Double(25100));
		boolean result = pledge.isPledge2500Up();
		assertFalse(result);
	}
	public void testIsPledge5000Up(){
		pledge.setPledgeAmount(new Double(5000));
		boolean result = pledge.isPledge5000Up();
		assertTrue(result);
		}
	public void testIsPledgeNot5000Up(){
		pledge.setPledgeAmount(new Double(100));
		boolean result = pledge.isPledge5000Up();
		assertFalse(result);
	}
	public void testAddPaymentDetail(){
		PaymentDetail newPayment = new PaymentDetail();
		newPayment.setAccount("XXX");
		newPayment.setBankname("UUU");
		newPayment.setSwift("321");
		newPayment.setCreated(new Date());
		newPayment.setPaymentAmount(new Double(10));
		Pledge pledge = new Pledge();
		pledge.setComment("monthly");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.FIVE.getValue());
		pledge.setPledgeAmount(new Double(33));
		pledge.setUser(createUser());
		pledge.addPaymentDetails(newPayment);
		System.out.println("Payment Amount");
	}
	private User createUser() {
		User user = new User();
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		Address address = new Address();
		address.setAddress("xxx");
		address.setCity("YYY");
		address.setCountry("ZZZ");
		address.setPostalCode("00000");
		user.setAddress(address);
		/*Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		 SystemWideSaltSource salt = new SystemWideSaltSource();
		 salt.setSystemWideSalt("ABC123XYZ789");
		 String pwd = encoder.encodePassword("pwd", salt);
		 */user.setConfirmPassword("trustee");
		user.setCredentialsExpired(false);
		user.setEmail("trustee6@svtc.com");
		user.setEnabled(true);
		user.setFirstName("Murali");
		user.setLastName("Ankavur");
		user.setPassword("trustee");
		user.setPasswordHint("don't know");
		user.setPhoneNumber("33333");
		/*Pledge pledge = new Pledge();
		 pledge.setComment("monthly");
		 pledge.setEndDate(new Date());
		 pledge.setPaymentFrequency("2");
		 pledge.setPldgeType("standard");
		 pledge.setPledgeAmount(new Double(1116));
		 user.addPledge(pledge);*/
		List<UserPrivilege> roles = new ArrayList<UserPrivilege>();
		UserPrivilege role = new UserPrivilege();
		role.setPrivilege("ROLE_MEMBER");
		user.addRole(role);
		UserPrivilege role1 = new UserPrivilege();
		role1.setPrivilege("ROLE_TRUSTEE");
		roles.add(role);
		roles.add(role1);
		user.addMoreRoles(roles);
		return user;
	}
}
