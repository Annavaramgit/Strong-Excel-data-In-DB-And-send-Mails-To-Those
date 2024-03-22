package com.exceltodb.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.exceltodb.enitity.Customer;

public class Helper {
	
	public static boolean checkExcelFormat(MultipartFile file)
	{
		String contentType = file.getContentType();
		
		//it checks given file is excel format or not
		
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true; 
		}
		else 
		{
			return false; 
		}
	}

	public static List<Customer> covertExcelDataToCustomerData(InputStream inputStream) //throws IOException
	{
		
		 List<Customer> customers = new ArrayList<>();
		 try {
		 XSSFWorkbook workbook =new XSSFWorkbook(inputStream);
		 
		 //the customes is the sheet name.this method reads that sheet
		 XSSFSheet sheet = workbook.getSheet("customer");
		 
		 int rowIndex = 0;
		 
		 //iterate rows in the sheet
		 for(Row row:sheet) {
			 
			 //the zero th row is titles or headings so we ignoring those
			 if(rowIndex == 0)
			 {
				 rowIndex++;
				 continue;
			 }
			 
			 //it iterates all cells in the row
			 
			 Iterator<Cell> cellIterator = row.iterator();
			 int cellIndex = 0;
			 Customer c = new Customer();
			 
			 while(cellIterator.hasNext())
			 {
				 Cell cell = cellIterator.next();
				 switch(cellIndex)
				 {
				 
	 //at zero index cell it take numeric value and set customer_id 
				 case 0:
					c.setCustomer_id((long)cell.getNumericCellValue());
					break;
					
	//at one index cell it take numeric value and set customer_name 
				 case 1:
					
					 c.setCustomer_name(cell.getStringCellValue());
				     break;
				     
				     
	//at two index cell it take numeric value and set customer_mail 
				 case 2:
					 c.setCustomer_mail(cell.getStringCellValue());
					 break;
	 //at three index cell it take numeric value and set customer_adress 			 
				 case 3:
					 
					 c.setCustomer_adress(cell.getStringCellValue());
					 break;
					 
					 
			     default: 
			    	 break;
				 }
				 //icreases cell index value
				 cellIndex++;
			 }
			 customers.add(c);
		 }
		 
		 
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return customers;
	}
	
	
}
