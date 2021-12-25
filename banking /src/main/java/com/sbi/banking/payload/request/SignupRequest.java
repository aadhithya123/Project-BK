package com.sbi.banking.payload.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sbi.banking.model.Role;

public class SignupRequest
{
	@NotBlank
    @Size(min = 3, max = 20)
	private String name;
	
	@NotBlank
	@Column(name = "date_of_birth")
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date dateOfBirth;
	
	@NotBlank
    @Size(max = 50)
    @Email
	private String email;
	
	@NotBlank
	@Column(name = "phone_number", unique = true)
	@Size(max = 10)
	private String phoneNumber;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String nationality;
	
	@NotBlank
	@Column(name= "permanent_address")
	private String permanentAddress;
	
	@NotBlank
	@Column(name= "marital_status")
	@Size(min=7, max = 9)
	private String maritalStatus;
     
	@NotBlank
	@Column(name = "aadhar_number", unique = true)
	@Pattern(regexp = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$")
	private Long aadharNumber;
	
	@NotBlank
 //   @Size(min = 6, max = 40)
	private String password;
	

	private Set<String> role;
	
	private Date creditedOn;
	private Boolean active;
	private Boolean deactive;
	
	
	
	
	@Override
	public String toString() {
		return "SignupRequest [name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", nationality=" + nationality + ", permanentAddress="
				+ permanentAddress + ", maritalStatus=" + maritalStatus + ", aadharNumber=" + aadharNumber
				+ ", password=" + password + ", role=" + role + ", creditedOn=" + creditedOn + ", active=" + active
				+ ", deactive=" + deactive + "]";
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SignupRequest(@NotBlank @Size(min = 3, max = 20) String name, @NotBlank Date dateOfBirth,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank String phoneNumber, @NotBlank String gender,
			@NotBlank String nationality, @NotBlank String permanentAddress, @NotBlank String maritalStatus,
			@NotBlank Long aadharNumber, @NotBlank String password) {
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
//		this.role = role;
//		this.creditedOn = creditedOn;
//		this.active = active;
//		this.deactive = deactive;
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
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
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

    
	
}
