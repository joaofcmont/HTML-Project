package com.SwingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Week Calendar class
 * @version 08/12/2022
 *
 */
public class WeekCalendar extends Calendar {

	/**
	 * week
	 */
    private Week week;
    
    /**
     * Week calendar constructer
     * @param events is a list of calendar events
     */
    public WeekCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        week = new Week(LocalDate.now());
    }

    @Override
 
    protected boolean dateInRange(LocalDate date) {
        return Week.getStartOfWeek(date).equals(week.getDay(DayOfWeek.MONDAY));
    }

    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return week.getDay(day);
    }

    protected int numDaysToShow() {
        return 7;
    }
    public Week getWeek() {
    	return week;
    }

    @Override
    protected DayOfWeek getStartDay() {
        return DayOfWeek.MONDAY;
    }

    @Override
    protected DayOfWeek getEndDay() {
        return DayOfWeek.SUNDAY;
    }

    @Override
    protected void setRangeToToday() {
        week = new Week(LocalDate.now());
    }

    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH + getDayWidth() * (dayOfWeek.getValue() - 1);
    }
    /**
     * gets next week
     */
    public void nextWeek() {
        week = week.nextWeek();
        repaint();
    }
    /**
     * gets previous week
     */
    public void prevWeek() {
        week = week.prevWeek();
        repaint();
    }
    /**
     * gets the next month
     */
    public void nextMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).plusWeeks(4));
        repaint();
    }
    /**
     * gets the previous month
     */
    public void prevMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).minusWeeks(4));
        repaint();
    }

}
