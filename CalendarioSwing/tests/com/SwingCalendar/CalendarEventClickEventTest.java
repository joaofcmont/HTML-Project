package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEventClickEventTest {

	CalendarEventClickEvent c;
	CalendarEvent calendarEvent;
	
	@BeforeEach
	void setUp() throws Exception {
		calendarEvent = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.now().plusMinutes(90),
				"EPPDS",
				"mpclq");
		c = new CalendarEventClickEvent(Object.class,calendarEvent);
	}
	  @Test
	    void TestCalendarEventClickEvent()
	    {
	        assertThrows(IllegalArgumentException.class, () -> new CalendarEventClickEvent(null, null));
	    }


	@Test
	void testGetCalendarEvent() {
		assertEquals(calendarEvent,c.getCalendarEvent());
	}
	
	@Test
	void clearEventTest() {
		c.clearEvent();
		assertEquals(c.getCalendarEvent(), null);
	}

}
