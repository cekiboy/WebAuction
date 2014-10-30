/**
 * 
 */
package com.levi9.app.webAuction.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.levi9.app.webAuction.model.AuctionBid;

/**
 * @author Ceki
 *
 */
public interface AuctionBidRepository extends Repository<AuctionBid, Long>{

	/**
     * Find and return entity with passed id.
     *
     * @param id of the entity to return
     * @return entity with passed id or null if not found
     */
	AuctionBid findOne(Long id);

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<AuctionBid> findAll();

    
    /**
     * Save entity and return saved instance (with id set).
     *
     * @param  auction bid to be saved
     * @return saved instance
     */
    AuctionBid save(AuctionBid auctionBid);

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     */
    void delete(Long id);
	
}
