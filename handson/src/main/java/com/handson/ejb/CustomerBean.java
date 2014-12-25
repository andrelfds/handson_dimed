package com.handson.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.handson.bd.CustomerBD;
import com.handson.entity.Customer;
import com.handson.infra.AppRN;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateless
public class CustomerBean extends AppRN<Customer, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerBD customerBD;

	public CustomerBean() {
		setBD(customerBD);
	}
	
	@Inject
	public void setBD(CustomerBD bd) {
		super.setBD(bd);
	}
	
	public List<Customer> listCustomer() {
		return customerBD.listCustomer();
	}
	
}
