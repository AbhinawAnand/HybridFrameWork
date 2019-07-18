package com.automationAbhinawDemo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL_Functions {
	
	public static FileInputStream file_input;
	public static FileOutputStream file_output;
	public static XSSFWorkbook wb;
	public static HSSFWorkbook hwb;
	public static XSSFSheet ws;
	public static HSSFSheet hws;	
	public static XSSFRow row;
	public static HSSFRow hrow;
	public static XSSFCell cell;
	public static HSSFCell hcell;
		
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		int rowcount;
		file_input=new FileInputStream(xlfile);
		if(xlfile.contains(".xlsx"))
		{
			wb=new XSSFWorkbook(file_input);
			ws=wb.getSheet(xlsheet);
			rowcount=ws.getLastRowNum();
			wb.close();
		}
		else {
			hwb=new HSSFWorkbook(file_input);
			hws=hwb.getSheet(xlsheet);
			rowcount=hws.getLastRowNum();
			hwb.close();
		}
		
		file_input.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		int cellcount = 0;
		file_input=new FileInputStream(xlfile);
		if(xlfile.contains(".xlsx"))
		{
			wb=new XSSFWorkbook(file_input);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cellcount=row.getLastCellNum();
			wb.close();
		}
		else
		{
			hwb=new HSSFWorkbook(file_input);
			hws=hwb.getSheet(xlsheet);
			hrow=hws.getRow(rownum);
			cellcount=row.getLastCellNum();
			wb.close();

		}

		file_input.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{	String data;
	file_input=new FileInputStream(xlfile);

	if(xlfile.contains(".xlsx"))
	{
		wb=new XSSFWorkbook(file_input);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		try 
		{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();

	}

	else 
	{
		hwb=new HSSFWorkbook(file_input);
		hws=hwb.getSheet(xlsheet);
		hrow=hws.getRow(rownum);
		hcell=hrow.getCell(colnum);

		try 
		{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(hcell);
			return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		hwb.close();

	}


	file_input.close();
	return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		file_input=new FileInputStream(xlfile);
		if(xlfile.contains(".xlsx"))
		{
		wb=new XSSFWorkbook(file_input);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		file_output=new FileOutputStream(xlfile);
		wb.write(file_output);		
		wb.close();
		
		}
		
		else {
			hwb=new HSSFWorkbook(file_input);
			hws=hwb.getSheet(xlsheet);
			hrow=hws.getRow(rownum);
			hcell=hrow.createCell(colnum);
			hcell.setCellValue(data);
			file_output=new FileOutputStream(xlfile);
			hwb.write(file_output);		
			hwb.close();
			
		}
		file_input.close();
		file_output.close();
	}

}
