package com.handson.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.handson.ejb.remote.UserLoggedInBeanRemote;

/**
 * Session Bean implementation class UserLoggedInBean
 */
@Stateful
@LocalBean
public class UserLoggedInBean implements UserLoggedInBeanRemote {

    /**
     * Default constructor. 
     */
    public UserLoggedInBean() {
        
    }
    


}
