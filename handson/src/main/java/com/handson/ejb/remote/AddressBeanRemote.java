package com.handson.ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import com.handson.entity.Address;

@Remote
public interface AddressBeanRemote {

	void save(Address address);
	void remove(Address address);
	Address  find(Address address);
	void update(Address address);
	List<Address> listAddress();
}
