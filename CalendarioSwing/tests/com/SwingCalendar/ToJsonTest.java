package com.SwingCalendar;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoCollection;

class ToJsonTest {

	ToJson tj;
	MongoCollection<Document> teste; 
	 ArrayList<Event> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		ConnectToDB db = new ConnectToDB();
		 teste= db.database.getCollection("Eventos");
		 tj = new ToJson();
		 tj.paraJson();
		 list = new ArrayList<>();
	}

	@Test
	void testParaJson() throws IOException {
		assertNotEquals("",tj.paraJson());
	}

	@Test
	void testGetUser() throws IOException {
		tj.paraJson();
		assertNotEquals(teste,tj.getUser());
	}

	@Test
	void testListaEventos() throws FileNotFoundException {
		
		assertNotEquals(tj.listaEventos(),list);
	}

}
