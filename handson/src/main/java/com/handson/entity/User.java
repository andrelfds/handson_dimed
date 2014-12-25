package com.handson.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Entity implementation class for Entity: User
 * 
 * @author cassio
 * @since 30/11/2014
 */

@Entity
@Table(name = "USERS", uniqueConstraints=@UniqueConstraint(columnNames="name"))
@NamedQueries({ @NamedQuery(name = "user.list", query = "select u from User u"),
				@NamedQuery(name = "user.login", query = "select u from User u where u.password = :password and u.name=:name"),
				@NamedQuery(name = "user.logado", query = "select u.status from User u where u.name=:name")})
public class User implements Serializable {

	private static final long	serialVersionUID	= 1L;
	
	public static final String	TIPO_ADMINISTRADOR	= "ADMINISTRADOR";
	public static final String	TIPO_FUNCIONARIO	= "FUNCIONARIO";
	public static final String	TIPO_CONVIDADO		= "CONVIDADO";

	public static final byte	ADMINISTRADOR		= 0;
	public static final byte	FUNCIONARIO			= 1;
	public static final byte	CONVIDADO			= 2;

	public static final byte	INATIVO				= 0;
	public static final byte	ATIVO				= 1;

	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(sequenceName = "USER_SEQ", name = "user_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	private Long				id;

	private String				name;

	private String				password;

	@Column(name="REGISTRATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				registrationDate	= new Date();

	private byte				tipo;

	private byte				status;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
