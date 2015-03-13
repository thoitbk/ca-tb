package com.catb.web.viewmodel;


public class UserViewModel {
	
	private String username;
	private String fullName;
	private String password;
	private Boolean gender;
	private String address;
	private String homePhoneNumber;
	private String mobileNumber;
	private String officePhoneNumber;
	private String email;
	private Integer position;
	private Integer department;
	private String description;
	
	public UserViewModel() {
		
	}

	public UserViewModel(String username, String fullName, String password,
			Boolean gender, String address, String homePhoneNumber,
			String mobileNumber, String officePhoneNumber, String email,
			Integer position, Integer department, String description) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.homePhoneNumber = homePhoneNumber;
		this.mobileNumber = mobileNumber;
		this.officePhoneNumber = officePhoneNumber;
		this.email = email;
		this.position = position;
		this.department = department;
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
