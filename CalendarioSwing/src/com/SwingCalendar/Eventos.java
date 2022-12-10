package com.SwingCalendar;
import java.util.ArrayList;

/**
 * Eventos Class
 * @version 08/12/2022
 *
 */
public class Eventos {

	/**
	 * list of events
	 */
	private ArrayList<Event> listaEventos;

	/**
	 * 
	 * @return the list of events
	 */
	public ArrayList<Event> getListaEventos() {
		return listaEventos;
	}
	/**
	 * Sets the list of events
	 * @param listaEventos is the list of events
	 */
	public void setListaEventos(ArrayList<Event> listaEventos) {
		this.listaEventos = listaEventos;
	}		
}

