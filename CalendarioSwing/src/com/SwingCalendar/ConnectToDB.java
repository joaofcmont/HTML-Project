package com.SwingCalendar;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Connects to the Database and acesses the collections
 * @version 08/12/2022
 *
 */
public class ConnectToDB {
	
	/**
	 * Connects to the DB
	 */
	public ConnectToDB() {}

	/**
	 * create database
	 */
	MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

	/**
	 * access database
	 */
	MongoDatabase database=client.getDatabase("ESProjectDB");

	/**
	 * access database collection
	 */
	MongoCollection<Document> col_links= database.getCollection("links");		
	
}
	



