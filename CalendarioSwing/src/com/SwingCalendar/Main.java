package com.SwingCalendar;
import javax.swing.*;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class Main {
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		JFrame frm = new JFrame();
		Gson gson = new Gson();

		Eventos evento = null;

		try (Reader reader = new FileReader("filename.json")) {

			// Convert JSON File to Java Object
			evento = gson.fromJson(reader, Eventos.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<CalendarEvent> calEvents = new ArrayList<CalendarEvent>();

		for (Event ev : evento.getListaEventos()) {
			String name = ev.getChair();

			int year = Integer.parseInt(ev.getDateStart().substring(0, 4));
			int month = Integer.parseInt(ev.getDateStart().substring(4, 6));
			int day = Integer.parseInt(ev.getDateStart().substring(6, 8));

			int startHour = Integer.parseInt(ev.getDateStart().substring(8, 10));
			int startMin = Integer.parseInt(ev.getDateStart().substring(10, 12));

			int endHour = Integer.parseInt(ev.getDateEnd().substring(8, 10));
			int endMin = Integer.parseInt(ev.getDateEnd().substring(10, 12));

			if (TimeZone.getDefault().inDaylightTime(new Date(year, month - 1, day))) {
				startHour += 1;
				endHour += 1;
			}

			calEvents.add(new CalendarEvent(LocalDate.of(year, month, day), 
					LocalTime.of(startHour, startMin), 
					LocalTime.of(endHour, endMin), name));
		}

		WeekCalendar cal = new WeekCalendar(calEvents);

		JButton goToTodayBtn = new JButton("Today");
		goToTodayBtn.addActionListener(e -> cal.goToToday());

		JButton nextWeekBtn = new JButton(">");
		nextWeekBtn.addActionListener(e -> cal.nextWeek());

		JButton prevWeekBtn = new JButton("<");
		prevWeekBtn.addActionListener(e -> cal.prevWeek());

		JButton nextMonthBtn = new JButton(">>");
		nextMonthBtn.addActionListener(e -> cal.nextMonth());

		JButton prevMonthBtn = new JButton("<<");
		prevMonthBtn.addActionListener(e -> cal.prevMonth());

		JButton addEvent = new JButton("Add event");
		addEvent.addActionListener(e -> { 
			JFrame frame = new JFrame();
			String data = JOptionPane.showInputDialog(frame, "Data do evento: (dd/mm/aa)");
			
			String[] dataEvento = data.split("/");
			int dia = Integer.parseInt(dataEvento[0]);
			int mes = Integer.parseInt(dataEvento[1]);
			int ano = Integer.parseInt(dataEvento[2]);

			String horasInicio = JOptionPane.showInputDialog(frame, "Hora de início do evento: (hh:mm)");
			String[] horaInicioEvento = horasInicio.split(":");
			int horaInicio = Integer.parseInt(horaInicioEvento[0]);
			int minutoInicio = Integer.parseInt(horaInicioEvento[1]);

			String horasFim = JOptionPane.showInputDialog(frame, "Hora de término do evento: (hh:mm)");
			String[] horaFimEvento = horasFim.split(":");
			int horaFim = Integer.parseInt(horaFimEvento[0]);
			int minutoFim = Integer.parseInt(horaFimEvento[1]);
			String descricao = JOptionPane.showInputDialog(frame, "Descrição do evento:");
			String descricaoEvento = descricao;
			
			calEvents.add(new CalendarEvent(LocalDate.of(ano, mes, dia), LocalTime.of(horaInicio, minutoInicio), LocalTime.of(horaFim, minutoFim), descricaoEvento));
		});

		JButton removeEvent = new JButton("Remove");
		removeEvent.addActionListener(e -> {
			JFrame frame = new JFrame();
			Object[] lista = calEvents.toArray();
			CalendarEvent n = (CalendarEvent)JOptionPane.showInputDialog(frame, "Que evento deseja eliminar?", 
					"Input Dialog", JOptionPane.QUESTION_MESSAGE, null, lista, lista[0]);
			//TODO
			calEvents.remove(n);
		});

		//TODO
		//JButton detalhes = new JButton("Detalhes");
		//detalhes.addActionListener(e -> { 

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);

		JPanel eventControls = new JPanel();
		eventControls.add(addEvent);
		eventControls.add(removeEvent);

		frm.add(weekControls, BorderLayout.NORTH);
		frm.add(eventControls, BorderLayout.SOUTH);
		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
