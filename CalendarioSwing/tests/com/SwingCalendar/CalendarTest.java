package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTest { 
	
	ArrayList<CalendarEvent> lista;
	Calendar c;
	
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
	}

	@Test
 void testRoundTime() {
		
		LocalTime t = LocalTime.of(12, 25);
		int m = 25;
		assertEquals(LocalTime.of(12, 25),Calendar.roundTime(t, m));
		
	}

//	@Test
//	void testAddCalendarEventClickListener() {
//		assertThrows(IllegalArgumentException.class , () ->c.addCalendarEventClickListener(null));
//
//	}
//
//	@Test
//	void testRemoveCalendarEventClickListener() {
//		assertThrows(IllegalArgumentException.class , () ->c.removeCalendarEventClickListener(null));
//
//	}
//
//	@Test
//	void testAddCalendarEmptyClickListener() {
//		assertThrows(IllegalArgumentException.class , () ->c.addCalendarEmptyClickListener(null));
//
//	}
//
//	@Test
//	void testRemoveCalendarEmptyClickListener() {
//		assertThrows(IllegalArgumentException.class , () ->c.removeCalendarEmptyClickListener(null));
//
//	}

}
