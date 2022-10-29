package Grupo13.Calendario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json {
	public static void parse(String[] src) {

		JsonParser parser=new JsonParser();
		
		try {
			
			Object obj= parser.parse(new FileReader("myJSON.json"));
			JsonObject jsonObject= (JsonObject) obj;
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}




}
