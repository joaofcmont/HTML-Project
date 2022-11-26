package com.SwingCalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class ToJson {
	public String paraJson() throws FileNotFoundException {
		
		ConnectToDB db = new ConnectToDB();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Eventos eventos = new Eventos();
		eventos.setListaEventos(listaEventos());
		
		FindIterable<org.bson.Document> cursor = db.user1.find();
		MongoCursor<org.bson.Document> iterator = cursor.iterator(); // (6) 
	 
			try (FileWriter writer = new FileWriter("agenda.json")) {
				gson.toJson(iterator.next(), writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return gson.toJson(eventos);
		
	}
	
	public ArrayList<Event> listaEventos() throws FileNotFoundException {
		
		ArrayList<Event> list = new ArrayList<>();
		Scanner scan = new Scanner(new File("agenda.txt"));
		
		while(scan.hasNextLine()) {
			String chair = scan.nextLine();
			String dateStart = scan.nextLine();
			String dateEnd = scan.nextLine();
			
			scan.nextLine();
			
			Event event = new Event(chair, dateStart, dateEnd);
		
				list.add(event);
		}
		scan.close();
		return list;
	}
	
	
	
}