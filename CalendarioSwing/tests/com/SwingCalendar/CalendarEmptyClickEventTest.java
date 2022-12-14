package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEmptyClickEventTest {
	
CalendarEmptyClickEvent c;
	
	@BeforeEach
	void setUp() throws Exception {
		 c = new CalendarEmptyClickEvent(Object.class,(LocalDateTime.of(LocalDate.of(2022, 12, 10),LocalTime.of(12, 00))));
	}
	
	  @Test
	    void testCalendarEmptyClickEvent()
	    {
	        assertThrows(IllegalArgumentException.class, () -> new CalendarEmptyClickEvent(null, null));
	    }

	
	@Test
	void testGetDateTime() {
		assertEquals(LocalDateTime.of(LocalDate.of(2022, 12, 10),LocalTime.of(12, 00)), c.getDateTime());
	}

}
