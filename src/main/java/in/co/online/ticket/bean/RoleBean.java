package in.co.online.ticket.bean;

/**
 * Role JavaBean encapsulates Role attributes
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public class RoleBean extends BaseBean {
	/**
	 * Predefined Role constants
	 */
	public static final int ADMIN = 1;
	public static final int CUSTOMER = 2;
	

	/**
	 * Role Name
	 */

	private String name;

	/**
	 * Role Description
	 */
	private String description;

	/**
	 * accessor
	 */

	/**
	 * @return Name of Role
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param to
	 *            set Name Of Role
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Description of Role
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * @param to
	 *            set Description Of Role
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}
}
