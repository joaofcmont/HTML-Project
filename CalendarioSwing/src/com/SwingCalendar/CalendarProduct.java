package com.SwingCalendar;


import javax.swing.event.EventListenerList;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Calendar Product Class
 * @version 10/12/2022
 *
 */
public class CalendarProduct implements Serializable {
	private EventListenerList listenerList = new EventListenerList();

	/**
	* Adds a click listener into calendar
	* @param l  is the click listener
	 * @throws IllegalAccessException 
	*/
	public void addCalendarEventClickListener(CalendarEventClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Event Click Listener nao pode ser null");
		}
		listenerList.add(CalendarEventClickListener.class, l);
	}

	/**
	* Removes the click listener from calendar
	* @param l  is the click listener
	 * @throws IllegalAccessException 
	*/
	public void removeCalendarEventClickListener(CalendarEventClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Event Click Listener nao pode ser null");
		}
		listenerList.remove(CalendarEventClickListener.class, l);
	}

	/**
	* CalendarEmptyClick method to add a calendar empty click listener
	* @param l  is a calendar empty click listener
	 * @throws IllegalAccessException 
	*/
	public void addCalendarEmptyClickListener(CalendarEmptyClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Empty Click Listener nao pode ser null");
		}
		listenerList.add(CalendarEmptyClickListener.class, l);
	}

	/**
	* CalendarEmptyClick method to remove a calendar empty click listener 
	* @param l  is a calendar empty click listener
	 * @throws IllegalAccessException 
	*/
	public void removeCalendarEmptyClickListener(CalendarEmptyClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Empty Click Listener nao pode ser null");
		}
		listenerList.remove(CalendarEmptyClickListener.class, l);
	}

	/**
	* Notify all listeners that have registered interest for notification on this event type.
	* @param calendarEvent  is a calendar event
	*/
	public void fireCalendarEventClick(CalendarEvent calendarEvent, Calendar calendar) {
		if(calendarEvent == null || calendar == null) {
			throw new IllegalArgumentException("Não pode ser null");
		}
		Object[] listeners = listenerList.getListenerList();
		CalendarEventClickEvent calendarEventClickEvent;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CalendarEventClickListener.class) {
				calendarEventClickEvent = new CalendarEventClickEvent(calendar, calendarEvent);
				((CalendarEventClickListener) listeners[i + 1]).calendarEventClick(calendarEventClickEvent);
			}
		}
	}

	/**
	* Fire calendar Empty Click
	* @param dateTime  is a local date time
	*/
	public void fireCalendarEmptyClick(LocalDateTime dateTime, Calendar calendar) {
		Object[] listeners = listenerList.getListenerList();
		CalendarEmptyClickEvent calendarEmptyClickEvent = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			calendarEmptyClickEvent = calendarEmptyClickEvent(dateTime, listeners, calendarEmptyClickEvent, i,
					calendar);
			if (listeners[i] == CalendarEmptyClickListener.class) {
				((CalendarEmptyClickListener) listeners[i + 1]).calendarEmptyClick(calendarEmptyClickEvent);
			}
		}
	}

	public CalendarEmptyClickEvent calendarEmptyClickEvent(LocalDateTime dateTime, Object[] listeners,
			CalendarEmptyClickEvent calendarEmptyClickEvent, int i, Calendar calendar) {
		if (listeners[i] == CalendarEmptyClickListener.class) {
			calendarEmptyClickEvent = new CalendarEmptyClickEvent(calendar, dateTime);
		}
		return calendarEmptyClickEvent;
	}
}