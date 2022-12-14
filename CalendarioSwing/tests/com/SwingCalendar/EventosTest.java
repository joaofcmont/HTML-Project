package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventosTest {

	Eventos eventos;
	ArrayList<Event> listaEventos;
	@BeforeEach
	void setUp() throws Exception {
		eventos = new Eventos();
	}

	@Test
	void testGetListaEventos() {
		assertEquals(listaEventos, eventos.getListaEventos());
	}

	@Test
	void testSetListaEventos() {
		eventos.setListaEventos(listaEventos);
		assertEquals(eventos.getListaEventos(),listaEventos);
	}

}
