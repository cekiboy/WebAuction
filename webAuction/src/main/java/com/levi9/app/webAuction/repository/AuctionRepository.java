/**
 * 
 */
package com.levi9.app.webAuction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.levi9.app.webAuction.model.Auction;

/**
 * @author Ceki
 *
 */
public interface AuctionRepository extends Repository<Auction, Long>{

	
	/**
     * Find and return auctions which are active .
     * @return list of active  auctions
     */
    List<Auction> findAuctionsByActiveTrue();

	/**
     * Find and return auctions which are active but expired.
     * @return list of active expired auctions
     */
    List<Auction> findAuctionsByActiveTrueAndEndTimeBefore(Date date);
    
	/**
     * Find and return auctions by user id.
     * @param id the user id
     * @return list of auctions from a user
     */
    List<Auction> findAuctionsByUserId(Long id);
    
    /**
     * Find and return auctions by user username.
     * @param username the user username
     * @return list of auctions from a user
     */
    List<Auction> findAuctionsByUserUsernameContaining(String username);
    
    /**
     * Find and return auctions by name.
     * @param name the auction name
     * @return list of auctions which name contains name
     */
    List<Auction> findAuctionsByNameContaining(String name);
	
	/**
     * Find and return entity with passed id.
     *
     * @param id of the entity to return
     * @return entity with passed id or null if not found
     */
    Auction findOne(Long id);

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<Auction> findAll();

    /**
     * Save entity and return saved instance (with id set).
     *
     * @param  auction to be saved
     * @return saved instance
     */
    Auction save(Auction auction);

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     * @throws IllegalArgumentException if there is no entity with passed id
     */
    void delete(Long id) throws IllegalArgumentException;
	
}
