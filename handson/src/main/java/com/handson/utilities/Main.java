package com.handson.utilities;

import javax.inject.Inject;

import com.handson.ejb.CustomerBean;

public class Main {
	
	@Inject
	CustomerBean ejbCustomer = new CustomerBean();
	
	public static void main(String[] args) {
	}

}
