package com.SwingCalendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class ConnectToDB {
	
	public ConnectToDB() throws FileNotFoundException{}
	
	
	MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

	//acessar base de dados
	MongoDatabase database=client.getDatabase("ESProjectDB");

	Parser p= new Parser();

	User usernames = new User(p.readFile(new File("links.txt")));
	String username= usernames.setUser(p.readFile(new File("links.txt")));

	
	//acessar a minha coleção
			
	MongoCollection<Document> col_links= database.getCollection("links");		
	MongoCollection<Document> user= database.getCollection(username);
	
	
	
}

	







