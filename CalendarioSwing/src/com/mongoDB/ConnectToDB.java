package com.mongoDB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 

public class ConnectToDB {

	public static void main(String[] args) throws IOException {
		try {
		      File calendario = new File("calendario.json");
		      if (calendario.createNewFile()) {
		        System.out.println("File created: " + calendario.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

		//acessar base de dados
		MongoDatabase database=client.getDatabase("ESProjectDB");
		
		//acessar a minha coleção
		MongoCollection<Document> col= database.getCollection("ESProjectCollection");
		
		Document document=new Document("title","Calendário1").append("Name","Mariana");
		
		List<Document> list= new ArrayList<Document>();
		list.add(document);
		col.insertMany(list);
		
		FileWriter myWriter = new FileWriter("calendario.json"); 

		FindIterable<Document> iterDoc = col.find();
		int i=1;
		Iterator it=iterDoc.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
		
		
		
	}

}
