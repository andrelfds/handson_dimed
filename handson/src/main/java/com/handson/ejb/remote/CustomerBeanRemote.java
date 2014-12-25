package com.handson.ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import com.handson.entity.Customer;

@Remote
public interface CustomerBeanRemote {

	void save(Customer customer);
	void remove(Customer customer);
	Customer  find(Customer customer);
	void update(Customer customer);
	List<Customer> listCustomer();
}
