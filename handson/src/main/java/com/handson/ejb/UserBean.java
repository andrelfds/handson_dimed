package com.handson.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.handson.ejb.remote.UserBeanRemote;
import com.handson.entity.User;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class UserBean implements UserBeanRemote {

	@PersistenceContext
	private EntityManager	em;

	private List<User>		users;

	@PostConstruct
	private void init() {
		users = new ArrayList<User>();
	}

	public void save(User user) {
		em.persist(user);
	}

	public void remove(User user) {
		User u = find(user);
		em.remove(u);
	}

	public void update(User user) {
		em.merge(user);
		em.persist(user);
	}

	public User find(User user) {
		User  u = em.find(User.class, user.getId());
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		Query query = em.createNamedQuery("user.list");
		users = query.getResultList();
		return users;
	}

	
	public User findUserByPassword(User user) throws Exception {
		Query query = em.createNamedQuery("user.login").setParameter("name", user.getName()).setParameter("password", user.getPassword());
		if (query.getSingleResult() == null) {
			throw new Exception("Usuario inexistente ou senha errada");
		} else {
			user = (User) query.getSingleResult();
			user.setStatus((byte) 1);
			em.persist(user);
			return user;
		}
	}

	public boolean findUserLogado(User user) throws Exception {
		Query query = em.createNamedQuery("user.logado").setParameter("name", user.getName());
		if (query.getFirstResult() == 1) {
			return true;
		}
		return false;
	}

	
}
