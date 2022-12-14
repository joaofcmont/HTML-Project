package com.SwingCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ListIterator;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import com.mongodb.client.FindIterable;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * Main method
 * @version 08/12/2022
 */

public class Main {
	/**
	 * 
	 * @param args for the main method
	 * @throws JsonSyntaxException is raised for Gson to read (or write) a malformed JSON element.
	 * @throws JsonIOException is raised when Gson was unable to read an input stream or write to one.
	 * @throws IOException for accessing files 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException, IllegalAccessException {

		ConnectToDB db = new ConnectToDB();
		Parser p= new Parser();
		ToJson js= new ToJson();

		ArrayList<CalendarEvent> eventsDiogo = new ArrayList<CalendarEvent>();
		ArrayList<CalendarEvent> eventsMatheus = new ArrayList<CalendarEvent>();
		ArrayList<CalendarEvent> eventsJoao = new ArrayList<CalendarEvent>();

		boolean collectionExists = db.database.listCollectionNames().into(new ArrayList()).contains("Eventos");

		if(!collectionExists) {
			db.database.createCollection("Eventos");
		}

		FindIterable<org.bson.Document> findIterable = js.getUser().find();
		for (org.bson.Document document : findIterable) {
			js.getUser().deleteMany(document);
		}

		org.bson.Document d=org.bson.Document.parse(js.paraJson());
		js.getUser().insertOne(d);

		js.paraJson();

		JFrame frm = new JFrame();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Eventos evento = null;

		try (Reader reader = new FileReader("agenda.json")) {
			// Convert JSON File to Java Object
			evento = gson.fromJson(reader, Eventos.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<CalendarEvent> calEvents = new ArrayList<CalendarEvent>();

		for (Event ev : evento.getListaEventos()) {
			String[] name = ev.getChair().split("-");
			String pt = name[0];

			int endHour = endHour(ev);
			int startHour = startHour(ev);
			int year = Integer.parseInt(ev.getDateStart().substring(0, 4));
			int month = Integer.parseInt(ev.getDateStart().substring(4, 6));
			int day = Integer.parseInt(ev.getDateStart().substring(6, 8));

			int startMin = Integer.parseInt(ev.getDateStart().substring(10, 12));
			int endMin = Integer.parseInt(ev.getDateEnd().substring(10, 12));

			if(ev.getUsername().equals("dfsaa1")) {
				calEvents.add(new CalendarEvent(LocalDate.of(year, month, day), 
						LocalTime.of(startHour, startMin), 
						LocalTime.of(endHour, endMin),pt,Color.BLUE,"dfsaa1"));
			}else if(ev.getUsername().equals("jfcmo1")){
				calEvents.add(new CalendarEvent(LocalDate.of(year, month, day), 
						LocalTime.of(startHour, startMin), 
						LocalTime.of(endHour, endMin),pt,Color.GREEN,"jfcmo1"));
			}else if(ev.getUsername().equals("mpclq")) {
				calEvents.add(new CalendarEvent(LocalDate.of(year, month, day), 
						LocalTime.of(startHour, startMin), 
						LocalTime.of(endHour, endMin),pt,Color.ORANGE,"mpclq"));
			}
		}

		// create checkbox

		JCheckBox c1 = new JCheckBox("Calendário Diogo", true);
		JCheckBox c2 = new JCheckBox("Calendário João", true);
		JCheckBox c3 = new JCheckBox("Calendário Matheus", true);

		c1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					for(CalendarEvent event : eventsDiogo)
						calEvents.add(event);
				} else {

					for (ListIterator<CalendarEvent> it = calEvents.listIterator(); it.hasNext();){
						CalendarEvent value = it.next();

						if (value.getUser().equals("dfsaa1")) {
							eventsDiogo.add(value);
							it.remove();
						}
					}		
				}
				SwingUtilities.updateComponentTreeUI(frm);
			}
		});
		c2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					for(CalendarEvent event : eventsJoao)
						calEvents.add(event);
				} else {

					for (ListIterator<CalendarEvent> it = calEvents.listIterator(); it.hasNext();){
						CalendarEvent value = it.next();

						if (value.getUser().equals("jfcmo1")) {
							eventsJoao.add(value);
							it.remove();
						}
					}		
				}
				SwingUtilities.updateComponentTreeUI(frm);
			}
		});
		c3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					for(CalendarEvent event : eventsMatheus)
						calEvents.add(event);
				} else {

					for (ListIterator<CalendarEvent> it = calEvents.listIterator(); it.hasNext();){
						CalendarEvent value = it.next();

						if (value.getUser().equals("mpclq")) {
							eventsMatheus.add(value);
							it.remove();
						}
					}		
				}
				SwingUtilities.updateComponentTreeUI(frm);
			}
		});

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

		JButton addEvent = new JButton("Add Event");
		cal.addCalendarEmptyClickListener(e -> {
			addEvent.addActionListener(e1 -> { 
				JFrame frame = new JFrame();
				String horasFim = JOptionPane.showInputDialog(frame, "Hora de término do evento: (hh:mm)");
				if(horasFim==null)
					return;
				String[] horaFimEvento = horasFim.split(":");
				int horaFim = Integer.parseInt(horaFimEvento[0]);
				int minutoFim = Integer.parseInt(horaFimEvento[1]);
				String descricao = JOptionPane.showInputDialog(frame, "Descrição do evento:");
				String user = JOptionPane.showInputDialog(frame, "Dono do horário:");
				CalendarEvent event = new CalendarEvent(LocalDate.of(e.getDateTime().getYear(), e.getDateTime().getMonthValue(), e.getDateTime().getDayOfMonth()), LocalTime.of(e.getDateTime().getHour(), e.getDateTime().getMinute()), LocalTime.of(horaFim, minutoFim), descricao, user);
				if(user.equals("dfsaa1")) {
					event.setColor(Color.BLUE);
				}else if(user.equals("jfcmo1")){
					event.setColor(Color.GREEN);
				}else if(user.equals("mpclq")){
					event.setColor(Color.ORANGE);
				}else if(user.equals("jmcls2")){
					event.setColor(Color.RED);
				}
				calEvents.add(event);
				cal.setEvents(calEvents);	
			});

		});

		JButton removeEvent = new JButton("Remove");
		cal.addCalendarEventClickListener(e -> {
			CalendarEvent event = e.getCalendarEvent();
			removeEvent.addActionListener(e1 -> {
				calEvents.remove(event);
				cal.setEvents(calEvents);
			});
		});

		JButton detalhes = new JButton("Details");

		cal.addCalendarEventClickListener(e -> {
			detalhes.addActionListener(e1 -> {
				CalendarEvent event = e.getCalendarEvent();
				JFrame frame = new JFrame();
				if(event==null) {
					return;
				}
				JOptionPane.showMessageDialog(frame, event, event.getUser(), 0);
				e.clearEvent();
			});
		});

		JButton pdf = new JButton("Convert to PDF");

		JButton addCalendar = new JButton("Add a Calendar Link");
		addCalendar.addActionListener(e -> { 
			JFrame frame = new JFrame();
			String link = JOptionPane.showInputDialog(frame, "Link do calendário:");
			List<String> lista_links = new ArrayList<>();
			lista_links.add(link);
			// Writing into the file
			try {
				// Reading the content of the file
				String filename= "links.txt";
				FileWriter fw = new FileWriter(filename,true); //the true will append the new data
				fw.write("\n" + link);
				fw.close();
				frm.dispose();
				main(args);
			} catch (IOException | JsonSyntaxException | JsonIOException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		});

		JButton seeChart = new JButton("See Barchart");
		seeChart.addActionListener(e -> {
			JFrame frame = new JFrame("Relação mensal em percentagem de Slots Preenchidos/Slots Vazios");
			BarChart chart = new BarChart();
			int ano = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ano a representar:"));
			String mes = JOptionPane.showInputDialog(frame, "Mês a representar: (ING/CAPS)");
			int slotsPreenchidos =0 ;
			int totalSlots = 420;
			for(CalendarEvent event : calEvents) {
				if(event.getDate().getYear() == ano) {
					if(event.getDate().getMonth().toString().equals(mes)) {
						slotsPreenchidos++;
					}
				}
			}
			int mediaSlotsVazios = (((totalSlots - slotsPreenchidos)*100)/totalSlots);
			chart.addBar(Color.red, (slotsPreenchidos*100)/totalSlots);
			chart.addBar(Color.green, mediaSlotsVazios);   
			frame.getContentPane().add(chart);
			frame.pack();
			frame.setVisible(true);
		});

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);
		weekControls.add(c1);
		weekControls.add(c2);
		weekControls.add(c3);

		JPanel eventControls = new JPanel();
		eventControls.add(addEvent);
		eventControls.add(removeEvent);
		eventControls.add(detalhes);
		eventControls.add(pdf);
		eventControls.add(addCalendar);
		eventControls.add(seeChart);

		frm.add(weekControls, BorderLayout.NORTH);
		frm.add(eventControls, BorderLayout.SOUTH);
		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		pdf.addActionListener(e -> { 
			BufferedImage img = new BufferedImage(frm.getWidth(), frm.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = img.createGraphics();
			frm.printAll(g2d);
			g2d.dispose();
			try {
				ImageIO.write(img, "png", new File("imagem.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Document document = new Document(PageSize.A2.rotate(), 0, frm.getHeight(), 0, frm.getWidth());
				PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
				document.open();
				Image image = ImageIO.read(new File("imagem.png"));
				document.add(com.itextpdf.text.Image.getInstance("imagem.png"));
				document.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		});
	}

	 static int startHour(Event ev) throws NumberFormatException {
		int year = Integer.parseInt(ev.getDateStart().substring(0, 4));
		int month = Integer.parseInt(ev.getDateStart().substring(4, 6));
		int day = Integer.parseInt(ev.getDateStart().substring(6, 8));
		int startHour = Integer.parseInt(ev.getDateStart().substring(8, 10));
		if (TimeZone.getDefault().inDaylightTime(new Date(year, month - 1, day))) {
			startHour += 1;
		}
		return startHour;
	}

	static int endHour(Event ev) throws NumberFormatException {
		int year = Integer.parseInt(ev.getDateStart().substring(0, 4));
		int month = Integer.parseInt(ev.getDateStart().substring(4, 6));
		int day = Integer.parseInt(ev.getDateStart().substring(6, 8));
		int endHour = Integer.parseInt(ev.getDateEnd().substring(8, 10));
		if (TimeZone.getDefault().inDaylightTime(new Date(year, month - 1, day))) {
			endHour += 1;
		}
		return endHour;
	}
}