package com.example.demo.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_details")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
//    @Column(name = "user_id")
//    private String userId;
//	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_name", nullable=false, unique=true)
    private String userName;
    @Column(name = "password", nullable=false, unique=true)
    private String password;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "email", nullable=false, unique=true)
    private String email;
    @Column(name = "mobile_number", nullable=false, unique=true)
    private String mobileNumber;
    @Column(name = "confirmed")
    private String confirmed;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
//	public User(Integer id, String userName, String password, String userType, String email, Integer mobileNumber,
//			String confirmed) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.password = password;
//		this.userType = userType;
//		this.email = email;
//		this.mobileNumber = mobileNumber;
//		this.confirmed = confirmed;
//	}
//	public User() {
//		super();
//	}        

}
