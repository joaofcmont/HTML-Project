package com.SwingCalendar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class ConnectToDB {
	public void ConnectToDB() throws IOException {

		MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

		//acessar base de dados
		MongoDatabase database=client.getDatabase("ESProjectDB");

		//acessar a minha coleção
		MongoCollection<Document> col= database.getCollection("ESProjectCollection");

		try {   

			String path = "./filename.json";
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter("filename.json"))
					);
			MongoCursor<Document> cursor = col.find().cursor();

			while (cursor.hasNext()){
				out.println(cursor.next().toJson());

			}
			out.flush();            // flush to ensure writes
			out.close();     // close the handle when done

		} catch (IOException e) {
			System.out.println("Error writing to file.");
		}

	}
	

}
