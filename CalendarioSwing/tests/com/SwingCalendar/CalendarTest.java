package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTest {

	Calendar c;
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testCalendar() {
		fail("Not yet implemented");
	}

	@Test
	void testCalendarArrayListOfCalendarEvent() {
		fail("Not yet implemented");
	}

	@Test
	void testRoundTime() {
		
		LocalTime t = LocalTime.of(12, 25);
		int m = 30;
		assertEquals(LocalTime.of(12, 00),c.roundTime(t, m));
		
	}
	
	Date startDate= new GregorianCalendar(2022,8,12).getTime();
	Date endDate=  new GregorianCalendar(2022,8,12).getTime();

	@Test
	void testDateInRange(Date testDate ) {
		System.out.println(startDate);
			assertTrue(testDate.after(startDate) && testDate.before(endDate));
		}
	@Test
	void testGetDateFromDay() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCalendarEventClickListener() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveCalendarEventClickListener() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCalendarEmptyClickListener() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveCalendarEmptyClickListener() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateScaleVars() {
		fail("Not yet implemented");
	}

	@Test
	void testNumDaysToShow() {
		fail("Not yet implemented");
	}

	@Test
	void testDayToPixel() {
		fail("Not yet implemented");
	}

	@Test
	void testPaintComponentGraphics() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStartDay() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEndDay() {
		fail("Not yet implemented");
	}

	@Test
	void testDrawStringMultiLine() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDayWidth() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRangeToToday() {
		fail("Not yet implemented");
	}

	@Test
	void testGoToToday() {
		fail("Not yet implemented");
	}

	@Test
	void testAddEvent() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveEvent() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEvents() {
		fail("Not yet implemented");
	}

}
