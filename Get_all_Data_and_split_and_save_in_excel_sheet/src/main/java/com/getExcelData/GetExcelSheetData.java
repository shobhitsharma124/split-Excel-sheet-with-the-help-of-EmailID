package com.getExcelData;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelSheetData {
	public String createExcelSheet(String str) throws Exception {
		Workbook wb = new HSSFWorkbook(); 
        OutputStream fileOut = new FileOutputStream("D:\\test\\"+str+".xlsx");
        
        return "D:\\test\\"+str+".xlsx";
	}
	public String createExcelAndPutData( String str,Map<String, Object[]> data) {

		XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet sheet = workbook.createSheet("student Details"); 
        boolean b = false;
        Set<String> keyset = data.keySet(); 
        int rownum = 0; 
        for (String key : keyset) { 
            // this creates a new row in the sheet 
            Row row = sheet.createRow(rownum++); 
            Object[] objArr = data.get(key); 
            int cellnum = 0;
            if(b==false) {
            	String [] Str = {"User","Question Name","Test Name","User_Choice","Correct","Right_Choice","C_ID"};
            	
            	for(String s:Str) {
            		Cell cell = row.createCell(cellnum++);
            		cell.setCellValue(s);	
            	}
            	
            	b=true;
            }else {
            for (Object obj : objArr) { 
                // this line creates a cell in the next column of that row 
                Cell cell = row.createCell(cellnum++); 
                if (obj instanceof String) 
                    cell.setCellValue((String)obj); 
                else if (obj instanceof Integer) 
                    cell.setCellValue((Integer)obj); 
            } 
            }
        } 
        try { 
            // this Writes the workbook gfgcontribute 
            FileOutputStream out = new FileOutputStream(new File("D:\\test\\New folder\\"+str+".xlsx")); 
            workbook.write(out); 
            out.close(); 
            System.out.println("written successfully."); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 

		
		return null;
		
	}

	public static void main(String args[]) throws Exception  
	{  
		GetExcelSheetData gesd = new GetExcelSheetData();
	String str = "a";
	String path = null;
	int i = 0;
	File excelfile = new File("D:\\test\\LeoFoarce Ald.Xlsx");
	FileInputStream fis = new FileInputStream(excelfile);
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIt = sheet.iterator();
	Map<String, Object[]> data = new TreeMap<String, Object[]>();
	while(rowIt.hasNext()) {
		Row row = rowIt.next();
		Iterator<Cell> cellIterator = row.cellIterator();
		if(row.getCell(0).toString().contains(".com")){
			if(!str.equals( row.getCell(0).toString())) {
				if(str.contains(".com")) {
					gesd.createExcelAndPutData(str, data);
					data.clear();
					i=0;
				}
				str = row.getCell(0).toString();
				
			}
			
		}
		i=i+1;
		String []s =row.getCell(4).toString().split("\\.");
		String SS = row.getCell(6).toString().replaceAll("\\.","");
		data.put(String.valueOf(i), new Object[]{row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),s[0],row.getCell(5).toString(),SS });
		/*
		 * FileInputStream Fis = new FileInputStream(path); Workbook wb =
		 * WorkbookFactory.create(Fis); Sheet sh = wb.getSheet("shobhit"); Row row1 =
		 * sh.createRow(i); i=i+1;
		 */
		/*
		 * while(cellIterator.hasNext()) { Cell cell = cellIterator.next();
		 * 
		 * System.out.print(cell.toString()+";"); }
		 */
		System.out.println(i);
	}
	workbook.close();
	fis.close();
	}  
}  