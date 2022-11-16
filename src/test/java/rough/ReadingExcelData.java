package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExcelData {
	public static void main(String[] args) {
		
		
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\resources\\testdata\\BankManagerSuit.xlsx");
				int rows =excel.getRowCount(Constants.dataSheet);
				System.out.println("Total rows are : "+rows);
				
				
			//	Find the test Case Start row
				String testName ="AddCustomerTest";
				
				int testCaseNum = 1;
				
				for(testCaseNum=1;testCaseNum<rows;testCaseNum++) {
					
					String testCaseName = excel.getCellData(Constants.dataSheet, 0, testCaseNum);
					
					if(testCaseName.equalsIgnoreCase(testName)) {
						break;
					}
				}
				System.out.println("Test Case Start From :  "+testCaseNum);
				
				// Checking total rows in test Case
				
				int dataStartrowNum = testCaseNum+2;
				
				int testStartRow = 0;
				
				while(!excel.getCellData(Constants.dataSheet, 0,dataStartrowNum+testStartRow).equals("")) {
					testStartRow++;
				}
				System.out.println("Total test row :  "+testStartRow);
				
				// Checking total total column in test Case
				int colStartColumnNum = testCaseNum+ 1;
				int testCol = 0;
				while(!excel.getCellData(Constants.dataSheet, testCol, colStartColumnNum).equals("")) {
					testCol++;
				}
		
				System.out.println("Total Test data col is :  "+testCol);
				
				//Print All the data in Test Case
				
				for(int rNum=dataStartrowNum;rNum<dataStartrowNum+testStartRow;rNum++) {
					for(int colNum=0;colNum<testCol;colNum++) {
						System.out.println(excel.getCellData(Constants.dataSheet, colNum, rNum));
					}
				}
	}
	
	
}
