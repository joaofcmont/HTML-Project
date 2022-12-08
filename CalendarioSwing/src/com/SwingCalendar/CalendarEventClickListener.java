package com.SwingCalendar;

import java.util.EventListener;

/**
 * Calendar event click listener class
 * @version 08/12/2022
 *
 */
public interface CalendarEventClickListener extends EventListener {
	/**
	 *  Event dispatch methods
	 * @param e is a calendar event click event
	 */
    void calendarEventClick(CalendarEventClickEvent e);
}
