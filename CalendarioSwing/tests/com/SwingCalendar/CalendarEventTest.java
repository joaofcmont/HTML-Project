package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEventTest {

	CalendarEvent calendarEvent;

	@BeforeEach
	void setUp() throws Exception {
		calendarEvent = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.now().plusMinutes(90),
				"EPPDS",
				"mpclq");
	}

	@Test
	void testGetUser() {
		assertEquals("mpclq",calendarEvent.getUser());
	}


	@Test
	void testGetDate() {
		assertEquals(LocalDate.of(2022, 12, 8), calendarEvent.getDate());
	}

//dava erro se pusesse LocalTime.now 
//expected 12:41:45.783899  - Actual 12:41:45.780898500 
	@Test
	void testGetStart() {
		assertEquals(LocalTime.of(12, 0),calendarEvent.getStart());
	}


	@Test
	void testGetEnd() {
		assertEquals(LocalTime.now().plusMinutes(90),calendarEvent.getEnd() );
	}



	@Test
	void testGetText() {
		assertEquals("EPPDS", calendarEvent.getText());
	}


}
