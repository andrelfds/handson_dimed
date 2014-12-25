package com.handson.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.handson.ejb.local.AddressBeanLocal;
import com.handson.ejb.remote.AddressBeanRemote;
import com.handson.entity.Address;
import com.handson.infra.AppBD;


public class AddressBD extends AppBD<Address, Long> {

	@PersistenceContext
	private EntityManager	em;
	private List<Address>	address;
	
	@PostConstruct
	void init() {
		this.setEntityManager(em);
	}
	
    /**
     * Default constructor. 
     */
    public AddressBD() {
    	address = new ArrayList<Address>();
    }
    
    @Override
    public Address save(Address entity) {
    	// TODO Auto-generated method stub
    	return super.save(entity);
    }
    
    @Override
    public void remove(Address entity) {
    	// TODO Auto-generated method stub
    	super.remove(entity);
    }
    
    @Override
    public Address update(Address entity) {
    	// TODO Auto-generated method stub
    	return super.update(entity);
    }

	@SuppressWarnings("unchecked")
	public List<Address> listAddress() {
		Query query = em.createNamedQuery("address.list");
		address = query.getResultList();
		return address;
	}
	
	@Override
	public Address find(Long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

}
