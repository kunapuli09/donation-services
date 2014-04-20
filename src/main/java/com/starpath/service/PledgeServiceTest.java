package com.starpath.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.starpath.domain.Address;
import com.starpath.domain.PaymentDetail;
import com.starpath.domain.PaymentFrequencyTerms;
import com.starpath.domain.PaymentFrequencyType;
import com.starpath.domain.Pledge;
import com.starpath.domain.PledgeType;
import com.starpath.domain.User;
import com.starpath.domain.UserPrivilege;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 * <p>
 *   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
 * <br>
 */

/**
 * Tests the RantService from the Spring context along with its dependencies.
 * Strictly speaking, this is an integration test, not a unit-test, as it tests
 * the service and its dependencies, as wired in Spring.
 * 
 * Listing B.3
 * 
 * @author 
 */
public class PledgeServiceTest extends
		AbstractDependencyInjectionSpringContextTests {
	public PledgeServiceTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "user-services.xml", "user-data-jpa.xml",
				"user-email.xml", "user-scheduler.xml" };
	}

	public void testGetUsers() {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		List users = pledgeService.getUsers();
		assertTrue(users.size() > 0);
	}

	public void testAddPaymentDetail() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		PaymentDetail newPayment = new PaymentDetail();
		newPayment.setAccount("XXX");
		newPayment.setBankname("UUU");
		newPayment.setSwift("321");
		newPayment.setCreated(new Date());
		newPayment.setPaymentAmount(new Double(1000));
		Pledge pledge = new Pledge();
		pledge.setComment("monthly");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.FIVE.getValue());
		pledge.setPledgeAmount(new Double(6000));
		pledge.setUser(createUser());
		//pledge.setPledgeType(PledgeType.STANDARD.name());
		newPayment.setPledge(pledge);
		pledgeService.addPaymentDetail(newPayment);
		Set<PaymentDetail> payments = pledgeService
				.getPaymentDetailsForPledge(pledge);
		assertTrue(payments.contains(newPayment));
		//pledgeService.addUser(new User());
	}

	public void testPayLater() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		PaymentDetail newPayment = new PaymentDetail();		
		Pledge pledge = new Pledge();
		pledge.setComment("monthly");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.TWELEVE.getValue());
		pledge.setPledgeAmount(new Double(1200));
		User u = new User();
		u.setId(new Long(3));
		pledge.setUser(u);
		newPayment.setPledge(pledge);
		pledgeService.addPledgePaymentForUser(newPayment);
		Set<PaymentDetail> payments = pledgeService
				.getPaymentDetailsForPledge(pledge);
		assertTrue(payments.contains(newPayment));
	}
	public void testPayOneTimeToday() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		PaymentDetail newPayment = new PaymentDetail();
		newPayment.setAccount("XXX");
		newPayment.setBankname("UUU");
		newPayment.setSwift("321");
		newPayment.setCreated(new Date());
		newPayment.setPaymentAmount(new Double(1000));	
		Pledge pledge = new Pledge();
		pledge.setComment("monthly");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.ZERO.getValue());
		pledge.setPledgeAmount(new Double(1000));
		User u = new User();
		u.setId(new Long(3));
		pledge.setUser(u);
		//pledge.setPledgeType(PledgeType.STANDARD.name());
		newPayment.setPledge(pledge);
		pledgeService.addPledgePaymentForUser(newPayment);
		Set<PaymentDetail> payments = pledgeService
				.getPaymentDetailsForPledge(pledge);
		assertTrue(payments.contains(newPayment));
	}
	public void testPayOneTimeLater() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		PaymentDetail newPayment = new PaymentDetail();			
		Pledge pledge = new Pledge();
		pledge.setComment("monthly");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.ZERO.getValue());
		pledge.setPledgeAmount(new Double(1000));
		User u = new User();
		u.setId(new Long(3));
		pledge.setUser(u);
		//pledge.setPledgeType(PledgeType.STANDARD.name());
		newPayment.setPledge(pledge);
		pledgeService.addPledgePaymentForUser(newPayment);
		Set<PaymentDetail> payments = pledgeService
				.getPaymentDetailsForPledge(pledge);
		assertTrue(payments.contains(newPayment));
	}


	public void testAddUser() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
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
		user.setEmail("trustee@svtc.com");
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
		pledgeService.addUser(user);
	}

	public void testAddPledge() throws Exception {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		Pledge pledge = new Pledge();
		pledge.setComment("for something");
		pledge.setPaymentFrequency(PaymentFrequencyTerms.TWO.getValue());
		pledge.setPaymentFrequencyType(PaymentFrequencyType.YEARLY.name());
		pledge.setPledgeAmount(new Double(100000));
		pledge.setPledgeType(PledgeType.TERM.name());
		User u = new User();
		u.setId(new Long(1));
		pledge.setUser(u);
		pledgeService.addPledge(pledge);
	}

	public void testEditUser() {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		User u = new User();
		u.setId(new Long(1));
		u.setPhoneNumber("3333333333");
		pledgeService.editUser(u);
	}

	public void testEditPledge() {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		Pledge pledge = pledgeService.findPledge(new Long(1));
		pledgeService.editPledge(pledge);
	}

	public void testSendEmail() {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		pledgeService.sendEmailForPledge(new Long(1));
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

	public void testTotalRevenueAsOfDate() {
		PledgeService pledgeService = (PledgeService) applicationContext
				.getBean("pledgeService");
		System.out.println(pledgeService.totalRevenueAsOfDate(new Date()));
	}
}