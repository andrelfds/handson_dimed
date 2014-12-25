package com.handson.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;

import com.handson.ejb.CustomerBean;
import com.handson.entity.Address;
import com.handson.entity.Customer;

@ManagedBean
@SessionScoped
public class CustomerMB {

	private Customer			customer;
	private Address				address;
	private List<Customer>		customers;
	private boolean				viewListcustomers;
	
//	@EJB
//	private CustomerBeanRemote	ejbCustomer;
	
	@Inject
	private CustomerBean ejbCustomer;

	private boolean				errorOccured		= false;

	private int					currentLevel		= 1;

	public CustomerMB() {
		customer = new Customer();
		address = new Address();
		viewListcustomers = true;
	}

	public void save() {
		ejbCustomer.save(customer);
		mensagem();
	}

	public void addAddress() {
		customer.setAddressCollection(new ArrayList<Address>());
		customer.getAddressCollection().add(address);
		//customAddress.add(address);
	}

	public void remove() {
		ejbCustomer.remove(customer);
		mensagem();
	}

	public void onEdit(RowEditEvent event) {
		if (!(event == null)) {
			// customer currentcustomer = (customer) event.getOldValue();
			// Object oldValue newValue = event.getNewValue();

			Customer currentcustomer = (Customer) event.getObject();
			// ejb.update(currentcustomer);
			FacesMessage msg = new FacesMessage("customer Edited ", currentcustomer.getName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onDelete(RowEditEvent event) {
		if (!(event == null)) {
			Customer currentcustomer = (Customer) event.getObject();
			ejbCustomer.remove(currentcustomer);
			FacesMessage msg = new FacesMessage("customer Deleted ", ((Customer) event.getObject()).getName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(msg.toString());
		}
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void setActive(Customer customer) {

	}

	public List<Customer> listCustomers() {
		customers = ejbCustomer.listCustomer();
		return customers;
	}

	public void viewcustomersList() {
		this.viewListcustomers = true;
		System.out.println(viewListcustomers);
	}

	public void test() {
		mensagem();
	}

	public String adicionarCustomer() {
		customers.add(customer);
		customer = new Customer();

		// Retorna para a p�gina de entrada (index.xhtml).
		return "index";
	}

	private void mensagem() {
		String msg = "customer: " + customer.getName();
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("msg", fm);
		System.out.println(msg);
	}

	public int handleNavigation(SelectLevelEvent selectLevelEvent) {
		if (errorOccured) {
			return 2;
		} else {
			return selectLevelEvent.getNewLevel();
		}
	}

	public void setErrorOccured(boolean errorOccured) {
		this.errorOccured = errorOccured;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setcustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

//	public CustomerBeanRemote getEjb() {
//		return ejbCustomer;
//	}
//
//	public void setCustomerEjb(CustomerBeanRemote ejb) {
//		this.ejbCustomer = ejb;
//	}

	public boolean isViewListcustomers() {
		return viewListcustomers;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
