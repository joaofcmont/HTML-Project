package com.SwingCalendar;

import java.util.EventListener;

/**
 * Calendar Empty Click Listener
 * @version 08/12/2022
 */
public interface CalendarEmptyClickListener extends EventListener {
 
	/**
	 * Event dispatch methods
	 * @param e is a click empty calendar event
	 */
    void calendarEmptyClick(CalendarEmptyClickEvent e);
}
