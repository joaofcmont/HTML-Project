package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {

Week week ;
	
	@BeforeEach
	void setUp() throws Exception {
		week = new Week(LocalDate.of(2022, 11, 27));
	}

	@Test
	void testWeek() {
		
	}

	@Test
	void testGetStartOfWeek() {
		assertEquals(LocalDate.of(2022, 11, 21),Week.getStartOfWeek(LocalDate.of(2022, 11, 27)));
	}

	@Test
	void testGetDay() {
		assertEquals(LocalDate.of(2022, 11, 27),week.getDay(DayOfWeek.SUNDAY));
	}
	@Test
	void testNextWeek() {
		Week week1 = new Week(LocalDate.of(2022, 11, 28));
		assertEquals(week1,week.nextWeek());
	}

	@Test
	void testPrevWeek() {
		Week week1 = new Week(LocalDate.of(2022, 11, 20));
		assertEquals(week1,week.prevWeek());
	}

	@Test
	void testEquals() {
		Week week1 = new Week(LocalDate.of(2022, 11, 20));
		assertEquals(week,week);
		assertNotEquals(null,week);
		assertNotEquals(3,week);
		assertNotEquals(week1,week);
		week1 = new Week(LocalDate.of(2022, 11, 25));
		assertEquals(week1,week);
	}
}
