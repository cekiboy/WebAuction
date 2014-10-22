/**
 * 
 */
package com.levi9.app.webAuction.web.dto;

import javax.validation.constraints.Pattern;


/**
 * @author Ceki
 *
 */
public class SearchDTO {
	
	private String searchType;
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String searchValue;



	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}


	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}


	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	

}
