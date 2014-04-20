package com.starpath.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;
import org.jasypt.hibernate.type.EncryptedStringType;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

/**
 * 
 * @author Krishna Kunapuli
 *
 */
@Entity
@Table(name = "PAYMENT_DETAIL")
@TypeDef(
    name="encryptedString", 
    typeClass=EncryptedStringType.class, 
    parameters= {
        @Parameter(name="encryptorRegisteredName", value="strongHibernateStringEncryptor")
    }
)
public class PaymentDetail implements Serializable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_DETAIL_ID")
	private Long id = null;

	@Version
	@Column(name = "OBJ_VERSION")
	private int version = 0;

	@ManyToOne(targetEntity = com.starpath.domain.Pledge.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PLEDGE_ID", updatable = false)
	//@org.hibernate.annotations.ForeignKey(name = "FK_PLEDGE_ID")
	private Pledge pledge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, updatable = false)
	private Date created = new Date();

	@Column(name = "BA_ACCOUNT", nullable = true)
	@Type(type="encryptedString")
	private String account;

	@Column(name = "BA_BANKNAME", nullable = true)
	@Type(type="encryptedString")
	private String bankname;

	@Column(name = "BA_SWIFT", nullable = true)
	@Type(type="encryptedString")
	private String swift;
	
	@Column(name = "CHECK_NUMBER", nullable = true)
	@Type(type="encryptedString")
	private String checkNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENT_DATE", nullable = true)
	private Date paymentDate = new Date();

	@Column(name = "PAYMENT_AMOUNT", nullable = true)
	private Double paymentAmount;

	//@Enumerated(EnumType.STRING)
	@Column(name = "CC_TYPE", nullable = true)
	private String type;

	@Column(name = "CC_NUMBER", nullable = true, length = 16)
	private String number;

	@Column(name = "CC_EXP_MONTH", nullable = true, length = 2)
	private String expMonth;

	@Column(name = "CC_EXP_YEAR", nullable = true, length = 4)
	private String expYear;

	@Column(name = "PAYMENT_RECEIVED", nullable = true)
	private Boolean paymentReceived;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public PaymentDetail() {
		//TODO find a way to load it only once per app
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		strongEncryptor.setPassword("jasypt");
		HibernatePBEEncryptorRegistry registry =
		      HibernatePBEEncryptorRegistry.getInstance();		
		  registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);
		
	}

	/**
	 * Full constructor
	 */
	protected PaymentDetail(Pledge pledge, String account, String bankname,
			String swift, Date paymentDate, Double paymentAmount) {
		this.pledge = pledge;
		this.account = account;
		this.bankname = bankname;
		this.swift = swift;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
	}

	// ********************** Accessor Methods ********************** //

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	// ********************** Common Methods ********************** //

	public int compareTo(Object o) {
		if (o instanceof PaymentDetail)
			// Don't compare Date objects! Use the time in milliseconds!
			return Long.valueOf(this.getCreated().getTime()).compareTo(
					Long.valueOf(((PaymentDetail) o).getCreated().getTime()));
		return 0;
	}

	// ********************** Business Methods ********************** //

	/**
	 * Checks if the payment information is correct.
	 * <p>
	 * Check algorithm is implemented in subclasses.
	 *
	 * @return boolean
	 */
	public boolean isValid() {
		return false;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pledge getPledge() {
		return pledge;
	}

	public void setPledge(Pledge pledge) {
		this.pledge = pledge;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Boolean getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(Boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	//business method
	public boolean isRecognizableBy(Date date) {
		if (this.getPaymentDate() != null
				&& this.getPaymentReceived() != null && this.getPaymentReceived().booleanValue() == true) {
			if (date.after(this.getPaymentDate())
					|| date.equals(this.getPaymentDate())) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}	
	//is this payment is made during current report period
	public boolean isCurrent(Date startDate, Date endDate) {
		if(null != startDate && null != endDate){
		if (this.getPaymentDate() != null
				&& this.getPaymentReceived() != null && this.getPaymentReceived().booleanValue() == true) {
			if (this.getPaymentDate().after(startDate)
					|| this.getPaymentDate().equals(startDate) && this.getPaymentDate().before(endDate)
					|| this.getPaymentDate().equals(endDate)) {
				return true;
			}
		} else {
			return false;
		}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((account == null) ? 0 : account.hashCode());
		result = PRIME * result
				+ ((bankname == null) ? 0 : bankname.hashCode());
		result = PRIME * result + ((created == null) ? 0 : created.hashCode());
		result = PRIME * result
				+ ((expMonth == null) ? 0 : expMonth.hashCode());
		result = PRIME * result + ((expYear == null) ? 0 : expYear.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((number == null) ? 0 : number.hashCode());
		result = PRIME * result
				+ ((paymentAmount == null) ? 0 : paymentAmount.hashCode());
		result = PRIME * result
				+ ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = PRIME * result
				+ ((paymentReceived == null) ? 0 : paymentReceived.hashCode());
		result = PRIME * result + ((pledge == null) ? 0 : pledge.hashCode());
		result = PRIME * result + ((swift == null) ? 0 : swift.hashCode());
		result = PRIME * result + ((type == null) ? 0 : type.hashCode());
		result = PRIME * result + version;
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
		final PaymentDetail other = (PaymentDetail) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (bankname == null) {
			if (other.bankname != null)
				return false;
		} else if (!bankname.equals(other.bankname))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (expMonth == null) {
			if (other.expMonth != null)
				return false;
		} else if (!expMonth.equals(other.expMonth))
			return false;
		if (expYear == null) {
			if (other.expYear != null)
				return false;
		} else if (!expYear.equals(other.expYear))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (paymentAmount == null) {
			if (other.paymentAmount != null)
				return false;
		} else if (!paymentAmount.equals(other.paymentAmount))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (paymentReceived == null) {
			if (other.paymentReceived != null)
				return false;
		} else if (!paymentReceived.equals(other.paymentReceived))
			return false;
		if (pledge == null) {
			if (other.pledge != null)
				return false;
		} else if (!pledge.equals(other.pledge))
			return false;
		if (swift == null) {
			if (other.swift != null)
				return false;
		} else if (!swift.equals(other.swift))
			return false;
		if (checkNumber == null) {
			if (other.checkNumber != null)
				return false;
		} else if (!checkNumber.equals(other.checkNumber))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (version != other.version)
			return false;
		return true;
	}	
}
