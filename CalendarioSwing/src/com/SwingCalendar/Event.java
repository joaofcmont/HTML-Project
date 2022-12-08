package com.SwingCalendar;

/**
 * Event Class
 * @version 08/12/2022
 *
 */
public class Event {
	/**
	 * chair of event
	 */
	private String chair;
	/**
	 * date start event
	 */
	private String dateStart;
	/**
	 * date end event
	 */
	private String dateEnd;
	/**
	 * event username
	 */
	private String username;
	
	/**
	 * 
	 * @return the username for the event
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param username is a username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Event constructer
	 * @param chair is the chair of the event
	 * @param dateStart is the event date start
	 * @param dateEnd is the event date end
	 * @param username is the username event
	 */
	public Event(String chair, String dateStart, String dateEnd, String username) {
		this.chair = chair;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.username=username;
	}
	/**
	 * 
	 * @return the chair
	 */
	public String getChair() {
		return chair;
	}
	/**
	 * Sets the chair of a event
	 * @param chair is the event chair
	 */
	public void setChair(String chair) {
		this.chair = chair;
	}
	/**
	 * 
	 * @return the event date start
	 */
	public String getDateStart() {
		return dateStart;
	}
	/**
	 * Sets the event date start
	 * @param dateStart is the event date start
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	/**
	 * 
	 * @return the event date end
	 */
	public String getDateEnd() {
		return dateEnd;
	}
	/**
	 * Sets the event date end
	 * @param dateEnd is the event end date
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	
	}