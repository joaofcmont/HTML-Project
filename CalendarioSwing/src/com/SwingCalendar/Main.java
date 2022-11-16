package com.SwingCalendar;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class Main {


	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {


		ToJson js= new ToJson();
		js.paraJson();
		Parser p= new Parser();

		ImportJsonService j= new ImportJsonService();

		ConnectToDB db= new ConnectToDB();

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
				String descricaoEvento = descricao;
				calEvents.add(new CalendarEvent(LocalDate.of(e.getDateTime().getYear(), e.getDateTime().getMonthValue(), e.getDateTime().getDayOfMonth()), LocalTime.of(e.getDateTime().getHour(), e.getDateTime().getMinute()), LocalTime.of(horaFim, minutoFim), descricaoEvento));
				cal.setEvents(calEvents);
				String jsonStr =descricaoEvento+"\n" +  "\n20230109080000\n" + "\n20230109110000\n";
				try {
					FileWriter myWriter = new FileWriter("agenda.txt", true);
					  myWriter.write(jsonStr);
				      myWriter.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
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

		//TODO - Faltam detalhes sobre o dono do calendário cujo evento foi selecionado
		JButton detalhes = new JButton("Details");

		cal.addCalendarEventClickListener(e -> {
			detalhes.addActionListener(e1 -> {
				CalendarEvent event = e.getCalendarEvent();
				JFrame frame = new JFrame();
				if(event==null) {
					return;
				}
				JOptionPane.showMessageDialog(frame, event);
				e.clearEvent();
			});
		});

		JButton pdf = new JButton("Convert to PDF");

		JButton addCalendar = new JButton("Add a Calendar Link");
		addCalendar.addActionListener(e -> { 
			JFrame frame = new JFrame();
			String link = JOptionPane.showInputDialog(frame, "Link do calendário:");
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
		eventControls.add(detalhes);
		eventControls.add(pdf);
		eventControls.add(addCalendar);

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


}