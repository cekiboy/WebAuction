/**
 * 
 */
package com.levi9.app.webAuction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.app.webAuction.model.AuctionBid;
import com.levi9.app.webAuction.repository.AuctionBidRepository;
import com.levi9.app.webAuction.service.AuctionBidService;

/**
 * @author Ceki
 *
 */
@Service
public class AuctionBidServiceImpl implements AuctionBidService{

	
	@Autowired
	private AuctionBidRepository auctionBidRepository;
	
	/** 
	 * @see com.levi9.app.webAuction.service.CrudService#findOne(java.lang.Long)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AuctionBid findOne(Long id) {
		return auctionBidRepository.findOne(id);
	}

	/** (non-Javadoc)
	 * @see com.levi9.app.webAuction.service.CrudService#findAll()
	 */
	@Override
	public List<AuctionBid> findAll() {
		return auctionBidRepository.findAll();
	}

	/**
	 * @see com.levi9.app.webAuction.service.CrudService#save(com.levi9.app.webAuction.model.AbstractBaseEntity)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public AuctionBid save(AuctionBid auctionBid) {
		return auctionBidRepository.save(auctionBid);
	}

	/**
	 * @see com.levi9.app.webAuction.service.CrudService#remove(java.lang.Long)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void remove(Long id) throws IllegalArgumentException {
		AuctionBid auctionBid = auctionBidRepository.findOne(id);
        if(auctionBid == null) {
            throw new IllegalArgumentException("auction bid with id " + id + " does not exist.");
        }
        auctionBidRepository.delete(id);
		
	}


	

}
