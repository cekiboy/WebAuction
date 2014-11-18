/**
 * 
 */
package com.levi9.app.webAuction.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ceki
 *
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2683019952209118420L;
	
	
	/**
	 * Primary key
	 */
	
	@Id
	@GeneratedValue
	private Long id;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
