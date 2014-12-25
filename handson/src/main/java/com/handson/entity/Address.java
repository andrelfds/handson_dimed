package com.handson.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Adress
 * 
 */
@Entity
@Table(name = "ADDRESS")
@NamedQueries({ 
		@NamedQuery(name = "address.list", query = "SELECT a FROM Address a"), 
		@NamedQuery(name = "addressCustomer.list", query = "select a from Address a where a.customers = :customer"),
		@NamedQuery(name = "address.findByAddressId", query = "SELECT a FROM Address a WHERE a.id = :addressId"),
		@NamedQuery(name = "address.findByAddition", query = "SELECT a FROM Address a WHERE a.addition = :addition"),
		@NamedQuery(name = "address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
		@NamedQuery(name = "address.findByDistrict", query = "SELECT a FROM Address a WHERE a.district = :district"),
		@NamedQuery(name = "address.findByNumber", query = "SELECT a FROM Address a WHERE a.number = :number"),
		@NamedQuery(name = "address.findByState", query = "SELECT a FROM Address a WHERE a.state = :state"),
		@NamedQuery(name = "address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
		@NamedQuery(name = "address.findByZip", query = "SELECT a FROM Address a WHERE a.zip = :zip") })
public class Address implements Serializable {

	@Id
	@Column(name = "ADDRESS_ID")
	@SequenceGenerator(sequenceName = "ADDRESS_SEQ", name = "address_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
	private Long				id;

	@NotNull
	private String				street;

	@NotNull
	private Integer				number1;

	@NotNull
	private String				city;

	private String				addition;

	@NotNull
	private String				state;

	private String				district;

	@NotNull
	private String				zip;

	@ManyToMany(mappedBy = "addressCollection")
	private List<Customer>		customers;

	private static final long	serialVersionUID	= 1L;

	public Address() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return this.number1;
	}

	public void setNumber(Integer number) {
		this.number1 = number;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddition() {
		return this.addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", number=" + number1 + ", city=" + city + ", addition=" + addition + ", state=" + state + ", district=" + district + ", zip=" + zip
				+ ", customers=" + customers + "]";
	}

}
