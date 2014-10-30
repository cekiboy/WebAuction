/**
 * 
 */
package com.levi9.app.webAuction.service.impl;

import java.util.Date;
import java.util.List;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.model.AuctionBid;
import com.levi9.app.webAuction.repository.AuctionRepository;
import com.levi9.app.webAuction.service.AuctionService;

/**
 * @author Ceki
 *
 */
@Service
public class AuctionServiceImpl implements AuctionService{
	
	@Autowired
	private AuctionRepository auctionRepository;

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Auction findOne(Long id) {
		return auctionRepository.findOne(id);
	}

	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public Auction save(Auction auction) {
		return auctionRepository.save(auction);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void remove(Long id) throws IllegalArgumentException {
		Auction auction = auctionRepository.findOne(id);
        if(auction == null) {
            throw new IllegalArgumentException("Auction with id " + id + " does not exist.");
        }
		auctionRepository.delete(id);
		
	}

	@Override
	public List<Auction> findByActiveTrueAndUserId(Long userId) {
		return auctionRepository.findAuctionsByActiveTrueAndUserId(userId);
	}

	@Override
	public void addBid(Auction auction, AuctionBid auctionBid) {
		auction.getAuctionBids().add(auctionBid);
		auctionRepository.save(auction);
		
	}

	@Override
	public List<Auction> findByActiveTrueAndEndTimeBefore(Date date) {
		return auctionRepository.findAuctionsByActiveTrueAndEndTimeBefore(date);
	}

	@Override
	public List<Auction> findByActiveTrue() {
		return auctionRepository.findAuctionsByActiveTrue();
	}

	@Override
	public List<Auction> findByActiveTrueAndUserUsername(String username) {
		return auctionRepository.findAuctionsByActiveTrueAndUserUsername(username);
	}

	@Override
	public List<Auction> findByActiveTrueAndNameContaining(String name) {
		return auctionRepository.findAuctionsByActiveTrueAndNameContaining(name);
	}

	@Override
	@Transactional
	public List<Auction> deactivateExpiredAuctions() {
		List<Auction> expiredAuctions  = auctionRepository.findAuctionsByActiveTrueAndEndTimeBefore(new Date());
		System.out.println(new Date());
		for (Auction auction : expiredAuctions){
			auction.setActive(false);
			auctionRepository.save(auction);
		}
		return expiredAuctions;
	}


	/* 
	 * @see com.levi9.app.webAuction.service.AuctionService#findByBidderUsername(java.lang.String)
	 */
	@Override
	public List<Auction> findByBidderId(Long bidderId) {
		List<Auction> auctions = auctionRepository.findAuctionsByBidderUsername(bidderId);
		return auctions;
	}

	
}
