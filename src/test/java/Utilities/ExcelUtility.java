package Utilities;



import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

	private String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	// Common method
	private XSSFWorkbook getWorkbook() throws IOException {
		FileInputStream fi = new FileInputStream(path);
		return new XSSFWorkbook(fi);
	}

	// Row Count
	public int getRowCount(String sheetName) throws IOException {

		XSSFWorkbook workbook = getWorkbook();
		XSSFSheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			workbook.close();
			throw new RuntimeException("Sheet not found: " + sheetName);
		}

		int rowcount = sheet.getLastRowNum();
		workbook.close();
		return rowcount;
	}

	// Column Count
	public int getCellCount(String sheetName, int rownum) throws IOException {

		XSSFWorkbook workbook = getWorkbook();
		XSSFSheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			workbook.close();
			throw new RuntimeException("Sheet not found: " + sheetName);
		}

		XSSFRow row = sheet.getRow(rownum);

		if (row == null) {
			workbook.close();
			return 0;
		}

		int cellcount = row.getLastCellNum();
		workbook.close();
		return cellcount;
	}

	// Get Cell Data
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

		XSSFWorkbook workbook = getWorkbook();
		XSSFSheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			workbook.close();
			throw new RuntimeException("Sheet not found: " + sheetName);
		}

		XSSFRow row = sheet.getRow(rownum);
		if (row == null) {
			workbook.close();
			return "";
		}

		XSSFCell cell = row.getCell(colnum);
		if (cell == null) {
			workbook.close();
			return "";
		}

		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(cell);

		workbook.close();
		return data;
	}

	// Set Cell Data
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

		FileInputStream fi = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			sheet = workbook.createSheet(sheetName);
		}

		XSSFRow row = sheet.getRow(rownum);
		if (row == null) row = sheet.createRow(rownum);

		XSSFCell cell = row.getCell(colnum);
		if (cell == null) cell = row.createCell(colnum);

		cell.setCellValue(data);

		fi.close();

		FileOutputStream fo = new FileOutputStream(path);
		workbook.write(fo);

		fo.close();
		workbook.close();
	}

	//Green Color (PASS)
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {

		FileInputStream fi = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		XSSFRow row = sheet.getRow(rownum);
		if (row == null) row = sheet.createRow(rownum);

		XSSFCell cell = row.getCell(colnum);
		if (cell == null) cell = row.createCell(colnum);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		fi.close();

		FileOutputStream fo = new FileOutputStream(path);
		workbook.write(fo);

		fo.close();
		workbook.close();
	}

	// Red Color (FAIL)
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {

		FileInputStream fi = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		XSSFRow row = sheet.getRow(rownum);
		if (row == null) row = sheet.createRow(rownum);

		XSSFCell cell = row.getCell(colnum);
		if (cell == null) cell = row.createCell(colnum);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		fi.close();

		FileOutputStream fo = new FileOutputStream(path);
		workbook.write(fo);

		fo.close();
		workbook.close();
	}
}






























//package Utilities;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtility {
//
//	public FileInputStream fi;
//	public FileOutputStream fo;
//	public XSSFWorkbook workbook;
//	public XSSFSheet sheet;
//	public XSSFRow row;
//	public XSSFCell cell;
//	public CellStyle style;   
//	String path;
//
//	public ExcelUtility(String path) // constructor
//	{
//		this.path=path;
//	}
//
//	public int getRowCount(String sheetName) throws IOException 
//	{
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//		sheet=workbook.getSheet(sheetName);
//		int rowcount=sheet.getLastRowNum();
//		workbook.close();
//		fi.close();
//		return rowcount;		
//	}
//
//	public int getCellCount(String sheetName,int rownum) throws IOException
//	{
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//		sheet=workbook.getSheet(sheetName);
//		row=sheet.getRow(rownum);
//		int cellcount=row.getLastCellNum();
//		workbook.close();
//		fi.close();
//		return cellcount;
//
//	}
//
//
//	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
//	{
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//		sheet=workbook.getSheet(sheetName);
//		row=sheet.getRow(rownum);
//		cell=row.getCell(colnum);
//
//		DataFormatter formatter = new DataFormatter();
//		String data;
//		try{
//			data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
//		}
//		catch(Exception e)
//		{
//			data="";
//		}
//		workbook.close();
//		fi.close();
//		return data;
//	}
//
//	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
//	{
//		File xlfile=new File(path);
//		if(!xlfile.exists())    // If file not exists then create new file
//		{
//			workbook=new XSSFWorkbook();
//			fo=new FileOutputStream(path);
//			workbook.write(fo);
//		}
//
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//
//		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
//			workbook.createSheet(sheetName);
//		sheet=workbook.getSheet(sheetName);
//
//		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
//			sheet.createRow(rownum);
//		row=sheet.getRow(rownum);
//
//		cell=row.createCell(colnum);
//		cell.setCellValue(data);
//		fo=new FileOutputStream(path);
//		workbook.write(fo);		
//		workbook.close();
//		fi.close();
//		fo.close();
//	}
//
//
//	public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
//	{
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//		sheet=workbook.getSheet(sheetName);
//
//		row=sheet.getRow(rownum);
//		if(row == null) row = sheet.createRow(rownum);
//		cell=row.getCell(colnum);
//		if(cell == null) cell = row.createCell(colnum);
//
//		style=workbook.createCellStyle();
//
//		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
//
//		cell.setCellStyle(style);
//		fi.close();
//		fo = new FileOutputStream(path);
//		workbook.write(fo);
//		workbook.close();
//		fo.close();
//	}
//
//
//	public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
//	{
//		fi=new FileInputStream(path);
//		workbook=new XSSFWorkbook(fi);
//		sheet=workbook.getSheet(sheetName);
//		row=sheet.getRow(rownum);
//		if(row == null) row = sheet.createRow(rownum);
//
//		cell=row.getCell(colnum);
//		if(cell == null) cell = row.createCell(colnum);
//		style=workbook.createCellStyle();
//
//		style.setFillForegroundColor(IndexedColors.RED.getIndex());
//		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//
//		cell.setCellStyle(style);	
//		fi.close();
//		fo = new FileOutputStream(path); 
//		workbook.write(fo);
//		workbook.close();
//
//		fo.close();
//	}
//
//}
//
//