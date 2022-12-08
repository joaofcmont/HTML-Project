package com.SwingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Week class
 * @version 08/12/2022
 *
 */
public class Week {

	/**
	 * list of days
	 */
    private ArrayList<LocalDate> days;

    
    /**
     * Gets week variables from any date (can be within week)
     * @param date is the local date
     */
    public Week(LocalDate date) {
        days = new ArrayList<>();
        LocalDate monday = getStartOfWeek(date);
        days.add(monday);
        for (int i = 1; i < 7; i++) {
            days.add(monday.plusDays(i));
        }
    }
    /**
     * Gets the start of the week
     * @param date is the local date
     * @return the start of the week date
     */
    public static LocalDate getStartOfWeek(LocalDate date) {
        LocalDate day = date;
        while (day.getDayOfWeek() != DayOfWeek.MONDAY) {
            day = day.minusDays(1);
        }
        return day;
    }

    /**
     * Gets the day of the week 
     * @param dayOfWeek is the day of the week
     * @return the day of the week
     */
    public LocalDate getDay(DayOfWeek dayOfWeek) {
        // DayOfWeek enum starts with monday == 1
        return days.get(dayOfWeek.getValue() - 1);
    }
    /**
     * 
     * @return the next week 
     */
    public Week nextWeek() {
        final LocalDate friday = getDay(DayOfWeek.FRIDAY);
        return new Week(friday.plusDays(3));
    }
    /**
     * 
     * @return the previous week
     */
    public Week prevWeek() {
        final LocalDate monday = getDay(DayOfWeek.MONDAY);
        return new Week(monday.minusDays(3));
    }
   
    public String toString() {
        return "Week of the " + getDay(DayOfWeek.MONDAY);
    }
    
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Week that = (Week) o;
		return days.equals(that.days);
	}

}
