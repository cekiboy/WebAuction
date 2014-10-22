/**
 * 
 */
package com.levi9.app.webAuction.web.dto;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.levi9.app.webAuction.model.Auction;

/**
 * @author Ceki
 *
 */
public class AuctionFilesDTO {

	private Auction auction;
	
	@NotEmpty
	private List<MultipartFile> files;

	
	/**
	 * @return the auction
	 */
	@Valid
	public Auction getAuction() {
		return auction;
	}

	/**
	 * @param auction the auction to set
	 */
	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	/**
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	
	
}
