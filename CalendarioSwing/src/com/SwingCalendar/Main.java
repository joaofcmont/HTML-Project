package com.SwingCalendar;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame frm = new JFrame();
        

        ArrayList<CalendarEvent> events = new ArrayList<>();
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 14), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 15), LocalTime.of(12, 0), LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 16), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 17), LocalTime.of(12, 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 18), LocalTime.of(9, 30), LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
        events.add(new CalendarEvent(LocalDate.of(2022, 11, 18), LocalTime.of(16, 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));

        WeekCalendar cal = new WeekCalendar(events);

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
      
            events.add(new CalendarEvent(LocalDate.of(ano, mes, dia), LocalTime.of(horaInicio, minutoInicio), LocalTime.of(horaFim, minutoFim), descricaoEvento));
          });
        
        JButton removeEvent = new JButton("Remove");
        removeEvent.addActionListener(e -> {
        	JFrame frame = new JFrame();
        	Object[] lista = events.toArray();
        	 CalendarEvent n = (CalendarEvent)JOptionPane.showInputDialog(frame, "Que evento deseja eliminar?", 
                     "Input Dialog", JOptionPane.QUESTION_MESSAGE, null, lista, lista[0]);
             cal.removeEvent(n);
        });
        

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
