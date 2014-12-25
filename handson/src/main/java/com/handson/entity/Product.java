package com.handson.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_ID", nullable = false)
	@SequenceGenerator(sequenceName="PRODUCT_SEQ",name="product_generator")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_generator")
	private Long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "PRICE", nullable = false)
	private String price;

	@Column(name = "VALIDATE_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date validateDate;
	
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}
	
	

}
