package com.SwingCalendar;

import javax.swing.*;
import javax.swing.event.EventListenerList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Calendar class
 * @version 08/12/2022
 */
public abstract class Calendar extends JComponent {
	private CalendarProduct calendarProduct = new CalendarProduct();
	/**
	 * start time
	 */
	protected static final LocalTime START_TIME = LocalTime.of(1, 0);
	/**
	 * end time
	 */
	protected static final LocalTime END_TIME = LocalTime.of(22, 0);

	/**
	 * min width
	 */
	protected static final int MIN_WIDTH = 600;
	/**
	 * min heigth
	 */
	protected static final int MIN_HEIGHT = MIN_WIDTH;
/**
 * header height
 */
	protected static final int HEADER_HEIGHT = 30;
	/**
	 * time col width
	 */
	protected static final int TIME_COL_WIDTH = 100;

	/**
	 *  An estimate of the width of a single character (not exact but good enough)
	 */
	private static final int FONT_LETTER_PIXEL_WIDTH = 7;
	/**
	 * list of calendar events
	 */
	private ArrayList<CalendarEvent> events;
	/**
	 * Time scale
	 */
	private double timeScale;
	/**
	 * Day Width
	 */
	private double dayWidth;
	/**
	 * graphics 2D 
	 */
	private Graphics2D g2;

	/**
	 * Calendar class
	 */
	public Calendar() {
		this(new ArrayList<>());
	}
	/**
	 * Set events into the calendar
	 * @param events to calendar
	 */
	Calendar(ArrayList<CalendarEvent> events) {
		this.events = events;
		setupEventListeners();
		setupTimer();
	}
	/**
	 * Gets the local time 
	 * @param time is the local time
	 * @param minutes is the minutes of the local time
	 * @return the local time
	 */
	public static LocalTime roundTime(LocalTime time, int minutes) {
		LocalTime t = time;

		if (t.getMinute() % minutes > minutes / 2) {
			t = t.plusMinutes(minutes - (t.getMinute() % minutes));
		} else if (t.getMinute() % minutes < minutes / 2) {
			t = t.minusMinutes(t.getMinute() % minutes);
		}

		return t;
	}

	/**
	 * Listen to calendar clicks
	 */
	private void setupEventListeners() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!checkCalendarEventClick(e.getPoint())) {
					checkCalendarEmptyClick(e.getPoint());
				}
			}
		});
	}
	
	/**
	 * 
	 * @param date is the date range
	 * @return the date range
	 */
	protected abstract boolean dateInRange(LocalDate date);

	/**
	 * Checks is the calendar was clicked
	 * @param p is the point where calendas was clicked
	 * @return true of false if the calendas was clicked or not
	 */
	private boolean checkCalendarEventClick(Point p) {
		double x0, x1, y0, y1;
		for (CalendarEvent event : events) {
			if (!dateInRange(event.getDate())) continue;

			x0 = dayToPixel(event.getDate().getDayOfWeek());
			y0 = timeToPixel(event.getStart());
			x1 = dayToPixel(event.getDate().getDayOfWeek()) + dayWidth;
			y1 = timeToPixel(event.getEnd());

			if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
				calendarProduct.fireCalendarEventClick(event, this);
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if the calendar was not clicked
	 * @param p is the point where calendar was clicked or not
	 * @return the point where the calendar was clicked or not
	 */
	private boolean checkCalendarEmptyClick(Point p) {
		final double x0 = dayToPixel(getStartDay());
		final double x1 = dayToPixel(getEndDay()) + dayWidth;
		final double y0 = timeToPixel(START_TIME);
		final double y1 = timeToPixel(END_TIME);

		if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
			LocalDate date = getDateFromDay(pixelToDay(p.getX()));
			calendarProduct.fireCalendarEmptyClick(LocalDateTime.of(date, pixelToTime(p.getY())), this);
			return true;
		}
		return false;
	}

	/**
	 * Gets the date of the week 
	 * @param day is the day of the week
	 * @return the day of the week
	 */
	protected abstract LocalDate getDateFromDay(DayOfWeek day);

	/**
	 * Adds a click listener into calendar
	 * @param l is the click listener
	 * @throws IllegalAccessException 
	 */
	public void addCalendarEventClickListener(CalendarEventClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Event Click Listener nao pode ser null");
		}
		calendarProduct.addCalendarEventClickListener(l);
	}

	/**
	 * Removes the click listener from calendar
	 * @param l is the click listener
	 * @throws IllegalAccessException 
	 */
	public void removeCalendarEventClickListener(CalendarEventClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Event Click Listener nao pode ser null");
		}
		calendarProduct.removeCalendarEventClickListener(l);
	}


	/**
	 * CalendarEmptyClick method to add a calendar empty click listener
	 * @param l is a calendar empty click listener
	 * @throws IllegalAccessException 
	 */
	public void addCalendarEmptyClickListener(CalendarEmptyClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Empty Click Listener nao pode ser null");
		}
		calendarProduct.addCalendarEmptyClickListener(l);
	}
	/**
	 * CalendarEmptyClick method to remove a calendar empty click listener 
	 * @param l is a calendar empty click listener
	 * @throws IllegalAccessException 
	 */
	public void removeCalendarEmptyClickListener(CalendarEmptyClickListener l) throws IllegalAccessException {
		if(l==null) {
			throw new IllegalArgumentException("Calendário Empty Click Listener nao pode ser null");
		}
		calendarProduct.removeCalendarEmptyClickListener(l);
	}
	/**
	 * Calculate scale vars
	 */
	void calculateScaleVars() {
		int width = getWidth();
		int height = getHeight();

		if (width < MIN_WIDTH) {
			width = MIN_WIDTH;
		}

		if (height < MIN_HEIGHT) {
			height = MIN_HEIGHT;
		}

		// Units are pixels per secon
		timeScale = (double) (height - HEADER_HEIGHT) / (END_TIME.toSecondOfDay() - START_TIME.toSecondOfDay());
		dayWidth = (width - TIME_COL_WIDTH) / numDaysToShow();
	}
	/**
	 * 
	 * @return the number of days to show
	 */
	protected abstract int numDaysToShow();
	
	/**
	 * Gives x val of left most pixel for day col
	 * @param dayOfWeek is the day of the week we are in
	 * @return x val of left most pixel for day col
	 */
	protected abstract double dayToPixel(DayOfWeek dayOfWeek);

	/**
	 * Resolves the local time to pixels
	 * @param time is the local time 
	 * @return the time to pixel ratio
	 */
	private double timeToPixel(LocalTime time) {
		return ((time.toSecondOfDay() - START_TIME.toSecondOfDay()) * timeScale) + HEADER_HEIGHT;
	}
	/**
	 * Resolves the pixels to the local time
	 * @param y is the ratio
	 * @return the local time for the ratio
	 */
	private LocalTime pixelToTime(double y) {
		return LocalTime.ofSecondOfDay((int) ((y - HEADER_HEIGHT) / timeScale) + START_TIME.toSecondOfDay()).truncatedTo(ChronoUnit.MINUTES);
	}

	/**
	 * Resolves the pixel to day
	 * @param x is the ratio for pixel to day
	 * @return the pixel to day ratio
	 */
	private DayOfWeek pixelToDay(double x) {
		double pixel = 0;
		DayOfWeek day = null;
		for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
			pixel = pixel(pixel, day, i);
			day = DayOfWeek.of(i);
			if (x >= pixel && x < pixel + dayWidth) {
				return day;
			}
		}
		return null;
	}
	private double pixel(double pixel, DayOfWeek day, int i) {
		day = DayOfWeek.of(i);
		pixel = dayToPixel(day);
		return pixel;
	}

	@Override
	
	protected void paintComponent(Graphics g) {
		calculateScaleVars();
		g2 = (Graphics2D) g;

		// Rendering hints try to turn anti-aliasing on which improves quality
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Set background to white
		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());

		// Set paint colour to black
		g2.setColor(Color.black);

		drawDayHeadings();
		drawTodayShade();
		drawGrid();
		drawTimes();
		drawEvents();
		drawCurrentTimeLine();
	}
	/**
	 * 
	 * @return the start day of the week
	 */
	protected abstract DayOfWeek getStartDay();

	/**
	 * 
	 * @return the end day of the week 
	 */
	protected abstract DayOfWeek getEndDay();

	/**
	 * Draw the day Headings
	 */
	private void drawDayHeadings() {
		int y = 20;
		int x = 0;
		LocalDate day = null;
		DayOfWeek dayOfWeek = null;

		for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
			x = x(x, day, dayOfWeek, i);
			day = day(day, dayOfWeek, i);
			dayOfWeek = DayOfWeek.of(i);
			String text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/" + day.getMonthValue();
			g2.drawString(text, x, y);
		}
	}
	private int x(int x, LocalDate day, DayOfWeek dayOfWeek, int i) {
		day = day(day, dayOfWeek, i);
		dayOfWeek = DayOfWeek.of(i);
		String text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/"
				+ day.getMonthValue();
		x = (int) (dayToPixel(DayOfWeek.of(i)) + (dayWidth / 2) - (FONT_LETTER_PIXEL_WIDTH * text.length() / 2));
		return x;
	}
	private LocalDate day(LocalDate day, DayOfWeek dayOfWeek, int i) {
		dayOfWeek = DayOfWeek.of(i);
		day = getDateFromDay(dayOfWeek);
		return day;
	}
	/**
	 * Draw the grid of the calendar
	 */
	private void drawGrid() {
		// Save the original colour
		final Color ORIG_COLOUR = g2.getColor();

		// Set colour to grey with half alpha (opacity)
		Color alphaGray = new Color(128, 128, 128, 128);
		Color alphaGrayLighter = new Color(200, 200, 200, 128);
		g2.setColor(alphaGray);

		// Draw vertical grid lines
		double x;
		for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
			x = dayToPixel(DayOfWeek.of(i));
			g2.draw(new Line2D.Double(x, HEADER_HEIGHT, x, timeToPixel(END_TIME)));
		}

		// Draw horizontal grid lines
		double y;
		int x1 = 0;
		for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusMinutes(30)) {
			y = timeToPixel(time);
			x1 = x1(x1, time);
			if (time.getMinute() == 0) {
				g2.setColor(alphaGray);
			} else {
				g2.setColor(alphaGrayLighter);
			}
			g2.draw(new Line2D.Double(x1, y, dayToPixel(getEndDay()) + dayWidth, y));
		}

		// Reset the graphics context's colour
		g2.setColor(ORIG_COLOUR);
	}
	private int x1(int x1, LocalTime time) {
		if (time.getMinute() == 0) {
			x1 = 0;
		} else {
			x1 = TIME_COL_WIDTH;
		}
		return x1;
	}

	/**
	 * Draw the shade of the day
	 */
	private void drawTodayShade() {
		LocalDate today = LocalDate.now();

		// Check that date range being viewed is current date range
		if (!dateInRange(today)) return;

		final double x = dayToPixel(today.getDayOfWeek());
		final double y = timeToPixel(START_TIME);
		final double width = dayWidth;
		final double height = timeToPixel(END_TIME) - timeToPixel(START_TIME);

		final Color origColor = g2.getColor();
		Color alphaGray = new Color(200, 200, 200, 64);
		g2.setColor(alphaGray);
		g2.fill(new Rectangle2D.Double(x, y, width, height));
		g2.setColor(origColor);
	}

	/**
	 * Draw the current time line into calendar
	 */
	private void drawCurrentTimeLine() {
		LocalDate today = LocalDate.now();

		// Check that date range being viewed is current date range
		if (!dateInRange(today)) return;

		final double x0 = dayToPixel(today.getDayOfWeek());
		final double x1 = dayToPixel(today.getDayOfWeek()) + dayWidth;
		final double y = timeToPixel(LocalTime.now());

		final Color origColor = g2.getColor();
		final Stroke origStroke = g2.getStroke();

		g2.setColor(new Color(255, 127, 110));
		g2.setStroke(new BasicStroke(2));
		g2.draw(new Line2D.Double(x0, y, x1, y));

		g2.setColor(origColor);
		g2.setStroke(origStroke);
	}

	/**
	 * Draw time
	 */
	private void drawTimes() {
		int y;
		for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusHours(1)) {
			y = (int) timeToPixel(time) + 15;
			g2.drawString(time.toString(), TIME_COL_WIDTH - (FONT_LETTER_PIXEL_WIDTH * time.toString().length()) - 5, y);
		}
	}
	/**
	 * Dras events
	 */
	private void drawEvents() {
		double x = 0;
		double y0 = 0;
		for (CalendarEvent event : events) {
			if (!dateInRange(event.getDate())) continue;

			Rectangle2D rect = rect(x, y0, event);
			x = dayToPixel(event.getDate().getDayOfWeek());
			y0 = timeToPixel(event.getStart());

			Color origColor = g2.getColor();
			g2.setColor(event.getColor());
			g2.fill(rect);
			g2.setColor(origColor);

			// Draw time header

			// Store the current font state
			Font origFont = g2.getFont();

			final float fontSize = origFont.getSize() - 1.6F;

			// Create a new font with same properties but bold
			Font newFont = origFont.deriveFont(Font.BOLD, fontSize);
			g2.setFont(newFont);

			g2.drawString(event.getStart() + " - " + event.getEnd(), (int) x + 5, (int) y0 + 11);

			// Unbolden
			g2.setFont(origFont.deriveFont(fontSize));

			// Draw the event's text
			drawStringMultiLine(g2, event.getText(), (int) dayWidth - 10, (int) x + 5, (int) y0 + 23);
			// Reset font
			g2.setFont(origFont);
		}
	}
	private Rectangle2D rect(double x, double y0, CalendarEvent event) {
		x = dayToPixel(event.getDate().getDayOfWeek());
		y0 = timeToPixel(event.getStart());
		Rectangle2D rect = new Rectangle2D.Double(x, y0, dayWidth,
				(timeToPixel(event.getEnd()) - timeToPixel(event.getStart())));
		return rect;
	}
	/**
	 * Draw multiline strings
	 * @param g is the 2D graphics
	 * @param text is the text in the string
	 * @param lineWidth is the width of the lines
	 * @param x is a measure
	 * @param y is a measure
	 */
	public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
		FontMetrics m = g.getFontMetrics();
		if(m.stringWidth(text) < lineWidth) {
			g.drawString(text, x, y);
		} else {
			String[] words = text.split(" ");
			String currentLine = words[0];
			for(int i = 1; i < words.length; i++) {
				if(m.stringWidth(currentLine+words[i]) < lineWidth) {
					currentLine += " "+words[i];
				} else {
					g.drawString(currentLine, x, y);
					y += m.getHeight();
					currentLine = words[i];
				}
			}
			if(currentLine.trim().length() > 0) {
				g.drawString(currentLine, x, y);
			}
		}
	}
	/**
	 * 
	 * @return the day width
	 */
	protected double getDayWidth() {
		return dayWidth;
	}

	
	/**
	 * Repaints every minute to update the current time line
	 */
	private void setupTimer() {
		Timer timer = new Timer(1000*60, e -> repaint());
		timer.start();
	}
	/**
	 * set the range to today
	 */
	protected abstract void setRangeToToday();
	
	/**
	 * gets the range to today
	 */
	public void goToToday() {
		setRangeToToday();
		repaint();
	}
	/**
	 * Add the event into calendar
	 * @param event is the event we add
	 */
	public void addEvent(CalendarEvent event) {
		events.add(event);
		repaint();
	}
	/**
	 * Removes the event from calendar
	 * @param event is the event we remove
	 * @return true if the event had been removed or false if event hadnt been removed
	 */
	public boolean removeEvent(CalendarEvent event) {
		boolean removed = events.remove(event);
		repaint();
		return removed;
	}
	/**
	 * Sets the events into calendar
	 * @param events is a list of the calendar events
	 */
	public void setEvents(ArrayList<CalendarEvent> events) {
		this.events = events;
		repaint();
	}
}