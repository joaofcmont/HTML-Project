package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarProductTest {

	CalendarProduct c;
	CalendarEvent calendarEvent;
	@BeforeEach
	void setUp() throws Exception {
		c = new CalendarProduct();
		calendarEvent = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.now().plusMinutes(90),
				"EPPDS",
				"mpclq");
	}
	

	@Test
	void testAddCalendarEventClickListener() {
		assertThrows(IllegalArgumentException.class , () ->c.addCalendarEventClickListener(null));
	}

	@Test
	void testRemoveCalendarEventClickListener() {
		assertThrows(IllegalArgumentException.class , () ->c.removeCalendarEventClickListener(null));

	}

	@Test
	void testAddCalendarEmptyClickListener() {
		assertThrows(IllegalArgumentException.class , () ->c.addCalendarEmptyClickListener(null));

	}

	@Test
	void testRemoveCalendarEmptyClickListener() {
		assertThrows(IllegalArgumentException.class , () ->c.removeCalendarEmptyClickListener(null));

	}

	@Test
	void testFireCalendarEventClick() {
	//	assertThrows(IllegalArgumentException.class , () ->c.fireCalendarEventClick(null, ));
		assertThrows(IllegalArgumentException.class , () ->c.fireCalendarEventClick(calendarEvent,null));
	}

//	@Test
//	void testFireCalendarEmptyClick() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCalendarEmptyClickEvent() {
//		fail("Not yet implemented");
//	}

}
