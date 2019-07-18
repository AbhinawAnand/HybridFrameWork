package com.automationAbhinawDemo.utilities;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
//import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.ElementPropertyContainer;
//import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
public class PDFReporting extends TestListenerAdapter{

	PdfWriter writer;
	PdfDocument pdfDoc;
	Document doc = null;
	Paragraph para = null;
	PdfPTable ptab = null;
	PdfPCell pcell = null;
	
	public void onStart(ITestContext testcontext) {
		
		String time_stamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ssss").format(new Date());
		String reportName= "Abhinaw_Demo_Report "+time_stamp+".pdf"; //creatUniquereportName 
		String reportPath = System.getProperty("user.dir")+"//test-output//"+reportName;
		pdfDoc = new PdfDocument();
		doc = new Document();
		
	

		try { 
			FileOutputStream fos = new FileOutputStream(reportPath);
			writer = PdfWriter.getInstance(doc, fos);
			doc.addHeader("Abhinaw", "Anand");
			doc.open();
			para = new Paragraph("Automation PDF Reports - Learn with Abhinaw",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.BLUE));
			doc.add(para);
			doc.add(new Paragraph("\n\n\n\n Date :: "+new Date().toString())); 
			doc.add(new Paragraph("\n Author :: Abhinaw Anand"));
			doc.add(new Paragraph("\n Env :: QA"));
			doc.add(new Paragraph("\n Host :: Local Host"));
		
		}
		catch (Exception e)
		{// TODO Auto-generated catch block
			e.printStackTrace();}

	}
	
	//@Override
	public void onTestSuccess(ITestResult testResult) {
		
		try {
			para = new Paragraph("Test Case => "+testResult.getName()+" :: Passed\n\n",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.GREEN));
			doc.add(para);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void onTestFailure(ITestResult testResult) {
		try {
			para = new Paragraph("Test Case => "+testResult.getName()+" :: Failed\n\n",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.RED));
			doc.add(para);
			//doc.add(Chunk.NEWLINE);
			ptab = new PdfPTable(2);
			pcell = new PdfPCell(new Paragraph(testResult.getName()+" :: Failure Error and Screenshot Below",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.RED)));
			pcell.setColspan(2);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ptab.addCell(pcell);
			ptab.addCell(testResult.getThrowable().getMessage());
			Image img = Image.getInstance(System.getProperty("user.dir")+"\\screenshots\\"+testResult.getName()+".png");
			ptab.addCell(img);
			doc.add(ptab);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult testResult) {
		try {
			para = new Paragraph("Test Case => "+testResult.getName()+" :: Skipped\n\n",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.BLUE));
			doc.add(para);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void onFinish(ITestContext context)
	{
		doc.close();
	    writer.close();
	}
	
	
	
	
	
}
