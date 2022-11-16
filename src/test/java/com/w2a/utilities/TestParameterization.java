package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

	@Test(dataProvider = "getdata")
	public void testdata(Hashtable<String,String> data) {
		
		System.out.println(data.get("RunMode")+ "======="+data.get("firstName")+ "======"+data.get("lastName")+ "======"+data.get("postCode"));

	}

	@DataProvider
	public Object[][] getdata() {
		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\BankManagerSuit.xlsx");
		int rows = excel.getRowCount(Constants.dataSheet);
		System.out.println("Total rows are : " + rows);

		// Find the test Case Start row
		String testName = "AddCustomerTest";

		int testCaseNum = 1;

		for (testCaseNum = 1; testCaseNum < rows; testCaseNum++) {

			String testCaseName = excel.getCellData(Constants.dataSheet, 0, testCaseNum);

			if (testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}
		System.out.println("Test Case Start From :  " + testCaseNum);

		// Checking total rows in test Case

		int dataStartrowNum = testCaseNum + 2;

		int testRow = 0;

		while (!excel.getCellData(Constants.dataSheet, 0, dataStartrowNum + testRow).equals("")) {
			testRow++;
		}
		System.out.println("Total test row :  " + testRow);

		// Checking total total column in test Case
		int colStartColumnNum = testCaseNum + 1;
		int testCol = 0;
		while (!excel.getCellData(Constants.dataSheet, testCol, colStartColumnNum).equals("")) {
			testCol++;
		}

		System.out.println("Total Test data col is :  " + testCol);

		// Print All the data in Test Case

		Object[][] data = new Object[testRow][1];
		int i =0;
		for (int rNum = dataStartrowNum; rNum < dataStartrowNum + testRow; rNum++) {
			Hashtable<String,String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < testCol; colNum++) {
				// System.out.println(excel.getCellData(Constants.dataSheet, colNum, rNum));

			String	testData = excel.getCellData(Constants.dataSheet, colNum, rNum);
			String colName = 	excel.getCellData(Constants.dataSheet, colNum, colStartColumnNum);
			table.put(colName, testData);
			}
			data[i][0]=table;
			i++;
		}
		return data;

	}

}
