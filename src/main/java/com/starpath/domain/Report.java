package com.starpath.domain;

import java.util.List;

public class Report {
	private int totalNumberOfUsers;
	private String usersDonated25000Up;
	private String usersDonated10000Up;
	private String  usersDonated5000Up;
	private String usersDonated2500Up;
	private String usersDonated100Up;
	private int numberOfNewPledges;
	private int totalActivePledges;
	private int totalDisabledPledges;
	private double revenueThisPeriod;
	private double totalRevenueAsOfDate;
	private int numberOfPaymentsReceived;
	/*Sponsor (Total Contribution of $25,000 or more)
	Patron (Total Contribution of $10,000 to $24,999)
	Benefactor (Total Contribution of $5,000 to $9,999)
	Supporter (Total Contribution of $2,500 to $4999)  
	Member (Total Contribution of $1,000 to $2499)  */
	public int getNumberOfNewPledges() {
		return numberOfNewPledges;
	}
	public void setNumberOfNewPledges(int numberOfNewPledges) {
		this.numberOfNewPledges = numberOfNewPledges;
	}	
	public int getNumberOfPaymentsReceived() {
		return numberOfPaymentsReceived;
	}
	public void setNumberOfPaymentsReceived(int numberOfPaymentsReceived) {
		this.numberOfPaymentsReceived = numberOfPaymentsReceived;
	}
	public double getRevenueThisPeriod() {
		return revenueThisPeriod;
	}
	public void setRevenueThisPeriod(double revenueThisPeriod) {
		this.revenueThisPeriod = revenueThisPeriod;
	}
	public int getTotalActivePledges() {
		return totalActivePledges;
	}
	public void setTotalActivePledges(int totalActivePledges) {
		this.totalActivePledges = totalActivePledges;
	}
	public int getTotalDisabledPledges() {
		return totalDisabledPledges;
	}
	public void setTotalDisabledPledges(int totalDisabledPledges) {
		this.totalDisabledPledges = totalDisabledPledges;
	}
	public int getTotalNumberOfUsers() {
		return totalNumberOfUsers;
	}
	public void setTotalNumberOfUsers(int totalNumberOfUsers) {
		this.totalNumberOfUsers = totalNumberOfUsers;
	}
	public double getTotalRevenueAsOfDate() {
		return totalRevenueAsOfDate;
	}
	public void setTotalRevenueAsOfDate(double totalRevenueAsOfDate) {
		this.totalRevenueAsOfDate = totalRevenueAsOfDate;
	}
	public String getUsersDonated10000Up() {
		return usersDonated10000Up;
	}
	public void setUsersDonated10000Up(String usersDonated10000Up) {
		this.usersDonated10000Up = usersDonated10000Up;
	}
	public String getUsersDonated100Up() {
		return usersDonated100Up;
	}
	public void setUsersDonated100Up(String usersDonated100Up) {
		this.usersDonated100Up = usersDonated100Up;
	}
	public String getUsersDonated25000Up() {
		return usersDonated25000Up;
	}
	public void setUsersDonated25000Up(String usersDonated25000Up) {
		this.usersDonated25000Up = usersDonated25000Up;
	}
	public String getUsersDonated2500Up() {
		return usersDonated2500Up;
	}
	public void setUsersDonated2500Up(String usersDonated2500Up) {
		this.usersDonated2500Up = usersDonated2500Up;
	}
	public String getUsersDonated5000Up() {
		return usersDonated5000Up;
	}
	public void setUsersDonated5000Up(String usersDonated5000Up) {
		this.usersDonated5000Up = usersDonated5000Up;
	}
	

	
	

}
