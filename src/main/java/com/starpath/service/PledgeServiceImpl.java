package com.starpath.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.starpath.dao.PaymentDetailDao;
import com.starpath.dao.PledgeDao;
import com.starpath.dao.UserDao;
import com.starpath.dao.UserFamilyMemberDao;
import com.starpath.domain.BusinessLogicException;
import com.starpath.domain.PaymentDetail;
import com.starpath.domain.PaymentStatusType;
import com.starpath.domain.Pledge;
import com.starpath.domain.PledgeStrategy;
import com.starpath.domain.PledgeType;
import com.starpath.domain.Report;
import com.starpath.domain.StandardPledgeStrategy;
import com.starpath.domain.TermPledgeStrategy;
import com.starpath.domain.User;
import com.starpath.domain.UserFamilyMember;
import com.starpath.domain.UserPrivilege;
import com.starpath.domain.UserPrivilegeType;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 *         <p>
 *         Copyright ©2007-2008 by StarpathIT Inc., all rights reserved. <br>
 */

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PledgeServiceImpl implements PledgeService,
		NotificationPublisherAware {
	Log log = LogFactory.getLog(PledgeServiceImpl.class);

	public PledgeServiceImpl() {
	}

	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addUser(User user) throws BusinessLogicException {
		User existingUser = userDao.getUserByEmail(user.getEmail());
		if (existingUser != null) {
			throw new BusinessLogicException();
		}
		if (user.getRoles() != null && user.getRoles().size() == 0) {
			// this is a super user for the system
			if (user.getEmail().equals("trustee@svtc.com")) {
				UserPrivilege role = new UserPrivilege();
				role.setPrivilege(UserPrivilegeType.ROLE_MEMBER.name());
				UserPrivilege role2 = new UserPrivilege();
				role2.setPrivilege(UserPrivilegeType.ROLE_VOLUNTEER.name());
				UserPrivilege role1 = new UserPrivilege();
				role.setPrivilege(UserPrivilegeType.ROLE_TRUSTEE.name());
				List<UserPrivilege> roles = new ArrayList<UserPrivilege>();
				roles.add(role);
				roles.add(role1);
				roles.add(role2);
				user.addMoreRoles(roles);
				user.setEnabled(true);
				user.setAccountExpired(false);
				user.setAccountLocked(false);
			} else {
				UserPrivilege role = new UserPrivilege();
				role.setPrivilege(UserPrivilegeType.ROLE_MEMBER.name());
				user.addRole(role);
				user.setEnabled(true);
				user.setAccountExpired(false);
				user.setAccountLocked(false);
			}
		}

		userDao.makePersistent(user);
		sendRegistrationEmail(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void editUser(User user) {
		if (null != user && user.getId() != null) {
			System.out.println("Received user" + user.getFirstName());
			User u = userDao.findById(user.getId());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setLastName(user.getLastName());
			u.setFirstName(user.getFirstName());
			u.setEmail(user.getEmail());
			if (null != user.getPassword())
				u.setPassword(user.getPassword());
			if (null != user.getConfirmPassword())
				u.setConfirmPassword(user.getConfirmPassword());
			u.setAddress(user.getAddress());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeUser(User user) {
		userDao.makeTransient(user);
	}

	public List<User> searchUsers(User criteria) {
		return userDao.getUser(criteria.getLastName(), criteria
				.getPhoneNumber());
	}

	public User findUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public User findUser(Long id) {
		return userDao.findById(id);

	}

	public User findUser(User user) {
		return userDao.findById(user.getId());
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
	public Set<PaymentDetail> getPaymentDetailsForPledge(Pledge requestedPledge) {
		List<Pledge> pledges = pledgeDao.findPledgeByUser(requestedPledge
				.getUser().getLastName());
		return pledges.get(0).getPaymentDetails();
	}

	/**
	 * Sends Tax e-mail to tell a user that they have tax benefit for
	 * contribution
	 * 
	 */
	public void sendEmailForPledge(Long id) {
		Pledge pledge = pledgeDao.findById(id);
		User user = pledge.getUser();
		if (user == null) {
			return;
		}

		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		message.setTo(user.getEmail());

		String text = message.getText();
		text = StringUtils.replace(text, "%USER%", pledge.getUser()
				.getLastName());
		message.setText(text);

		mailSender.send(message);
	}

	/**
	 * Sends a daily e-mail to users who have been paymentDetailed about.
	 * 
	 */
	public void sendDailyPaymentDetailEmails() {
		List<PaymentDetail> paymentDetailsForToday = getPaymentDetailsForDay(new Date());

		Set<Pledge> pledgesPaymentDetailedAboutToday = new HashSet<Pledge>();

		// extract pledges and place into set (for uniqueness)
		for (PaymentDetail paymentDetail : paymentDetailsForToday) {
			pledgesPaymentDetailedAboutToday.add(paymentDetail.getPledge());
		}

		for (Pledge pledge : pledgesPaymentDetailedAboutToday) {
			sendEmailForPledge(pledge.getId());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPledge(Pledge pledge) {
		PledgeStrategy pledgeStrategy = null;
		if (pledge == null)
			throw new IllegalArgumentException("Can't add a null Pledge.");
		if (pledge.getPledgeType().equals(PledgeType.STANDARD.name())) {
			pledgeStrategy = new StandardPledgeStrategy();
		} else if (pledge.getPledgeType().equals(PledgeType.TERM.name())) {
			pledgeStrategy = new TermPledgeStrategy();
		}
		pledgeStrategy.choosePledge(pledge);
		// TODO uncomment this after the front-end is fixed
		User u = userDao.findById(pledge.getUser().getId());
		pledge.setUser(u);
		pledgeDao.makePersistent(pledge);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void editPledge(Pledge pledge) {
		if (null != pledge && pledge.getId() != null) {
			Pledge p = pledgeDao.findById(pledge.getId());
			boolean isPledgeComplete = p
					.isPledgeComplete(p.getPaymentDetails());
			if (isPledgeComplete) {
				p.setPaymentStatus(PaymentStatusType.COMPLETE.name());
			} else {
				p.setPaymentStatus(PaymentStatusType.INACTIVE.name());
			}
			for (Iterator<PaymentDetail> it = p.getPaymentDetails().iterator(); it
					.hasNext();) {
				PaymentDetail paymentDetail = it.next();
				if (paymentDetail.getPaymentReceived() == false) {
					it.remove();
					paymentDetailDao.makeTransient(paymentDetail);
				}
			}
		}

	}

	public List<Pledge> getPledges() {
		return pledgeDao.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removePledge(Pledge pledge) {
		pledgeDao.makeTransient(pledge);

	}

	public List<Pledge> searchPledges(String lastName) {
		return pledgeDao.findPledgeByUser(lastName);

	}

	public Pledge findPledge(Long id) {
		return pledgeDao.findById(id);
	}

	public List<PaymentDetail> getPaymentDetailsForDay(Date day) {
		return paymentDetailDao.getPaymentDetailsForDay(day);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPaymentDetail(PaymentDetail paymentDetail) {
		Pledge pledge = paymentDetail.getPledge();
		User user = pledge.getUser();
		pledge.addPaymentDetails(paymentDetail);
		user.addPledge(pledge);
		if (user.getRoles() != null && user.getRoles().size() == 0) {

			UserPrivilege role = new UserPrivilege();
			role.setPrivilege(UserPrivilegeType.ROLE_MEMBER.name());
			user.addRole(role);
			user.setEnabled(true);
			user.setAccountExpired(false);
			user.setAccountLocked(false);
		}
		userDao.makePersistent(user);
		sendRegistrationEmail(user);
	}

	public List<PaymentDetail> getRecentPaymentDetails() {
		return paymentDetailDao.findAll();
	}

	public List<PaymentDetail> getPaymentDetailsForUser(User user) {
		return paymentDetailDao.getPaymentDetailsForUser(user.getEmail());
	}

	public List<PaymentDetail> searchPaymentDetails(String lastName) {
		return paymentDetailDao.getPaymentDetailsForUser(lastName);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Double totalRevenueAsOfDate(Date date) {
		double revenue = 0d;
		List<PaymentDetail> paymentDetails = paymentDetailDao.findAll();
		for (PaymentDetail payment : paymentDetails) {
			if (payment.isRecognizableBy(date)) {
				revenue = revenue + payment.getPaymentAmount().doubleValue();
			}
		}
		return revenue;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void editPaymentDetail(PaymentDetail paymentDetail) {
		if (null != paymentDetail && paymentDetail.getId() != null) {
			System.out.println("Received Payment" + paymentDetail.getId());
			PaymentDetail p = paymentDetailDao.findById(paymentDetail.getId());
			p.setAccount(paymentDetail.getAccount());
			p.setBankname(paymentDetail.getBankname());
			p.setSwift(paymentDetail.getSwift());
			p.setPaymentAmount(paymentDetail.getPaymentAmount());
			p.setPaymentDate(paymentDetail.getPaymentDate());
			p.setPaymentReceived(paymentDetail.getPaymentReceived());
		}

	}

	public PaymentDetail findPaymentDetail(Long id) {
		return paymentDetailDao.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void makePayment(PaymentDetail paymentDetail) {
		if (null != paymentDetail && paymentDetail.getId() != null) {
			System.out.println("Received Payment" + paymentDetail.getId());
			paymentDetailDao.makePersistent(paymentDetail);
		}

	}

	// injected by JMX implementation
	private NotificationPublisher notificationPublisher;

	public void setNotificationPublisher(
			NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPledgePaymentForUser(PaymentDetail paymentDetail)
			throws BusinessLogicException {
		Pledge pledge = paymentDetail.getPledge();
		Long id = pledge.getUser().getId();
		User user = userDao.findById(id);
		pledge.addPaymentDetails(paymentDetail);
		user.addPledge(pledge);
	}

	public Report reportSummary(Date startDate, Date endDate) {
		Report report = new Report();
		Set<String> usersDonated25000Up = new HashSet();
		Set<String> usersDonated10000Up = new HashSet();
		Set<String> usersDonated5000Up = new HashSet();
		Set<String> usersDonated2500Up = new HashSet();
		Set<String> usersDonated100Up = new HashSet();
		String donors25000Up = "";
		StringBuffer buf1 = new StringBuffer(donors25000Up);
		String donors10000Up = "";
		StringBuffer buf2 = new StringBuffer(donors10000Up);
		String donors5000Up = "";
		StringBuffer buf3 = new StringBuffer(donors5000Up);
		String donors2500Up = "";
		StringBuffer buf4 = new StringBuffer(donors2500Up);
		String donors100Up = "";
		StringBuffer buf5 = new StringBuffer(donors100Up);
		int numberOfNewPledges = 0;
		int totalActivePledges = 0;
		int totalDisabledPledges = 0;
		double revenueThisPeriod = 0d;
		double totalRevenueAsOfDate = 0d;
		int numberOfPaymentsReceived = 0;
		List<User> users = userDao.findAll();
		for (User user : users) {
			Set<Pledge> pledges = user.getPledges();
			for (Pledge pledge : pledges) {
				Set<PaymentDetail> paymentDetails = pledge.getPaymentDetails();
				if (pledge.isCurrent(startDate, endDate)) {
					numberOfNewPledges++;
					if (pledge.isPledge25000Up()) {
						usersDonated25000Up.add(user.getLastName() + " "
								+ user.getFirstName());
					}
					if (pledge.isPledge10000Up()) {
						usersDonated10000Up.add(user.getLastName() + " "
								+ user.getFirstName());
					}
					if (pledge.isPledge5000Up()) {
						usersDonated5000Up.add(user.getLastName() + " "
								+ user.getFirstName());
					}
					if (pledge.isPledge2500Up()) {
						usersDonated2500Up.add(user.getLastName() + " "
								+ user.getFirstName());
					}
					if (pledge.isPledge100Up()) {
						usersDonated100Up.add(user.getLastName() + " "
								+ user.getFirstName());
					}
				}
				if (pledge.isActivePledge()) {
					totalActivePledges++;
				}
				if (pledge.isDisabledPledge()) {
					totalDisabledPledges++;
				}
				for (PaymentDetail paymentDetail : paymentDetails) {
					if (paymentDetail.isCurrent(startDate, endDate)) {
						revenueThisPeriod = paymentDetail.getPaymentAmount();
						numberOfPaymentsReceived++;
					}
					if (paymentDetail.isRecognizableBy(new Date())) {
						totalRevenueAsOfDate = totalRevenueAsOfDate
								+ paymentDetail.getPaymentAmount()
										.doubleValue();
					}
				}
			}
		}
		if (null != usersDonated25000Up) {
			java.util.Iterator iter1 = usersDonated25000Up.iterator();
			while (iter1.hasNext()) {
				buf1.append((String) iter1.next());
				buf1.append(" , ");
			}
			donors25000Up = buf1.toString();
		}
		if (null != usersDonated10000Up) {
			java.util.Iterator iter2 = usersDonated10000Up.iterator();
			while (iter2.hasNext()) {
				buf2.append((String) iter2.next());
				buf2.append(" , ");
			}
			donors10000Up = buf2.toString();
		}
		if (null != usersDonated5000Up) {
			java.util.Iterator iter3 = usersDonated5000Up.iterator();
			while (iter3.hasNext()) {
				buf3.append((String) iter3.next());
				buf3.append(" , ");
			}
			donors5000Up = buf3.toString();
		}
		if (null != usersDonated2500Up) {
			java.util.Iterator iter4 = usersDonated2500Up.iterator();
			while (iter4.hasNext()) {
				buf4.append((String) iter4.next());
				buf4.append(" , ");
			}
			donors2500Up = buf4.toString();
		}
		if (null != usersDonated100Up) {
			java.util.Iterator iter5 = usersDonated100Up.iterator();
			while (iter5.hasNext()) {
				buf5.append((String) iter5.next());
				buf5.append(" , ");
			}
			donors100Up = buf5.toString();
		}
		report.setNumberOfNewPledges(numberOfNewPledges);
		report.setNumberOfPaymentsReceived(numberOfPaymentsReceived);
		report.setRevenueThisPeriod(revenueThisPeriod);
		report.setTotalActivePledges(totalActivePledges);
		report.setTotalDisabledPledges(totalDisabledPledges);
		report.setTotalNumberOfUsers(users.size());
		report.setTotalRevenueAsOfDate(totalRevenueAsOfDate);
		report.setUsersDonated10000Up(donors10000Up);
		report.setUsersDonated100Up(donors100Up);
		report.setUsersDonated25000Up(donors25000Up);
		report.setUsersDonated2500Up(donors2500Up);
		report.setUsersDonated5000Up(donors5000Up);
		return report;
	}

	public List<UserFamilyMember> searchFamilyMembers(Long id) {
		return userFamilyMemberDao.findMembersByUserId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void editUserFamilyMember(UserFamilyMember userFamilyMember) {
		if (null != userFamilyMember && userFamilyMember.getId() != null) {
			UserFamilyMember u = userFamilyMemberDao.findById(userFamilyMember
					.getId());
			u.setFirstName(userFamilyMember.getFirstName());
			u.setLastName(userFamilyMember.getLastName());
	}
	}

	public List<UserFamilyMember> searchFamilyMembers(String lastName) {
		return userFamilyMemberDao.getUserFamilyMembers(lastName);
	}

	public UserFamilyMember findByUserFamilyMemberId(Long id) {
		return userFamilyMemberDao.findFamilyMemberByMemberId(id);
	}

	public void sendForgotPasswordEmail(String email, String phoneNumber)
			throws BusinessLogicException {
		User user = userDao.getUserByEmail(email);
		if (user.getPhoneNumber().equals(phoneNumber)) {
			SimpleMailMessage message = new SimpleMailMessage(
					forgotPasswordMessage);
			message.setTo(user.getEmail());
			String text = message.getText();
			text = StringUtils.replace(text, "%USER%", user.getLastName());
			text = StringUtils.replace(text, "%PASSWORD%", user.getPassword());
			message.setText(text);
			mailSender.send(message);
		} else {
			throw new BusinessLogicException("User is not registered");
		}

	}

	public void sendRegistrationEmail(User user) {
		SimpleMailMessage message = new SimpleMailMessage(registrationMessage);
		message.setTo(user.getEmail());
		String text = message.getText();
		text = StringUtils.replace(text, "%USER%", user.getLastName());
		text = StringUtils.replace(text, "%EMAIL%", user.getEmail());
		text = StringUtils.replace(text, "%PASSWORD%", user.getPassword());
		message.setText(text);
		mailSender.send(message);

	}

	// injected by spring
	private PledgeDao pledgeDao;

	public void setPledgeDao(PledgeDao pledgeDao) {
		this.pledgeDao = pledgeDao;
	}

	// injected by spring
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// injected by spring
	private UserFamilyMemberDao userFamilyMemberDao;

	public void setUserFamilyMemberDao(UserFamilyMemberDao userFamilyMemberDao) {
		this.userFamilyMemberDao = userFamilyMemberDao;
	}

	// injected by spring
	private PaymentDetailDao paymentDetailDao;

	public void setPaymentDetailDao(PaymentDetailDao paymentDetailDao) {
		this.paymentDetailDao = paymentDetailDao;
	}

	// injected by spring
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	// injected by spring
	private SimpleMailMessage mailMessage;

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	// injected by spring
	private SimpleMailMessage forgotPasswordMessage;

	public void setForgotPasswordMessage(SimpleMailMessage forgotPasswordMessage) {
		this.forgotPasswordMessage = forgotPasswordMessage;
	}

	// injected by spring
	private SimpleMailMessage registrationMessage;

	public void setRegistrationMessage(SimpleMailMessage registrationMessage) {
		this.registrationMessage = registrationMessage;
	}

}