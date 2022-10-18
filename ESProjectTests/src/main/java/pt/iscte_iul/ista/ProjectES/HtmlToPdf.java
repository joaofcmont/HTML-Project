package pt.iscte_iul.ista.ProjectES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPdf {

	public static void main(String[] args) {
		PdfWriter pdfWriter = null;

		// create a new document
		Document document = new Document();
		try {
			String html = VelocityTemplateParser.generateHTML();

			document = new Document();
			// document header attributes
			document.addAuthor("dfsaa1iscte");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("dfsaa1iscte.github.io");
			document.addTitle("HTML to PDF using itext");
			document.setPageSize(PageSize.LETTER);

			OutputStream file = new FileOutputStream(new File("Test.pdf"));
			pdfWriter = PdfWriter.getInstance(document, file);

			// open document
			document.open();

			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.getDefaultCssResolver(true);
			xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));
			// close the document
			document.close();
			// close the writer
			pdfWriter.close();

			System.out.println("PDF generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
