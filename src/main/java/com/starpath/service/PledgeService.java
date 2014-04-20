package com.starpath.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.starpath.domain.BusinessLogicException;
import com.starpath.domain.PaymentDetail;
import com.starpath.domain.Pledge;
import com.starpath.domain.Report;
import com.starpath.domain.User;
import com.starpath.domain.UserFamilyMember;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

@ManagedResource(objectName="starpath:name=PledgeService") // Annotation added in Listing 12.5
public interface PledgeService {
  public void addPaymentDetail(PaymentDetail paymentDetail) throws BusinessLogicException;
  public List<PaymentDetail> getRecentPaymentDetails();
  public Set<PaymentDetail> getPaymentDetailsForPledge(Pledge pledge);
  public List<PaymentDetail> getPaymentDetailsForUser(User user);
  public List<PaymentDetail> getPaymentDetailsForDay(Date date);  
  public PaymentDetail findPaymentDetail(Long id);
  public void editPaymentDetail(PaymentDetail paymentDetail); 
  public void makePayment(PaymentDetail paymentDetail); 
  public void addUser(User user) throws BusinessLogicException;
  public void editUser(User user);
  public void removeUser(User user);
  public List<User> getUsers();
  public List<User> searchUsers(User criteria);
  public User findUser(Long id);
  public Pledge findPledge(Long id);
  public User findUser(User user);
  public User findUserByEmail(String email);
  public List<Pledge> getPledges();
  public List<Pledge> searchPledges(String lastName);
  public List<PaymentDetail> searchPaymentDetails(String lastName);
  public void addPledge(Pledge pledge) throws BusinessLogicException;
  public void addPledgePaymentForUser(PaymentDetail paymentDetail) throws BusinessLogicException;
  public void editPledge(Pledge pledge);
  public void removePledge(Pledge pledge);  
  public void sendEmailForPledge(Long id);
  public Double totalRevenueAsOfDate(Date date);
  public Report reportSummary(Date startDate, Date endDate);
  @ManagedOperation(description="Send the daily pledge e-mail.") // Annotation added in Listing 12.5
  public void sendDailyPaymentDetailEmails();
  public List<UserFamilyMember> searchFamilyMembers(Long id);
  public void editUserFamilyMember(UserFamilyMember userFamilyMember);
  public List<UserFamilyMember> searchFamilyMembers(String lastName);
  public UserFamilyMember findByUserFamilyMemberId(Long id);
  public void sendForgotPasswordEmail(String email, String phoneNumber) throws BusinessLogicException;

}