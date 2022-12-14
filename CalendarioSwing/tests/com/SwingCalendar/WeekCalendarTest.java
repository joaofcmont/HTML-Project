package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekCalendarTest {

	WeekCalendar weekCalendar; 
	ArrayList<CalendarEvent> lista;
	Week week ;
	
	@BeforeEach
	void setUp() throws Exception {
		
		lista = new ArrayList<>();
		
		lista.add(new CalendarEvent(
				LocalDate.now(), 
				LocalTime.now(),
				LocalTime.now().plusMinutes(90),
				"EPPDS","mpclq"));
		
		lista.add(new CalendarEvent(
				LocalDate.now().plusDays(8), 
				LocalTime.now(),
				LocalTime.now().plusMinutes(90),
				"MC","mpclq"));
		
		weekCalendar =  new WeekCalendar(lista);
	}

	@Test
	void testDateInRange() {
		assertTrue(weekCalendar.dateInRange(lista.get(0).getDate()));
		assertFalse(weekCalendar.dateInRange(lista.get(1).getDate()));
	}

	@Test
	void testGetDateFromDay() {
		assertEquals(LocalDate.now(), weekCalendar.getDateFromDay(LocalDate.now().getDayOfWeek()));
	}

	@Test
	void testNumDaysToShow() {
		assertEquals(7, weekCalendar.numDaysToShow());
	}

	@Test
	void testGetStartDay() {
		assertEquals(DayOfWeek.MONDAY , weekCalendar.getStartDay());
	}

	@Test
	void testGetEndDay() {
		assertEquals(DayOfWeek.SUNDAY , weekCalendar.getEndDay());
	}
	@Test
	void TestnextWeek() {
		week= new Week(LocalDate.now());
		assertEquals(weekCalendar.getWeek().nextWeek(),week.nextWeek());
	}

}
