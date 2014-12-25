package com.handson.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_ID", nullable = false)
	@SequenceGenerator(sequenceName="ITEM_SEQ",name="item_generator")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_generator")
	private Long id;
	
	@OneToOne
	private Product product;
   
	private Integer amount;
    private Double price;

	public Long getId() {
		return id;
	}    
    
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
 
    public Integer getQty() {
        return amount;
    }
 
    public void setQty(Integer qty) {
        this.amount = qty;
    }
 
    /*

    private static final ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
 
    public ArrayList<OrderBean> getOrderList() {
        return orderList;
    }
 
    public String addAction() {
        OrderBean orderitem = new OrderBean(this.item, this.qty, this.price);
        orderList.add(orderitem);
 
        item = "";
        qty = 0;
        price = 0.0;
        return null;
    }
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Edited",((OrderBean) event.getObject()).getItem());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        orderList.remove((OrderBean) event.getObject());
    } */
}
