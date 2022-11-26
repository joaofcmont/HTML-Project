package com.SwingCalendar;

import java.io.BufferedWriter;
import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class ConnectToDB {

	MongoClient client= MongoClients.create("mongodb+srv://testUser:DiogoMonteiro12@esprojectcluster.jjsxybq.mongodb.net/?retryWrites=true&w=majority");

	//acessar base de dados
	MongoDatabase database=client.getDatabase("ESProjectDB");
	
	//acessar a minha coleção
	MongoCollection<Document> col_links= database.getCollection("links");		
	MongoCollection<Document> user1= database.getCollection("user1");
	MongoCollection<Document> user2= database.getCollection("user2");
	MongoCollection<Document> user3= database.getCollection("user3");
	MongoCollection<Document> user4= database.getCollection("user4");


	

}









