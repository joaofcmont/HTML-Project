package com.SwingCalendar;


public class User {

	Parser p = new Parser();
	
	private String link;
	
	public User(String link) {
		this.link=link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String setUser(String link) {

		String[] username= link.split("username=",2);
		String[] username1= username[1].split("@",2);
		User user= new User(username1[0]);
		
		return user.getLink();
	}
}
