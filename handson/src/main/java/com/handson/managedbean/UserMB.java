package com.handson.managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.handson.ejb.remote.UserBeanRemote;
import com.handson.entity.User;

@ManagedBean
@SessionScoped
public class UserMB {

	private User			user;
	private List<User>		users;
	private boolean			viewListUsers;
	@EJB
	private UserBeanRemote	ejb;

	public UserMB() {
		user = new User();
		viewListUsers = false;
	}

	public void save() {
		ejb.save(user);
		mensagem();
	}

	public void onEdit(RowEditEvent event) {
		if (!(event == null)) {
			//User  currentUser = (User) event.getOldValue(); 
	        //Object oldValue newValue = event.getNewValue();

			User currentUser = (User) event.getObject();
			//ejb.update(currentUser);
			FacesMessage msg = new FacesMessage("User Edited ", currentUser.getName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onDelete(RowEditEvent event) {
		if (!(event == null)) {
			User currentUser = (User) event.getObject();
			ejb.remove(currentUser);
			FacesMessage msg = new FacesMessage("User Deleted ", ((User) event.getObject()).getName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(msg.toString());
		}
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue(); 
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

	public boolean findUserLogado() throws Exception {
		mensagem();
		return ejb.findUserLogado(user);
	}

	public User getUserByPassword() throws Exception {
		User u = ejb.findUserByPassword(user);
		return u;
	}

	public void setActive(User user) {

	}

	public List<User> listUsers() {
		users = ejb.listUsers();
		return users;
	}

	public void viewUsersList() {
		this.viewListUsers = true;
		System.out.println(viewListUsers);
	}

	public void test() {
		mensagem();
	}

	public String adicionarUser() {
		users.add(user);
		user = new User();

		// Retorna para a p�gina de entrada (index.xhtml).
		return "index";
	}

	private void mensagem() {
		String msg = "User: " + user.getName();
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("msg", fm);
		System.out.println(msg);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserBeanRemote getEjb() {
		return ejb;
	}

	public void setEjb(UserBeanRemote ejb) {
		this.ejb = ejb;
	}

	public boolean isViewListUsers() {
		return viewListUsers;
	}

}
