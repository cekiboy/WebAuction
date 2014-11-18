/**
 * 
 */
package com.levi9.app.webAuction.service;

import com.levi9.app.webAuction.model.User;

/**
 * @author Ceki
 *
 */
public interface UserService extends CrudService<User>{
	
	/**
	 * Return user.
	 * 
	 * @param username
	 * @return  user
	 */
	User findByUsername(String username);

}
