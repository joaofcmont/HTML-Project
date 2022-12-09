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
	
}
