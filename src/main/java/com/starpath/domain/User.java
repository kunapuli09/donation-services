package com.starpath.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "USER")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(nullable = false)
	protected String password;

	@Transient
	protected String confirmPassword;

	@Column(name = "password_hint")
	protected String passwordHint;

	@Column(name = "first_name", nullable = false, length = 50)
	protected String firstName; // required

	@Column(name = "last_name", nullable = false, length = 50)
	protected String lastName; // required

	@Column(nullable = false, unique = true)
	protected String email; // required; unique

	@Column(name = "phone_number")
	protected String phoneNumber;

	@Transient
	protected String website;

	@Embedded
	protected Address address = new Address();

	@Version
	protected Integer version;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = com.starpath.domain.UserPrivilege.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "user")
	protected Set<UserPrivilege> roles = new HashSet<UserPrivilege>();

	@Column(name = "account_enabled")
	protected boolean enabled;

	@Column(name = "account_expired", nullable = false)
	protected boolean accountExpired;

	@Column(name = "account_locked", nullable = false)
	protected boolean accountLocked;

	@Column(name = "credentials_expired", nullable = false)
	protected boolean credentialsExpired;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = com.starpath.domain.Pledge.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "user")
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	protected Set<Pledge> pledges = new HashSet<Pledge>();
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@OneToMany(fetch = FetchType.EAGER, targetEntity = com.starpath.domain.UserFamilyMember.class, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "user")
   		private List<UserFamilyMember> userFamilyMembers = new ArrayList<UserFamilyMember>();
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	/**
	 * Returns the full name.
	 * @return firstName + ' ' + lastName
	 */
	@Transient
	public String getFullName() {
		return firstName + ' ' + lastName;
	}

	public Address getAddress() {
		return address;
	}

	public Set<UserPrivilege> getRoles() {
		return roles;
	}
	
	public List<UserFamilyMember> getUserFamilyMembers() {
		return userFamilyMembers;
	}

	/**
	 * Convert user roles to LabelValue objects for convenience.
	 * @return a list of LabelValue objects with role information
	 */
	/**
	 * Adds a role for the user
	 * @param role the fully instantiated role
	 */
	public void addRole(UserPrivilege role) {
		if (!this.getRoles().contains(role)) {
		role.setUser(this);
		getRoles().add(role);
		}
	}

	

	public void addMoreRoles(List<UserPrivilege> roles) {
		for (UserPrivilege role : roles) {
			if (!this.getRoles().contains(role)) {
				role.setUser(this);
				getRoles().add(role);
			}
		}
	}

	public Set<Pledge> getPledges() {
		return pledges;
	}

	/**
	 * Adds a <tt>Pledges</tt> to the set.
	 * <p>
	 * This method checks if there is only one billing method
	 * in the set, then makes this the default.
	 *
	 * @param pledge
	 */
	public void addPledge(Pledge pledge) {	
		PledgeStrategy pledgeStrategy = null;
		if (!this.getPledges().contains(pledge)) {				
			pledge.setUser(this);
			this.getPledges().add(pledge);
		}
	}

	/**
	 * Removes a <tt>Pledges</tt> from the set.
	 * <p>
	 * This method checks if the removed is the default element,
	 * and will throw a BusinessException if there is more than one
	 * left to chose from. This might actually not be the best way
	 * to handle this situation.
	 *
	 * @param pledge
	 * @throws BusinessException
	 */
	public void removePledge(Pledge pledge) throws BusinessLogicException {
		if (pledge == null)
			throw new IllegalArgumentException("Can't remove a null Pledge.");
		pledge.setUser(null);
		getPledges().remove(pledge);

	}

	public Integer getVersion() {
		return version;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Transient
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Transient
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Transient
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	
	/**
	 * Adds a <tt>UserFamilyMembers</tt> to the set.
	 * <p>
	 * This method enables to add list of family members
	 * to the user.
	 *
	 * @param userMembers
	 */
	
	public void addUserFamilyMembers(List<UserFamilyMember> userMembers) {
		for (UserFamilyMember userFamilyMember : userMembers) {
			if (!this.getUserFamilyMembers().contains(userFamilyMember)) {
				userFamilyMember.setUser(this);
				getUserFamilyMembers().add(userFamilyMember);
			}
		}
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;

		final User user = (User) o;

		return !(id != null ? !id.equals(user.getId()) : user.getId() != null);

	}

	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}
}
