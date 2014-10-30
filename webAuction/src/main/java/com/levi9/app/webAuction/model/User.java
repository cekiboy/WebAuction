/**
 * 
 */
package com.levi9.app.webAuction.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

/**
 * @author Ceki
 *
 */
@Entity
@Table(name="user")
public class User extends AbstractBaseEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5359767698245939975L;
	
	/**
	 * user's name
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String name;
	
	/**
	 * user's username
	 */
	@Column(nullable = false, length = 255, unique = true)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String username;
	
	/**
	 * user's password
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String password;
	
	/**
	 * user's email
	 */
	@Column(nullable = false, length = 255)
	@Email
	private String email;
	
	/**
	 * is user enabled by activating his account
	 */
	private boolean enabled;
	
	/**
	 * authority (role) of user
	 */
	private String authority;
	
	/**
	 * list of bids by user
	 */
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy="user")
	private List<AuctionBid> auctionBids;
	
	/**
	 * list of auctions by user
	 */
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy="user")
	private List<Auction> auctions;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the auctionBids
	 */
	public List<AuctionBid> getAuctionBids() {
		return auctionBids;
	}

	/**
	 * @param auctionBids the auctionBids to set
	 */
	public void setAuctionBids(List<AuctionBid> auctionBids) {
		this.auctionBids = auctionBids;
	}

	/**
	 * @return the auctions
	 */
	public List<Auction> getAuctions() {
		return auctions;
	}

	/**
	 * @param auctions the auctions to set
	 */
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
