package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.POILogFactory;

public class ExcelUtils {
	XSSFSheet wSheet = null;
	XSSFWorkbook wBook;
	XSSFCell cell;
	XSSFRow row;
	String cellData = null;
	String workingDir = System.getProperty("user.dir");
	//public Logger log = Logger.getLogger(ExcelUtils.class.getName());
	
	public Object[][] dataSource(String pFilePath, String pSheetName, int pStartRow, int pStartColumn){
		String[][] dataArray = null;
		try {
			FileInputStream file = new FileInputStream(workingDir+pFilePath);
			System.out.println("our file is "+ workingDir+pFilePath);
			// Access the required test data sheet
			wBook = new XSSFWorkbook(file);
			wSheet = wBook.getSheet(pSheetName);
			
			
			//MY CODE
			cell = wSheet.getRow(pStartRow).getCell(pStartColumn);
			System.out.println("MY CELL is "+ cell);
			
			
			
			int row = 0;
			int column = 0;
		
			
			int rowCount = wSheet.getLastRowNum()+1;
		//	log.info("Total number of Rows => " + rowCount);
			
			int columnCount = wSheet.getRow(0).getLastCellNum();
		//	log.info("Total number of Columns => "+columnCount);
			
			dataArray = new String[rowCount][columnCount];
			System.out.println("My total row is "+ rowCount);
			System.out.println("My total column  is "+ columnCount);
			for(int rowIterate = pStartRow;rowIterate < rowCount;rowIterate++,row++) {
				column = 0;
				//log.info("");
				System.out.println("");
				for(int columnIterate = pStartColumn;columnIterate < columnCount;columnIterate++,column++) {
					if(columnIterate > 0) {
						//log.info("");
						System.out.println("");
					}
					if(getCellData(rowIterate,columnIterate).isEmpty() == true) {
						dataArray[row][column] = null;
					}
					else {
						dataArray[row][column] = getCellData(rowIterate,columnIterate);
					}
					//log.info(dataArray[row][column]);
					System.out.println(dataArray[row][column]);
				}
			}
		}
		catch(Exception e) {
		//	log.info
			System.out.println("------ OBSERVED EXCEPTION ------- : " + e +
					" in method dataSource(String pFilePath, String pSheetName, int pStartRow, int pStartColumn)");
		}
		return (dataArray);
		
		
	}
	/*
	 * Purpose: This method reads the test data from Excel cell 
	 * Pre - condition: Give rowNumber and ColumnNumber to get Cell Data 
	 * Input Parameters: We are passing row number and column number as parameters
	 * Output: Cell data for given rowNumber and columnNumber
	 * 
	 */
	public String getCellData(int pRowNum, int pColNum) {
		try {
			cell = wSheet.getRow(pRowNum).getCell(pColNum);
			DataFormatter format = new DataFormatter();
			cellData = format.formatCellValue(cell);
			//CellData = CellgetStringCellValue()
		} catch (Exception e) {
		//	log.info
			System.out.println("--------OBSERVED Exception ------ : " + e + 
					" in method getCellData(int pRowNum, int pColNum)");
			
		}
		return cellData;
	}
	
	/*
	 * Purpose; This method takes row number as a parameter and returns the data of a given row number
	 * Pre-condition: Give row numbe rto get data for a given row
	 * Input parameters: we are passing row number as parameters
	 * Output: Cell Data for a given RowNumber
	 */
	
	public XSSFRow getRowData(int pRowNum) {
		try {
			row = wSheet.getRow(pRowNum);
		}
		catch (Exception e){
		//	log.info
			System.out.println("------- OBSERVED EXCEPTION --------- :" + e + 
					"in method getRowData(int pRowNum)");
			e.printStackTrace();
		}
		return row;
	}
	
	/*
	 * Purpose; This method gets excel file, row and column number and set  a value to that cell
	 * Pre-condition: Give file path, row number, column number and the value to get data inserted in a given cell
	 * Input parameters: we are passing file path, row number, column number, input value as parameters
	 * Output: Data for a given cell will be inserted in the excel file;
	 */
	public void setCellData(String pFilePath, int pRowNum, int pColNum, String pValue) {
		
		try {
		row = wSheet.getRow(pRowNum);
		cell = row.getCell(pColNum);
		
		if(cell == null) {
			cell = row.createCell(pColNum);
			cell.setCellValue(pValue);
		}
		else {
			cell.setCellValue(pValue);
		}
		
			FileOutputStream fileOut = new FileOutputStream(workingDir + pFilePath);
			wBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			
			
		} catch (Exception e) {
		//	log.info
			System.out.println("------- OBSERVED -------- : " + e +
					"in method setCellData(String pFilePath, int pRowNum, int pColNum, String pValue)");
			e.printStackTrace();
		}
		
		
	}

}
