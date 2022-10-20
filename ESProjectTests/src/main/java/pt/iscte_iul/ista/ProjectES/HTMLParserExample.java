package pt.iscte_iul.ista.ProjectES;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParserExample {

	public static void main(String[] args) throws IOException {
		File input = new File("/tmp/input.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		String filePath = "ESProjectTests\\input.txt";
		Scanner sc = new Scanner(new File(filePath));
		
		//Data em pesquisa para verificar a disponibilidade
		for(int i = 0; i < 5; i++) {//Percorrer o ficheiro
			String dataEhora = doc.getElementsByTag("Data").get(i).text();
			String dataprocurada = null; //Utilizar a data dada como input no html
			if(!dataEhora.equals(dataprocurada)) {
				System.out.println("Encontra-se toda a gente disponível");


			}else {
				System.out.println("Pessoas indisponíveis na data procurada");
				System.out.println(doc.getElementsByTag("Nome").get(i).text());
			}
		}




	}

}
