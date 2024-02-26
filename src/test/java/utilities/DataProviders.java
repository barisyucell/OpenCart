package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	@DataProvider(name="LoginData")
	public Object[][] getData() throws IOException
	{
		String path = ".\\testData\\OpenCart_LoginData.xlsx";

		ExcelUtility excelUtility = new ExcelUtility(path);
		
		int totalRows = excelUtility.getRowCount("Sheet1");
		int totalCells = excelUtility.getCellCount("Sheet1",1);
				
		String loginData [][] = new String [totalRows] [totalCells];
		
		for (int i=1; i<=totalRows; i++)
		{
			for (int j=0; j<totalCells; j++)
			{
				loginData [i-1] [j] = excelUtility.getCellData("Sheet1", i, j);
			}
		}

		return loginData;
	}

}