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


public class Parser{

	public void parser() throws IOException {
		
		Scanner	scanner = new Scanner(new File("links.txt"));
		while (scanner.hasNextLine()) 
		{	
		String webcal = scanner.nextLine();
		webcal  = webcal.replace("webcal:", "https:");
		//cria um objecto do tipo URL baseado na string que damos
		URL url = new URL(webcal);
		//copia para um ficheiro o que o link nos dÃ¡, ics file 
		Files.copy(url.openStream(), Paths.get("webcal.txt"), REPLACE_EXISTING);

		
			Scanner	scan = new Scanner(new File("webcal.txt"));
			String dateStart = null;
			String dateEnd = null;
			
			File f1 = new File("agenda.txt");
			if(f1.exists()) { 
			    f1.delete();
			}
			
			while (scan.hasNextLine()) 
			{	
				//escrever no file agenda.txt apenas o que queremos do file webcal.txt
				BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt",true));;
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
					//System.out.println(summary+ "\n" +dateStart+ "\n" +dateEnd+ "\n");
					String l =summary+ "\n" +dateStart+ "\n" +dateEnd+ "\n \n";
					
					writer.write(l);
					writer.close();
				}  	 
			}
			scan.close();
		}
		scanner.close();
		}

	
}
