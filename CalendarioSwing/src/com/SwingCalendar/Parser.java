package com.SwingCalendar;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Parser from a given link to a .txt file
 @version 8/12/2022
 **/

public class Parser{

	
	/**
	 * Class that parses a link into agenda.txt file. This file is created within this class
	 * @throws IOException
	 * Throw IOException because we are creating a file named "links.txt"
	 */
	public void parser() throws IOException {
		File myObj = new File("links.txt");
		if(myObj.exists() && !myObj.isDirectory()) { 
			Scanner	scanner = new Scanner(myObj);
			while (scanner.hasNextLine()) 
			{			
				String webcal = scanner.nextLine();
				User usernames = new User(webcal);
				String username= usernames.setUser(webcal);
				webcal  = webcal.replace("webcal:", "https:");

				//creates a URL type object based on the string we give
				URL url = new URL(webcal);
				//copies to a file what the link gives us, ics file
				Files.copy(url.openStream(), Paths.get("webcal.txt"), REPLACE_EXISTING);


				Scanner	scan = new Scanner(new File("webcal.txt"));
				String dateStart = null;
				String dateEnd = null;

				File f1 = new File("agenda.txt");
				long lastModified = f1.lastModified();


				if(f1.exists()) { 
					f1.delete();
				}

				while (scan.hasNextLine()) 
				{	
					
					//write in the agenda.txt file only what we want from the webcal.txt file
					BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt",true));
					
					String a = scan.nextLine();	
					if(a.equals("BEGIN:VEVENT")) 
					{
						scan.nextLine();
						a = scan.nextLine();

						dateStart = a.split(":")[1];
						dateStart = dateStart.replace("T","");
						dateStart = dateStart.replace("Z","");

						a = scan.nextLine();		

						dateEnd = a.split(":")[1];	
						dateEnd = dateEnd.replace("T","");
						dateEnd = dateEnd.replace("Z","");

						String summary = scan.nextLine();	 
						a = scan.nextLine();	 
						if(!a.contains("UID:")) 
						{
							summary =summary+a.strip();		 	 

						}
						if (summary.contains("Teste:" ) || summary.contains("Exame:") || summary.contains("AvaliaÃ§Ã£o ContÃ­nua:")) {summary = summary.split(":")[2] + " - Exame";
						summary = summary.strip();			
						} else {
							summary = summary.split(":")[1];
						}	 
						
						String l = username + "\n" +summary+ "\n" +dateStart+ "\n" +dateEnd+ "\n \n";
						writer.write(l);
						writer.close();
					}  	 
				}
				scan.close();
			}
			scanner.close();
		}else {
			myObj.delete();
		}
	}





}