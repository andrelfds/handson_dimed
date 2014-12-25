package com.handson.infra;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AppBD<Entity, PK> {
	
	
	private EntityManager entityManager;
	private Class<Entity> entityClass;
	private String namedQueryConsulta;

	@SuppressWarnings("unchecked")
	public AppBD() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
		namedQueryConsulta = entityClass.getSimpleName() + ".consulta";
	}
	
	public Entity save(Entity entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}
	
	public Entity update(Entity entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}
	
//	public void remove(Entity entity) {
//		Query query = entityManager.createQuery(queryExclui);
//		query.setParameter("id", entity.getId());
//		query.executeUpdate();
//	}
	
	public void remove(Entity entity) {
		entityManager.remove(entity);
		entityManager.flush();
	}
	
	@SuppressWarnings("unchecked")
	public Entity find(PK id) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
		namedQueryConsulta = entityClass.getSimpleName() + ".consulta";
		Query query = entityManager.createNamedQuery(namedQueryConsulta);
		query.setParameter("id", id);
		return (Entity) query.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}
