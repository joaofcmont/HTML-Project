package com.SwingCalendar;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Calendar Event Class
 * @version 08/12/2022
 *
 */
public class CalendarEvent {

	/**
	 * local date event
	 */
	private LocalDate date;
	/** 
	 * local date start event
	 */
	private LocalTime start;
	
	/*
	 * local date end event
	 */
	private LocalTime end;
	
	/**
	 * text event
	 */
	private String text;
	
	/**
	 * color event
	 */
	private Color color;
	/**
	 * user event
	 */
	private String user;

	/**
	 * Calendar Event constructer 
	 * @param date is the local date 
	 * @param start is the local time start
	 * @param end is the local time end
	 * @param text is the text into the calendar event
	 * @param color is the color of the calendar event
	 * @param user is the user of the calendar event
	 */
	public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, Color color, String user) {
		this.date = date;
		this.start = start;
		this.end = end;
		this.text = text;
		this.color = color;
		this.user = user;
	}
	
	/**
	 * Calendar Event constructer
	 * @param date is the local date 
	 * @param start is the local time start
	 * @param end is the local time end
	 * @param text is the text into the calendar event
	 * @param user is the user of the calendar event
	 */
	public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, String user) {
		this.date = date;
		this.start = start;
		this.end = end;
		this.text = text;
		this.user = user;
	}
	
	/**
	 * 
	 * @return the event user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user
	 * @param user is the set user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 
	 * @return the local data
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Sets the local date
	 * @param date is the local date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * 
	 * @return the start local time
	 */
	public LocalTime getStart() {
		return start;
	}
	/**
	 * 
	 * @param start is the start local date to set
	 */
	public void setStart(LocalTime start) {
		this.start = start;
	}
	/**
	 * 
	 * @return the end local time
	 */
	public LocalTime getEnd() {
		return end;
	}
	/**
	 * 
	 * @param end is the local end time to set
	 */
	public void setEnd(LocalTime end) {
		this.end = end;
	}
	/**
	 *  
	 * @return the text 
	 */
	public String getText() {
		return text;
	}
	/**
	 * 
	 * @param text is the text that is set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * to String constructer
	 */
	public String toString() {
		return getDate() + " " + getStart() + "-" + getEnd() + ". " + getText();
	}
	/**
	 * 
	 * @return the color of the calendar event
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * 
	 * @param cor is a Color
	 * @return the color that was set
	 */
	public Color setColor(Color cor) {
		return color=cor;
	}

	@Override
	/**
	 * checks if the calendar event is equal to the other calendar event
	 */
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CalendarEvent that = (CalendarEvent) o;

		if (!date.equals(that.date)) return false;
		if (!start.equals(that.start)) return false;
		return end.equals(that.end);

	}

	@Override
	/**
	 * Returns the hashCode of the calendar event
	 */
	public int hashCode() {
		int result = date.hashCode();
		result = 31 * result + start.hashCode();
		result = 31 * result + end.hashCode();
		return result;
	}

}
