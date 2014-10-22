/**
 * 
 */
package com.levi9.app.webAuction.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ceki
 *
 */
@Entity
@Table(name="auctionBid")
public class AuctionBid extends AbstractBaseEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5951887512473569465L;
	
	/**
	 * bid value
	 */
	@Column(nullable = false)
	private double bidValue;
	
	/**
	 * date of bid
	 */
	@Column(nullable = false)
	private Date bidDate;

	/**
	 * is bid current
	 */
	@Column(nullable = false)
	private boolean isCurrent;
	
	
	
	@ManyToOne
	@JoinColumn(name="user_fk", referencedColumnName = "id", nullable = false)
	 private User user;
	


	/**
	 * @return the bidDate
	 */
	public Date getBidDate() {
		return bidDate;
	}

	/**
	 * @return the bidValue
	 */
	public double getBidValue() {
		return bidValue;
	}

	/**
	 * @param bidValue the bidValue to set
	 */
	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
	
	
	
}
