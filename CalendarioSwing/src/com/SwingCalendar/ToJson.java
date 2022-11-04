package com.SwingCalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToJson {


	public void paraJson() throws FileNotFoundException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Eventos eventos = new Eventos();
		eventos.setListaEventos(listaEventos());
		
		try (FileWriter writer = new FileWriter("agenda.json")) {
            gson.toJson(eventos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
