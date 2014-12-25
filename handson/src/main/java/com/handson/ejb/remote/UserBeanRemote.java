package com.handson.ejb.remote;
import java.util.List;

import javax.ejb.Remote;

import com.handson.entity.User;

/**
 * @author cassio
 * @since 30/11/2014
 */

@Remote
public interface UserBeanRemote {
	
	void save(User user);
	void remove(User user);
	User find(User user);
	void update(User user);
	List<User> listUsers();
	
	User findUserByPassword(User user) throws Exception;
	boolean findUserLogado(User user) throws Exception;
	
	
}
