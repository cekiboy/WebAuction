/**
 * 
 */
package com.levi9.app.webAuction.repository;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.Repository;

import com.levi9.app.webAuction.model.User;

/**
 * @author Ceki
 *
 */
public interface UserRepository extends Repository<User, Long>{
	
	/**
     * Find and return user by username.
     * @param id the user id
     * @return specific user
     */
    User findUserByUsername(String username);
	
	/**
     * Find and return entity with passed id.
     *
     * @param id of the entity to return
     * @return entity with passed id or null if not found
     */
	User findOne(Long id);

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<User> findAll();

    /**
     * Save entity and return saved instance (with id set).
     *
     * @param  user to be saved
     * @return saved instance
     */
    User save(User user) throws DataIntegrityViolationException;

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     */
    void delete(Long id);
	

}
