/**
 * 
 */
package com.levi9.app.webAuction.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

/**
 * Auction model class
 * @author Ceki
 *
 */
@Entity
@Table(name="auction")
public class Auction extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5696460829069974258L;

	/**
	 * name of the auction
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String name;
	
	/**
	 * description of the auction
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String description;
	
	/**
	 * starting price
	 */
	@Column(nullable = false)
//	@NumberFormat(style = Style.CURRENCY)
	@Min(1)
	private double startPrice;
	
	/**
	 * buy out price
	 */
	@Column
	@Min(1)
	private double buyOutPrice;
	
	/**
	 * start time
	 */
	@Column(nullable = false)
	private Date startTime;
	
	/**
	 * end time
	 */
	@Column(nullable = false)
	private Date endTime;
	
	/**
	 * is auction active
	 */
	@Column
	private boolean active;
	
	/**
	 * all bids for auction
	 */
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "auction_fk", referencedColumnName = "id", nullable = false)
	@Fetch (FetchMode.SELECT)
	@OrderBy(clause = "bidValue desc"  ) 
	private List<AuctionBid> auctionBids;
	
	
	/**
	 * all images for auction
	 */
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "auction_fk", referencedColumnName = "id", nullable = false)
	@Fetch (FetchMode.SELECT)
	private List<Image> images ;
	
	/**
	 * auction owner 
	 */
	@ManyToOne
	@JoinColumn(name="user_fk", referencedColumnName = "id", nullable = false)
	 private User user;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startPrice
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * @return the buyOutPrice
	 */
	public double getBuyOutPrice() {
		return buyOutPrice;
	}

	/**
	 * @param buyOutPrice the buyOutPrice to set
	 */
	public void setBuyOutPrice(double buyOutPrice) {
		this.buyOutPrice = buyOutPrice;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}
