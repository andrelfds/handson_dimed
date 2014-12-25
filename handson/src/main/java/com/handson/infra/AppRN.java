package com.handson.infra;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.io.Serializable;

import javax.ejb.TransactionAttribute;

public class AppRN<Entity, PK> implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private AppBD<Entity, PK> bd;
	

	@TransactionAttribute(SUPPORTS)
	public Entity find(PK id) {
		return bd.find(id);
	}
	

	public Entity save(Entity entity) {
		return bd.save(entity);
	}
	

	public Entity update(Entity entity) {
		return bd.update(entity);
	}
	

	public void remove(Entity entity) {
		bd.remove(entity);
	}

//	@TransactionAttribute(SUPPORTS)
//	public List<Entity> lista(Entity ped){
//		return bd.lista(ped);
//	}
	
	// Getters & Setters 

	public void setBD(AppBD<Entity, PK> bd) {
		this.bd = bd;
	}
}
