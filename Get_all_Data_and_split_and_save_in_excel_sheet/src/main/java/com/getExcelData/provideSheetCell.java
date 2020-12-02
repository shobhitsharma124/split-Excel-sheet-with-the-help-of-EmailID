package com.getExcelData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class provideSheetCell {
	
	public void writeInTextFile(String str) {
		try {
		      FileWriter myWriter = new FileWriter("D:\\New folder (7)\\filename.txt");
		      myWriter.write(str);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	

	public static void main(String[] args) throws Exception {
		  
				//GetExcelSheetData gesd = new GetExcelSheetData();
			String tem = null;
			String Str = null;
			int i = 0;
			FileWriter myWriter = new FileWriter("D:\\New folder (7)\\filename.txt");
			File excelfile = new File("D:\\New folder (7)\\CatalogCouseDetails_with_Tags_update.Xlsx");
			FileInputStream fis = new FileInputStream(excelfile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext()) {
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				i=i+1;
				//while(cellIterator.hasNext()) {
				if(row.getCell(5).toString().contentEquals("#N/A")||row.getCell(5).toString().contentEquals("Not Available")||row.getCell(5).toString().contentEquals("Module Count")||row.getCell(5).toString().contentEquals("Not Applicable")) {
					myWriter.write(i+" $ "+"#N/A or Not Available");
					myWriter.write("\r\n");
					//System.out.println(i+" $ "+"#N/A or Not Available");
					continue;
				}
					Cell cell = cellIterator.next();
					tem = row.getCell(0).toString().replaceAll("[^a-zA-Z0-9\\s]", "");
					tem = tem.replaceAll(" as", "");
					tem = tem.replaceAll(" at", "");
					tem = tem.replaceAll(" but", "");
					tem = tem.replaceAll(" by", "");
					tem = tem.replaceAll(" for", "");
					tem = tem.replaceAll(" from", "");
					tem = tem.replaceAll(" in ", "");
					tem = tem.replaceAll(" of", "");
					tem = tem.replaceAll(" to", "");
					tem = tem.replaceAll(" with", "");
					//row.getCell(5).toString().replaceAll(".0", "");
					tem = tem.replaceAll("  ", " ");
					String []str = tem.split(" ");
					
					myWriter.write(i+" $ "+row.getCell(0).toString()+" $ "+row.getCell(5).toString()+" $ ");
					//System.out.print(i+" $ "+row.getCell(0).toString()+" $ "+row.getCell(5).toString()+" $ ");
					for(String s:str) {
						char [] c = s.toCharArray();
						myWriter.write(c[0]);
						//System.out.print(c[0]);	
					}
					String []s = row.getCell(5).toString().split("\\.");
					myWriter.write("_001-"+s[0]);
					//System.out.print("_001-"+s[0]);
					myWriter.write("\r\n");
				//}
				System.out.println();
			}
			myWriter.close();
			workbook.close();
			fis.close();
	}
}
