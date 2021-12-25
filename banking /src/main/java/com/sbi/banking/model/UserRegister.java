package com.sbi.banking.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "name"),
	@UniqueConstraint(columnNames = "email") 
},
catalog = "bank_db")
public class UserRegister 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(name = "date_of_birth")
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date dateOfBirth;
	
	@Column(unique = true)
	private String email;
	
    @Column(name = "phone_number", unique = true)
	private String phoneNumber;
    
	private String gender;

	private String nationality;
	
	@Column(name= "permanent_address")
	private String permanentAddress;
	
	@Column(name= "marital_status")
	private String maritalStatus;
	
	@Column(name = "aadhar_number", unique = true)
	private Long aadharNumber;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "userId"), 
				inverseJoinColumns = @JoinColumn(name = "roleId"))
	@JsonProperty("role")
	private Set<Role> role = new HashSet<>();
	
	private Date creditedOn;
	private Boolean active;
	private Boolean deactive;
	
	@Override
	public String toString() {
		return "UserRegister [userId=" + userId + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", nationality=" + nationality
				+ ", permanentAddress=" + permanentAddress + ", maritalStatus=" + maritalStatus + ", aadharNumber="
				+ aadharNumber + ", password=" + password + ", role=" + role + ", creditedOn=" + creditedOn
				+ ", active=" + active + ", deactive=" + deactive + "]";
	}
	public UserRegister()
	{
		super();
	}
	public UserRegister(String name, String email, String phoneNumber, Date dateOfBirth, Long aadharNumber,
			String gender, String maritalStatus, String nationality, String permanentAddress, String password, Date creditedOn, Boolean active, Boolean deactive) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.permanentAddress = permanentAddress;
		this.maritalStatus = maritalStatus;
		this.aadharNumber = aadharNumber;
		this.password = password;
		this.creditedOn = creditedOn;
		this.active = active;
		this.deactive = deactive;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Bean
	public Date getCreditedOn() {
		return creditedOn;
	}

	public void setCreditedOn(Date creditedOn) {
		this.creditedOn = creditedOn;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDeactive() {
		return deactive;
	}

	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
}