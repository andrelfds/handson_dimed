package com.handson.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.handson.ejb.AddressBean;
import com.handson.ejb.remote.AddressBeanRemote;
import com.handson.entity.Address;

@ManagedBean
@SessionScoped
public class AddressMB {

	private Address				address;
	private List<Address>		addresss;
	private boolean				viewListAddresss;
	
	
//	@EJB
//	private AddressBeanRemote	ejb;
	
	@Inject
	private AddressBean	ejb;

	public AddressMB() {
		address = new Address();
	}

	public void save() {
		ejb.save(address);
		mensagem();
	}
	
	
	private void mensagem() {
		String msg = "Adress: " + address.getStreet();
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("msg", fm);
		System.out.println(msg);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddresss() {
		return addresss;
	}

	public void setAddresss(List<Address> addresss) {
		this.addresss = addresss;
	}

	public boolean isViewListAddresss() {
		return viewListAddresss;
	}

	public void setViewListAddresss(boolean viewListAddresss) {
		this.viewListAddresss = viewListAddresss;
	}

//	public AddressBeanRemote getEjb() {
//		return ejb;
//	}
//
//	public void setEjb(AddressBeanRemote ejb) {
//		this.ejb = ejb;
//	}
	
	
}
