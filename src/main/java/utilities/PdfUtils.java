package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

public class PdfUtils {
	
	public Logger log = Logger.getLogger(PdfUtils.class.getName());
	public PdfUtils() {
		
	}
	
	// Verify content of PDF FILE
		public void verifyContentInPDF() throws IOException {
			// SPECFY THE PATH OF PDF FILE
			FileInputStream doc = new FileInputStream("");
			PDDocument pdfDoc = PDDocument.load(doc);
			String pdfContent = new PDFTextStripper().getText(pdfDoc);
			System.out.println("Text displayed as : "+ pdfContent);
			Assert.assertTrue(pdfContent.contains("Text of that content"));
		}
	
	public PDDocument loadPdfDocument(String filePath, String fileName) {
		String pdfLocation;
		PDDocument pdf = null;
		try {
			pdfLocation = filePath + fileName;
			System.out.println(pdfLocation + "&&&&&&&&&&&&&&&&&&&");
			File pdfFile = new File(pdfLocation);
			pdf = PDDocument.load(pdfFile);
		}
		catch(Exception e) {
			log.info("------ OBSERVED EXCEPTION ------------ : " + e +
					" in method loadPdfDocument(String filePath, String fileName)");
		}
		return pdf;
	}
	
	
	public int getPdfPageCount(PDDocument pdf) {
		int pageCount = 0;
		try {
			pageCount = pdf.getNumberOfPages();
		}catch(Exception e) {
			log.info("-------OBSERVED EXCEPTION --------- : " + e +
					"in method getPdfPageCount");
			
		}
		return pageCount;
	}
	
	public void closedPdfDocument(PDDocument pdf) {
		try {
			pdf.close();
		}catch(Exception e) {
			log.info("--------- OBSERVED EXCEPTION --------- : " + e +
					"in method closedPdfDocument(PDDocument pdf)");
		}
	}
	
	
	
	
	
	
	
	
	
	

}
