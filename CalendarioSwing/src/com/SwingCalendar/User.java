package com.SwingCalendar;

/**
 * User class constructer
 * @version 08/12/2022
 *
 */
public class User {
	/**
	 * link of user
	 */
	private String link;
	/**
	 * User class constructer
	 * @param link to set the link connect a user to the link
	 */
	public User(String link) {
		this.link=link;
	}
	/**
	 * 
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * 
	 * @param link to set the link of a user
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * 
	 * @param link to get the link of the user
	 * @return the name of the user
	 */
	public String setUser(String link) {
		
		String[] username= link.split("username=",2);
		String[] username1= username[1].split("@",2);
		User user= new User(username1[0]);
		return user.getLink();
	}
}
