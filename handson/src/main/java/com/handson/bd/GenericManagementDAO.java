package com.handson.bd;

import java.util.List;

public interface GenericManagementDAO<t> {
	void save(t entity);
	void remove(t entity);
	t find(t entity);
	void update(t entity);
	List<t> listUsers();
}
