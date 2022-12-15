package com.SwingCalendar;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;


import java.time.LocalTime;

import java.util.ArrayList;

import java.util.Date;
import java.util.GregorianCalendar;

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
	
	Date startDate= new GregorianCalendar(2022,8,12).getTime();
	Date endDate=  new GregorianCalendar(2022,8,12).getTime();


	@Test
	void testAddCalendarEventClickListener() {
		assertThrows(NullPointerException.class , () ->c.addCalendarEventClickListener(null));

	}

	@Test
	void testRemoveCalendarEventClickListener() {
		assertThrows(NullPointerException.class , () ->c.removeCalendarEventClickListener(null));

	}

	@Test
	void testAddCalendarEmptyClickListener() {
		assertThrows(NullPointerException.class , () ->c.addCalendarEmptyClickListener(null));

	}

	@Test
	void testRemoveCalendarEmptyClickListener() {
		assertThrows(NullPointerException.class , () ->c.removeCalendarEmptyClickListener(null));

	}

//	@Test
//	void testDateInRange() {
//		LocalDate date = new LocalDate(2022, 12, 14);
////		System.out.println(startDate);
//			assertTrue(date.after(startDate) && testDate.before(endDate));
//		}
//	@Test
//	void testGetDateFromDay() {
//		fail("Not yet implemented");
//	}
//
//
//	@Test
//	void testCalculateScaleVars() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testNumDaysToShow() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDayToPixel() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testPaintComponentGraphics() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetStartDay() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetEndDay() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDrawStringMultiLine() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDayWidth() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetRangeToToday() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGoToToday() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddEvent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemoveEvent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetEvents() {
//		fail("Not yet implemented");
//	}


}
