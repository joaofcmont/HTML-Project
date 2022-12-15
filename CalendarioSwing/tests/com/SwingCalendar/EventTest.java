package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

	Event event;
	
	@BeforeEach
	void setUp() throws Exception {
		event = new Event("Desenvolvimento para A Internet e Aplicações Móveis - Developmentfor Internet and Mobile Apps","20230301193000","20230301210000","mpclq");
		
	}


	@Test
	void testGetChair() {
		assertEquals("Desenvolvimento para A Internet e Aplicações Móveis - Developmentfor Internet and Mobile Apps",event.getChair());
	}


	@Test
	void testGetDateStart() {
		assertEquals("20230301193000",event.getDateStart());
	}

	@Test
	void testGetDateEnd() {
		assertEquals("20230301210000",event.getDateEnd());
	}

	@Test
	
	void testGetUsername() {
		assertEquals("mpclq",event.getUsername());
	}

	@Test
	void testSetDateEnd() {
		event.setDateEnd("20221012");
		assertEquals(event.getDateEnd(),"20221012");
	}
	@Test
	void testSetDateStart() {
		event.setDateStart("20221012");
		assertEquals(event.getDateStart(),"20221012");
	}
	@Test
	void testSetChair() {
		event.setChair("POO");
		assertEquals(event.getChair(),"POO");
	}
	@Test
	void testSetUsername() {
		event.setUsername("Diogo");
		assertEquals(event.getUsername(),"Diogo");
	}
	
	
}
