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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


/**
 * Converts the agenda.txt to json and sends the json to the Database
 * @version 08/12/2022
 */
public class ToJson {

	/**
	 * Returns the events in json from agenda.txt
	 * @return the events write in json
	 * @throws IOException because we write into agenda.json
	 */
	public String paraJson() throws IOException {
		
		Parser p = new Parser();
		p.parser();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		
		Eventos eventos = new Eventos();
		eventos.setListaEventos(listaEventos());
		
		FindIterable<org.bson.Document> cursor = getUser().find();
		MongoCursor<org.bson.Document> iterator = cursor.iterator(); 

		try (FileWriter writer = new FileWriter("agenda.json")) {
			while(iterator.hasNext()) {
				gson.toJson(iterator.next(), writer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return gson.toJson(eventos);
	}
	
	/**
	 * 
	 * @return the documents of the collection in Database 
	 */
	public MongoCollection<Document> getUser() {
		ConnectToDB db = new ConnectToDB();
		MongoCollection<Document> user= db.database.getCollection("Eventos");
		return user;
	}
	
	/**
	 * Get the list of the events in "agenda.txt"
	 * @return the list of events from "agenda.txt"
	 * @throws FileNotFoundException to acess the file "agenda.txt"
	 */
	public ArrayList<Event> listaEventos() throws FileNotFoundException {

		ConnectToDB db = new ConnectToDB();

		ArrayList<Event> list = new ArrayList<>();
		Scanner scan = new Scanner(new File("agenda.txt"));

		while(scan.hasNextLine()) {
			String user = scan.nextLine();
			String chair = scan.nextLine();
			String dateStart = scan.nextLine();
			String dateEnd = scan.nextLine();
			scan.nextLine();

			Event event = new Event(chair, dateStart, dateEnd, user);

			list.add(event);
		}
		scan.close();
		return list;
	}



}