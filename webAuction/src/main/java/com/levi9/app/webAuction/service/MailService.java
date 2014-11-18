/**
 * 
 */
package com.levi9.app.webAuction.service;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.model.User;

/**
 * @author Ceki
 *
 */
public interface MailService {
	
	/**
	 * 
	 * @param user
	 */
	void sendRegistrationMail(User user);
	
	/**
	 * @param user
	 * @param auction
	 */
	void sendBuyOutSuccesMail(User user, Auction auction);
	
	/**
	 * @param user
	 * @param auction
	 */
	void sendBiggestBidSuccessMail(Auction auction);

}
