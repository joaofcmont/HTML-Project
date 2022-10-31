package com.SwingCalendar;
public class Event {
		private String chair;
	private String dateStart;
	private String dateEnd;
	
	public Event(String chair, String dateStart, String dateEnd) {
		this.chair = chair;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		
	}

	public String getChair() {
		return chair;
	}

	public void setChair(String chair) {
		this.chair = chair;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	
	}