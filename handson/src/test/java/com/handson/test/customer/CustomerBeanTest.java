package com.handson.test.customer;

import static org.junit.Assert.*;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import com.handson.ejb.CustomerBean;
import com.handson.entity.Customer;
import com.handson.entity.PersonalType;

public class CustomerBeanTest {
	
	@Inject
	private CustomerBean ejbCustomer = new ;

	@Test
	public void test() {
		Customer entity = new Customer();
		entity.setName("asdjkadksad");
		entity.setPersonalType(PersonalType.IndividualPhysical);
		entity.setRegistrationDate(new Date());
		ejbCustomer.save(entity);
	}

}
