package com.SwingCalendar;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * Adds a click listener into calendar
 * @version 08/12/2022
 */
public class CalendarEmptyClickEvent extends AWTEvent {

	/**
	 * local date time 
	 */
    private LocalDateTime dateTime;
    
    /**
     * adds a click listener into calendar
     * @param source is the object to add
     * @param dateTime is the local date time
     */
    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) {
        super(source, 0);
        this.dateTime = dateTime;
    }
    /**
     *
     * @return the local date time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
