package com.handson.ejb.remote;

import javax.ejb.Remote;

/**
 * @author cassio
 * @since 30/11/2014
 */

@Remote
public interface AuthenticationRemote {

	public boolean  authentication(String login, String password); 
}
