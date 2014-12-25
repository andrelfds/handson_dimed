package com.handson.ejb.local;

import javax.ejb.Local;

import com.handson.entity.Product;

@Local
public interface ShoppingCart {

	void addProduct(Product product);
	
	void completePurchase();
}
