package com.handson.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ORDER_SALES")
public class OrderSales implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@Id
	@Column(name = "ORDER_SALES_ID", nullable = false)
	@SequenceGenerator(sequenceName = "ORDER_SALES_SEQ", name = "order_sales_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sales_generator")
	private Long				id;

	@OneToMany
	private Collection<Item>	items;

	@OneToOne
	private Customer			customer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				dateOrder;

	@Column(name="TOTAL_PRICE", nullable = false)
	private Double				totalPrice;

	@Column(name="DELIVERY_VALUE", nullable = false)
	private Double				deliveryValue;

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDeliveryValue() {
		return deliveryValue;
	}

	public void setDeliveryValue(Double deliveryValue) {
		this.deliveryValue = deliveryValue;
	}

	public Long getId() {
		return id;
	}
}