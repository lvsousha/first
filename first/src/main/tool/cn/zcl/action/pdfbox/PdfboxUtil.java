package cn.zcl.action.pdfbox;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfboxUtil {

	
	public void test(){
		PDDocument pd; 
		try {
			File input = new File("D:\\TEST.pdf"); 
			pd = PDDocument.load(input);
			System.out.println(pd.getNumberOfPages());
			System.out.println(pd.isEncrypted());
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(100);
			stripper.setEndPage(110);
			stripper.setSortByPosition(false);
			System.out.println(stripper.getText(pd));
			if (pd != null) {
				pd.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PdfboxUtil pu = new PdfboxUtil();
		pu.test();
		System.out.println("End");

	}

}
