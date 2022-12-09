package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mpclq@iscte.pt&password=xmL473npYB7oemMN431WH9nEJRIctc1xGX20iWj2peknw6XJ6XL3yJuTfbSwpO9gE64qIekOJtyhfGeFFWBwElxA6zxz14SHNsKbx4LZIX56YjhMvYa1hQQRFDoA53W8"
);
	}


	@Test
	void testUser() {
		
	}

	@Test
	void testSetUser() {
		assertEquals("mpclq",user.setUser("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mpclq@iscte.pt&password=xmL473npYB7oemMN431WH9nEJRIctc1xGX20iWj2peknw6XJ6XL3yJuTfbSwpO9gE64qIekOJtyhfGeFFWBwElxA6zxz14SHNsKbx4LZIX56YjhMvYa1hQQRFDoA53W8"));
	}

}
