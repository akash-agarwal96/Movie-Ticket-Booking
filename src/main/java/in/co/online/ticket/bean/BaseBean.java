package in.co.online.ticket.bean;

import java.sql.Timestamp;

/**
 * BaseBean JavaBean encapsulates Generic attributes
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public abstract class BaseBean implements DropdownListBean, Comparable<BaseBean> {
	/**
	 * Generic Attribute Id For All Child
	 */
	protected long id;

	/**
	 * Generic Attribute Created BY For All Child
	 */
	protected String createdBy;
	/**
	 * Generic Attribute Modified BY For All Child
	 */
	protected String modifiedBy;
	/**
	 * Generic Attribute Created Date and TIme For All Child
	 */
	protected Timestamp createdDatetime;
	/**
	 * Generic Attribute Modified Date And TIme For All Child
	 */
	protected Timestamp modifiedDatetime;

	/**
	 * accessor
	 */

	/**
	 * @return ID of Child
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param Id
	 *            To set Child ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Created By Child
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param CreatedBy
	 *            To set Child Creatd By
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return Modified By Child
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param Modified
	 *            by To set Child ModifiedBY
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return Created Date And TIme of Child
	 */

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * @param Created
	 *            Date and Time To set Child Created dATE aND tiME
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * @return Modified Date And Time of Child
	 */

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * @param Modified
	 *            By To set Child ModiFied By
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public int compareTo(BaseBean next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}
}
