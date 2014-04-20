package com.starpath.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * This class is deprecated..doesn't need it any more in this branch
 * @author krishna
 *
 */
@Embeddable
public class TempleUserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable=true, length=50)
    protected String gothram;
    
	public String getGothram() {
		return gothram;
	}
	public void setGothram(String gothram) {
		this.gothram = gothram;
	}
	public String getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	@Column(nullable=true,length=150)
    protected String miscellaneous;
	
	@Column(nullable=true,length=50)
    protected String nakshathram;
   
	public String getNakshathram() {
		return nakshathram;
	}
	public void setNakshathram(String nakshathram) {
		this.nakshathram = nakshathram;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gothram == null) ? 0 : gothram.hashCode());
		result = prime * result
				+ ((miscellaneous == null) ? 0 : miscellaneous.hashCode());
		result = prime * result
				+ ((nakshathram == null) ? 0 : nakshathram.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TempleUserDetails other = (TempleUserDetails) obj;
		if (gothram == null) {
			if (other.gothram != null)
				return false;
		} else if (!gothram.equals(other.gothram))
			return false;
		if (miscellaneous == null) {
			if (other.miscellaneous != null)
				return false;
		} else if (!miscellaneous.equals(other.miscellaneous))
			return false;
		if (nakshathram == null) {
			if (other.nakshathram != null)
				return false;
		} else if (!nakshathram.equals(other.nakshathram))
			return false;
		return true;
	}
}
