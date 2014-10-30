/**
 * 
 */
package com.levi9.app.webAuction.service;

import java.util.Date;
import java.util.List;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.model.AuctionBid;

/**
 * Service for web auctions management.
 * @author Ceki
 *
 */
public interface AuctionService extends CrudService<Auction>{

	/**
     * Find and return auctions by name.
     * @param name the auction name
     * @return list of auctions which name contains name
     */
    List<Auction> findByActiveTrueAndNameContaining(String name);
	
	/**
     * Find and return auctions by user username.
     * @param username the user username
     * @return list of auctions from a user
     */
    List<Auction> findByActiveTrueAndUserUsername(String username);
	/**
     * Find and return auctions which are active but expired.
     * @return list of active expired auctions
     */
    List<Auction> findByActiveTrueAndEndTimeBefore(Date date);
    
    /**
     * Find and return auctions by id of user who made a bid on it.
     * @param bidderId the id of user who made a bid
     * @return list of auctions 
     */
    List<Auction> findByBidderId(Long bidderId);
    
    /**
     * Deactivate auctions which are active but expired.
     * @return list of active expired auctions
     */
    List<Auction> deactivateExpiredAuctions();
    
    /**
     * Find and return auctions which are active .
     * @return list of active  auctions
     */
    List<Auction> findByActiveTrue();

    
	/**
	 * Return all auctions of a user.
	 * 
	 * @param userId
	 * @return list of auctions associated with provided user
	 */
	List<Auction> findByActiveTrueAndUserId(Long userId);
	
	
	/**
	 * Adds new bid to the auction.
	 * 
	 * @param auction the auction to which new actionBid is added
	 * @param auctionBid the auctionBid to be added
	 */
	void addBid(Auction auction, AuctionBid auctionBid);
}
