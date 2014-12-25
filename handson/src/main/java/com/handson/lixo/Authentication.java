package com.handson.lixo;

import javax.ejb.Stateless;

import com.handson.ejb.local.AuthenticationLocal;
import com.handson.ejb.remote.AuthenticationRemote;

/**
 * Session Bean implementation class Authentication
 */
@Stateless
public class Authentication implements AuthenticationRemote, AuthenticationLocal {

    /**
     * Default constructor. 
     */
    public Authentication() {
        // TODO Auto-generated constructor stub
    }

	public boolean authentication(String login, String password) {
		System.out.println(login +" -- "+ password);
		return 	"admin".equals(login) && "123".equals(password);

	}

}
