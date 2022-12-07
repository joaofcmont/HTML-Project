package com.SwingCalendar;

import java.io.File;
import java.io.FileNotFoundException;
<<<<<<< HEAD

=======
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ConnectToDB {
	public ConnectToDB() throws FileNotFoundException{}

	MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

	//acessar base de dados
	MongoDatabase database=client.getDatabase("ESProjectDB");
<<<<<<< HEAD
	Parser p= new Parser();

	User usernames = new User(p.readFile(new File("links.txt")));
	String username= usernames.setUser(p.readFile(new File("links.txt")));


	//sList<String> usernames= p.readFile(f);

	//acessar a minha coleção

=======

	//acessar a minha coleção	
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
	MongoCollection<Document> col_links= database.getCollection("links");		
<<<<<<< HEAD
	MongoCollection<Document> user= database.getCollection(username);

=======

	
	
	
>>>>>>> branch 'branch_joaoiscte' of https://github.com/joaoiscte/ES-LETI-1Sem-2022-Grupo-13.git
}












