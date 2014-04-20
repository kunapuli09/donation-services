package com.starpath.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @version $Revision: 1.0 $ $Date: 2008/02/02 $
 * @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
 * @author Krishna M. Kunapuli
 * <p>
 *   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
 * <br>
 */

@Entity
@Table(name = "PLEDGE")
public class Pledge implements Serializable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLEDGE_ID")
	protected Long id;

	@Version
	@Column(name = "OBJ_VERSION")
	private int version = 0;

	//@Enumerated(EnumType.STRING)
	@Column(name = "PLEDGE_TYPE", nullable = false)
	protected String pledgeType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	protected Date startDate = new Date();

	@Column(name = "PLEDGE_AMOUNT", nullable = false)
	protected Double pledgeAmount;

	@Column(name = "PAYMENT_FREQUENCY", nullable = false)
	protected int paymentFrequency;

	@Column(name = "PAYMENT_FREQUENCY_TYPE", nullable = false)
	protected String paymentFrequencyType;

	@Column(name = "PAYMENT_STATUS", nullable = false)
	protected String paymentStatus;

	@Column(name = "COMMENT", nullable = false)
	protected String comment;

	@ManyToOne(targetEntity = com.starpath.domain.User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PLEDGE_USER_ID", updatable = false)
	//@org.hibernate.annotations.ForeignKey(name = "FK_PLEDGE_USER_ID")
	protected User user;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "pledge")
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	protected Set<PaymentDetail> paymentDetails = new HashSet<PaymentDetail>();

	@Transient
	GregorianCalendar gc = new GregorianCalendar();

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getPaymentFrequency() {
		return paymentFrequency;
	}

	public String getPledgeType() {
		return pledgeType;
	}

	public Double getPledgeAmount() {
		return pledgeAmount;
	}

	public String getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}

	public void setPledgeAmount(Double pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Set<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(Set<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	/**
	 * Adds a <tt>PaymentDetails</tt> to the set.
	 * <p>
	 * 
	 * @param billingDetails
	 */
	public void addPaymentDetails(PaymentDetail paymentDetails) {
		if (paymentDetails == null)
			throw new IllegalArgumentException(
					"Can't add a null PaymentDetails.");	
		this.roundPledgeAmount();
		//This condition tells us if this is a standard or term pledge		
		if (this.getPaymentFrequency() == PaymentFrequencyTerms.ZERO.getValue()) {	
			//this is standard pledge because the paymentfrequency is zero
			this.setPledgeType(PledgeType.STANDARD.name());
			this.setPaymentStatus(PaymentStatusType.ACTIVE.name());
			this.setPaymentFrequencyType(PaymentFrequencyType.MONTHLY.name());
			if (paymentDetails.getPaymentAmount() != null
					&& this.getPledgeAmount().doubleValue() == paymentDetails
							.getPaymentAmount().doubleValue()) {
				//user is making the first payment
				//bcs this is standard one-time pledge 
				// the rule is pay everything or paylater
				paymentDetails.setPaymentDate(new Date());
				paymentDetails.setCreated(new Date());
				paymentDetails.setPaymentReceived(Boolean.TRUE);
				if (!this.getPaymentDetails().contains(paymentDetails)) {
					paymentDetails.setPledge(this);
					this.getPaymentDetails().add(paymentDetails);
				}
			} else {
				//user wants to make the total pledge amount at one-time payment later
				gc.add(Calendar.MONTH, 1);
				paymentDetails.setAccount(null);
				paymentDetails.setBankname(null);
				paymentDetails.setSwift(null);
				paymentDetails.setPaymentDate(gc.getTime());
				paymentDetails.setCheckNumber(null);
				paymentDetails.setPaymentAmount(this.getPledgeAmount());
				paymentDetails.setCreated(new Date());
				paymentDetails.setPaymentReceived(Boolean.FALSE);
				if (!this.getPaymentDetails().contains(paymentDetails)) {
					paymentDetails.setPledge(this);
					this.getPaymentDetails().add(paymentDetails);
				}
			}
		} else if (this.getPaymentFrequency() != PaymentFrequencyTerms.ZERO
				.getValue()) {
			//user chose to pay in frequent monthly terms 			
			this.setPledgeType(PledgeType.TERM.name());
			this.setPaymentStatus(PaymentStatusType.ACTIVE.name());
			this.setPaymentFrequencyType(PaymentFrequencyType.MONTHLY.name());
			if (null != paymentDetails.getPaymentAmount() && paymentDetails.getPaymentAmount().doubleValue() > 0) {
				//user is paying some thing today..make it the first payment
				int numberOfTerms = this.getPaymentFrequency();
				double amountPerTerm = Math.round((this.getPledgeAmount().doubleValue() - paymentDetails
						.getPaymentAmount().doubleValue())
						/ numberOfTerms);
				paymentDetails.setPaymentDate(new Date());
				paymentDetails.setCreated(new Date());
				paymentDetails.setPaymentReceived(Boolean.TRUE);
				if (!this.getPaymentDetails().contains(paymentDetails)) {
					paymentDetails.setPledge(this);
					this.getPaymentDetails().add(paymentDetails);
				}
				gc.add(Calendar.MONTH, 1);
				//create a new payment record holder for each monthly terms
				for (int i = 0; i < numberOfTerms; i++) {
					gc.add(Calendar.MONTH, i);
					PaymentDetail paymentDetail = new PaymentDetail();
					paymentDetail.setPaymentDate(gc.getTime());
					paymentDetail.setCreated(new Date());
					paymentDetail.setCheckNumber(paymentDetails
							.getCheckNumber());
					paymentDetail.setPaymentAmount(amountPerTerm);
					paymentDetail.setAccount(paymentDetails.getAccount());
					paymentDetail.setBankname(paymentDetails.getBankname());
					paymentDetail.setSwift(paymentDetails.getSwift());
					paymentDetail.setPaymentReceived(Boolean.FALSE);
					if (!this.getPaymentDetails().contains(paymentDetail)) {
						paymentDetail.setPledge(this);
						this.getPaymentDetails().add(paymentDetail);
					}
					gc = new GregorianCalendar();
				}
			} else {
				//user made a term pledge and decided to pay pledgeAmount
				//in monthly terms
				int numberOfTerms = this.getPaymentFrequency();
				double amountPerTerm = Math.round(this.getPledgeAmount().doubleValue()
						/ numberOfTerms);
				for (int i = 0; i < numberOfTerms; i++) {
					gc.add(Calendar.MONTH, i);
					PaymentDetail paymentDetail = new PaymentDetail();
					paymentDetail.setPaymentDate(gc.getTime());
					paymentDetail.setCreated(new Date());
					paymentDetail.setCheckNumber(null);
					paymentDetail.setPaymentAmount(amountPerTerm);
					paymentDetail.setAccount(null);
					paymentDetail.setBankname(null);
					paymentDetail.setSwift(null);
					paymentDetail.setPaymentReceived(Boolean.FALSE);
					if (!this.getPaymentDetails().contains(paymentDetail)) {
						paymentDetail.setPledge(this);
						this.getPaymentDetails().add(paymentDetail);
					}
					gc = new GregorianCalendar();
				}

			}

		}

	}

	/**
	 * Adds a <tt>PaymentDetails</tt> to the set.
	 * <p>
	 * This method checks if there is only one billing method
	 * in the set, then makes this the default.
	 *
	 * @param billingDetails
	 */
	public void addMorePaymentDetails(List<PaymentDetail> paymentDetails) {
		if (paymentDetails == null)
			throw new IllegalArgumentException(
					"Can't add a null PaymentDetails.");
		for (PaymentDetail payment : paymentDetails) {
			payment.setPledge(this);
			this.getPaymentDetails().add(payment);
		}
	}

	/**
	 * Removes a <tt>PaymentDetails</tt> from the set.
	 * <p>
	 * This method checks if the removed is the default element,
	 * and if there is more than one
	 * left to chose from. This might actually not be the best way
	 * to handle this situation.
	 *
	 * @param billingDetails
	 * @throws BusinessException
	 */
	public void removePaymentDetails(Set<PaymentDetail> paymentDetails){
		/*for (PaymentDetail paymentDetail : paymentDetails) {
			paymentDetail.setPledge(null);
			this.getPaymentDetails().remove(paymentDetail);				
		}*/
		if (isPledgeComplete(paymentDetails)) {
			this.setPaymentStatus(PaymentStatusType.COMPLETE.name());
		} else {
			this.setPaymentStatus(PaymentStatusType.INACTIVE.name());
		}
	}
	public void removePaymentDetail(PaymentDetail paymentDetail){		
			this.getPaymentDetails().remove(paymentDetail);	
	}	
	public boolean isPledge25000Up(){
		return this.pledgeAmount>=25000;
	}
	public boolean isPledge10000Up(){
		return this.pledgeAmount>=10000 && this.pledgeAmount<25000;
	}
	public boolean isPledge5000Up(){
		if(!isPledge10000Up()){
		return this.pledgeAmount>=5000 && this.pledgeAmount<10000;
		}
		return false;
	}
	public boolean isPledge2500Up(){
		if(!isPledge5000Up()){
		return this.pledgeAmount>=2500 && this.pledgeAmount<5000;
		}
		return false;
	}
	public boolean isPledge100Up(){
		if( !isPledge2500Up()){
		return this.pledgeAmount>=100 && this.pledgeAmount<2500;
		}
		return false;
	}
	public boolean isActivePledge(){
		return this.paymentStatus.equalsIgnoreCase(PaymentStatusType.ACTIVE.name());
	}
	public boolean isDisabledPledge(){
		return this.paymentStatus.equalsIgnoreCase(PaymentStatusType.INACTIVE.name());
	}
	public void roundPledgeAmount(){
		double amount = this.getPledgeAmount().doubleValue();
		this.setPledgeAmount(new Double(Math.round(amount)));
	}
	public boolean isPledgeComplete(Set<PaymentDetail> paymentDetails){
		boolean isPledgeComplete = true;
		for (PaymentDetail paymentDetail : paymentDetails) {
			if (paymentDetail.getPaymentReceived() == false) {
				isPledgeComplete = false;
				break;
			}
		}
		return isPledgeComplete;
	}
	//is this pledge created during current report period
	public boolean isCurrent(Date startDate, Date endDate){
		if(null != startDate && null != endDate){
		if (this.getStartDate() != null ) {
			if (this.getStartDate().after(startDate)
					|| this.getStartDate().equals(startDate) &&this.getStartDate().before(endDate)
					|| this.getStartDate().equals(endDate)) {
				return true;
			}
		} else {
			return false;
		}
		}
		return false;
	}
	public String getPaymentFrequencyType() {
		return paymentFrequencyType;
	}

	public void setPaymentFrequencyType(String paymentFrequencyType) {
		this.paymentFrequencyType = paymentFrequencyType;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result
				+ ((pledgeType == null) ? 0 : pledgeType.hashCode());
		result = PRIME * result
				+ ((pledgeAmount == null) ? 0 : pledgeAmount.hashCode());
		result = PRIME * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pledge other = (Pledge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pledgeType == null) {
			if (other.pledgeType != null)
				return false;
		} else if (!pledgeType.equals(other.pledgeType))
			return false;
		if (pledgeAmount == null) {
			if (other.pledgeAmount != null)
				return false;
		} else if (!pledgeAmount.equals(other.pledgeAmount))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
