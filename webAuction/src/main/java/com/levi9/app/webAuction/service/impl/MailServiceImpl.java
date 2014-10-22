/**
 * 
 */
package com.levi9.app.webAuction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.model.User;
import com.levi9.app.webAuction.service.MailService;

/**
 * @author Ceki
 *
 */
@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailSender mailSender;
	 
	@Override
	public void sendRegistrationMail(User user) {
		 
		SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(user.getEmail());
        message.setSubject("Please confirm your registration");
        message.setText("Dear "+user.getName().trim()+", you registered an account at Web Auction "
        		+ "with username: [" + user.getUsername() + "] and password: [" + user.getPassword()+"]. "
        		+ "Please click here to confirm registration http://localhost:8080/webAuction/users/"+user.getId());
        mailSender.send(message);
	}

	@Override
	public void sendBuyOutSuccesMail(User user, Auction auction){
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Buying successful");
		
        message.setTo(user.getEmail());
        message.setText("Dear "+user.getName().trim()+", you have successfully bought the item "
        +auction.getName()+" with Buy Out option. Price: "+auction.getBuyOutPrice());
        mailSender.send(message);
		
	}

	@Override
	public void sendBiggestBidSuccessMail(Auction auction) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Bidding successful");
		User user = auction.getAuctionBids().get(0).getUser();
		message.setTo(user.getEmail());
        message.setText("Dear "+user.getName().trim()+" congratulations.The auction for item: "+auction.getName()+" has timed out, "
        		+ "and your bid ("+auction.getAuctionBids().get(0).getBidValue()+") was the highest.");
        mailSender.send(message);
	}
	

}
