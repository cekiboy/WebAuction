/**
 * 
 */
package com.levi9.app.webAuction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ceki
 *
 */

@Entity
@Table(name="image")
public class Image extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8944724286493247074L;

	/**
	 * name of the image file
	 */
	@Column(nullable = false)
	private String fileName;
	
	


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	
}
