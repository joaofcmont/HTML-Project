package com.SwingCalendar;
public class Event {
		private String chair;
	private String dateStart;
	private String dateEnd;
	private String username;
	
<<<<<<< HEAD
	public Event(String username,String chair, String dateStart, String dateEnd) {
=======
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Event(String chair, String dateStart, String dateEnd, String username) {
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
		this.chair = chair;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.username=username;
<<<<<<< HEAD
		
=======
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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