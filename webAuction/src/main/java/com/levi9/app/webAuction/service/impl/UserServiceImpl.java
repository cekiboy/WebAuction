/**
 * 
 */
package com.levi9.app.webAuction.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.app.webAuction.model.User;
import com.levi9.app.webAuction.repository.UserRepository;
import com.levi9.app.webAuction.service.UserService;

/**
 * @author Ceki
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User save(User user) throws DataIntegrityViolationException{
		return userRepository.save(user);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void remove(Long id) throws IllegalArgumentException {
		User user= userRepository.findOne(id);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
		userRepository.delete(id);
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

}
