package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.awt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEventTest {

	CalendarEvent calendarEvent;
	CalendarEvent calendarEvent1;
	CalendarEvent calendarEventColor;
	CalendarEvent calendarEvent2;
	CalendarEvent calendarEvent3;
	CalendarEvent calendarEvent4;
	
	
	User user;
	@BeforeEach
	void setUp() throws Exception {
		calendarEvent = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.of(12, 30),
				"EPPDS",
				"mpclq");
		
		
		calendarEventColor = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.of(12, 30),
				"EPPDS",
				Color.GREEN,
				"mpclq");
		
	}
	


	
	@Test
	void testGetUser() {
		assertEquals("mpclq",calendarEvent.getUser());
	}
	
	@Test
	void testSetUser() {
		calendarEvent.setUser("mpclq");
		assertEquals(calendarEvent.getUser(),"mpclq");
	}

	@Test
	void testGetDate() {
		assertEquals(LocalDate.of(2022, 12, 8), calendarEvent.getDate());
	}
	
	@Test
	void testSetDate() {
		calendarEvent.setDate(LocalDate.of(2022, 12, 8));
		assertEquals(calendarEvent.getDate(),LocalDate.of(2022, 12, 8));
	}
	
	@Test
	void testGetStart() {
		assertEquals(LocalTime.of(12, 0),calendarEvent.getStart());
	}

	@Test
	void testSetStart() {
		calendarEvent.setStart(LocalTime.of(12, 0));
		assertEquals(calendarEvent.getStart(),LocalTime.of(12, 0));
	}

	@Test
	void testGetEnd() {
		assertEquals(LocalTime.of(12, 30),calendarEvent.getEnd() );
	}

	@Test
	void testSetEnd() {
		calendarEvent.setEnd(LocalTime.of(12, 30));
		assertEquals(calendarEvent.getEnd(),LocalTime.of(12, 30));
	}

	@Test
	void testGetText() {
		assertEquals("EPPDS", calendarEvent.getText());
	}

	@Test
	void testSetText() {
		calendarEvent.setText("EPPDS");
		assertEquals(calendarEvent.getText(),"EPPDS");
	}
	
	@Test
	void testGetColor() {
		assertEquals(Color.GREEN, calendarEventColor.getColor());
	}

	@Test
	void testSetColor() {
		calendarEvent.setColor(Color.GREEN);
		assertEquals(calendarEventColor.getColor(),Color.GREEN );
	}
	
	@Test
	void testEquals() {
		
		assertNotEquals(null,calendarEvent,"Não deve ser igual a null");
		assertNotEquals("int", calendarEvent, "Um calendarEvent não pode ser de um tipo diferente");
		assertEquals(calendarEvent,calendarEvent,"Devem ser iguais");
		
		calendarEvent2 = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 15),
				LocalTime.of(12, 30),
				"EPPDS",
				"mpclq");
		assertNotEquals(calendarEvent2,calendarEvent,"Não são iguais, não tem hora de inicio igual");
		
		calendarEvent3 = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 00),
				LocalTime.of(12, 35),
				"EPPDS",
				"mpclq");
		assertNotEquals(calendarEvent3,calendarEvent,"Não são iguais, não tem hora de fim igual");
		
		calendarEvent4 = new CalendarEvent(LocalDate.of(2022, 12, 14),
				LocalTime.of(12, 0),
				LocalTime.of(12, 30),
				"EPPDS",
				"mpclq");
		assertNotEquals(calendarEvent4,calendarEvent,"Não são iguais, não têm a mesma data");

	}
	
	@Test
	void TestHashCode() {
		
		calendarEvent1 = new CalendarEvent(LocalDate.of(2022, 12, 8),
				LocalTime.of(12, 0),
				LocalTime.of(12, 30),
				"EPPDS",
				"mpclq");
		
		assertTrue(calendarEvent.hashCode() == calendarEvent1.hashCode());
	}
	
	@Test
	void TestToString() {
		CalendarEvent ce = new CalendarEvent(null, null, null, null, null);
		String expected = "null null-null. null";
		assertEquals(expected,ce.toString());
	}
}
