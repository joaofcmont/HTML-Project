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

public class ToJson {

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
<<<<<<< HEAD
=======
		} catch (IOException e) {
			e.printStackTrace();
		}
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
		return gson.toJson(eventos);
	}
	public MongoCollection<Document> getUser() throws FileNotFoundException {
		ConnectToDB db = new ConnectToDB();
		MongoCollection<Document> user= db.database.getCollection("Eventos");
		return user;
	}
	public ArrayList<Event> listaEventos() throws FileNotFoundException {
<<<<<<< HEAD

		ConnectToDB db = new ConnectToDB();

=======
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
		ArrayList<Event> list = new ArrayList<>();
		Scanner scan = new Scanner(new File("agenda.txt"));

		while(scan.hasNextLine()) {
			String user = scan.nextLine();
			String chair = scan.nextLine();
			String dateStart = scan.nextLine();
			String dateEnd = scan.nextLine();
			scan.nextLine();

<<<<<<< HEAD
			Event event = new Event(db.username,chair, dateStart, dateEnd);
=======
			Event event = new Event(chair, dateStart, dateEnd, user);
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git

			list.add(event);
		}
		scan.close();
		return list;
	}



}