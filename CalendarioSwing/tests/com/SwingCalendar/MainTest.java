package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
Main main;
Event ev;
	@BeforeEach
	void setUp() throws Exception {
		ev = new Event("Desenvolvimento para A Internet e Aplicações Móveis - Developmentfor Internet and Mobile Apps","20230301193000","20230301210000","mpclq");
		main = new Main();
	}

	@Test
	void testMain() {
		assertThrows(NullPointerException.class, () -> Main.startHour(null));
		assertThrows(NullPointerException.class, () -> Main.endHour(null));
		

	}
	
	@Test
	void testStartHour() {
		assertEquals(19,main.startHour(ev));
	}

	@Test
	void testEndHour() {
		assertEquals(21,main.endHour(ev));
	}
}
