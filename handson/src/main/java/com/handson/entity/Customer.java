package com.handson.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;


/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
@Table(name = "CUSTOMERS", uniqueConstraints=@UniqueConstraint(columnNames="REGISTRATION_DOCUMENT"))
	@NamedQueries({
	@NamedQuery(name = "customer.list", query = "SELECT c FROM Customer c"),
	@NamedQuery(name = "customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.id = :customerId"),
	@NamedQuery(name = "customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
	@NamedQuery(name = "customer.findByPersonalType", query = "SELECT c FROM Customer c WHERE c.personalType = :personalType"),
	@NamedQuery(name = "customer.findByRegistrationDate", query = "SELECT c FROM Customer c WHERE c.registrationDate = :registrationDate"),
	@NamedQuery(name = "customer.findByRegistrationDocument", query = "SELECT c FROM Customer c WHERE c.registrationDocument = :registrationDocument")
		})
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CUSTOMER_ID")
	@SequenceGenerator(sequenceName = "CUSTOMER_SEQ", name = "customer_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	private Long id;

	@Column(name="REGISTRATION_DOCUMENT")
	@Length(max=11)
	@CPF
	private String registrationDocument;
	
	private String name;
	
	@NotNull	
	@Column(name="PERSONAL_TYPE")
	@Enumerated(EnumType.STRING)
	private PersonalType personalType;
	
	
	@Column(name="REGISTRATION_DATE")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="address_customer", joinColumns = {
	        @JoinColumn(name = "customers_customer_id", referencedColumnName = "customer_id")}, inverseJoinColumns = {
	        @JoinColumn(name = "address_address_id", referencedColumnName = "address_id")
	})
	private Collection<Address> addressCollection;
	
	@Column
	private String test;
	
	
	
	public Customer() {
		registrationDate = new Date();
	}   
	public Long getId() {
		return this.id;
	}
   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public PersonalType getPersonalType() {
		return this.personalType;
	}

	public void setPersonalType(PersonalType personalType) {
		this.personalType = personalType;
	}   
	public   String getRegistrationDocument() {
		return this.registrationDocument;
	}

	public void setRegistrationDocument(String registrationDocument) {
		this.registrationDocument = registrationDocument;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public Collection<Address> getAddressCollection() {
		return addressCollection;
	}
	public void setAddressCollection(Collection<Address> addressCollection) {
		this.addressCollection = addressCollection;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", registrationDocument=" + registrationDocument + ", name=" + name + ", personalType=" + personalType + ", registrationDate=" + registrationDate
				+ ", addressCustomer=" + addressCollection + "]";
	}
	
	
   
}
