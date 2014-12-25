package com.handson.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserLoggedIn {
	@Id
	@Column(name = "USER_LOGGEDIN_ID")
	@SequenceGenerator(sequenceName = "USER_LOGGEDIN_SEQ", name = "user_loggedin_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_loggedin_generator")
	private Long	userLoggedid;
	
	private Long	userId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	dateLogin;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	dateLogout;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDateLogin() {
		return dateLogin;
	}
	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}
	public Date getDateLogout() {
		return dateLogout;
	}
	public void setDateLogout(Date dateLogout) {
		this.dateLogout = dateLogout;
	}
	public Long getUserLoggedid() {
		return userLoggedid;
	}
	
	
}
