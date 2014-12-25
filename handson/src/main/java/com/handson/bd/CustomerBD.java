package com.handson.bd;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.handson.entity.Customer;
import com.handson.infra.AppBD;


public class CustomerBD extends AppBD<Customer, Long> {

	@PersistenceContext
	private EntityManager	em;
	private List<Customer>	listCustomer;
	
	@PostConstruct
	void init() {
		this.setEntityManager(em);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> listCustomer() {
		Query query = em.createNamedQuery("customer.list");
		listCustomer = query.getResultList();
		return listCustomer;
	}
	
	@Override
	public Customer find(Long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

}
