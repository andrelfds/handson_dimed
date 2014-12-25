package com.handson.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.handson.ejb.local.ShoppingCart;
import com.handson.entity.Product;



@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class ShoppingCartBean implements ShoppingCart {

	@PersistenceContext
	private EntityManager em;
	
	private List<Product> products;
	
	@PostConstruct
	private void init(){
		products = new ArrayList<Product>();
	}
	
	public void addProduct(Product product){
		products.add(product);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void completePurchase(){
		for(Product product : products){
			em.persist(product);
		}
		products.clear();
	}

}
