package com.SwingCalendar;

import java.awt.*;

/**
 * Calendar event click event class
 * @version 08/12/2022
 * 
 */
public class CalendarEventClickEvent extends AWTEvent {

	
	/**
	 * calendar event
	 */
    private CalendarEvent calendarEvent;

    /**
     * Calendar event click event constructer
     * @param source is the calendar event source
     * @param calendarEvent is a calendar event
     */
    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }
    /**
     * 
     * @return the calendar event
     */
    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
    /**
     * clears the event
     */
    public void clearEvent() {
    	this.calendarEvent=null;
    }
}
