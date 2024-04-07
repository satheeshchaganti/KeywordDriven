package cyclos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KeywordWithExcel extends Library 
{
	
	public static void main(String[] args) throws Exception {
		Library LB=new Library();
		//declaring variable Res
		String Res=null;
		// Keyword test data path
		String Kpath="C:\\Users\\sathe\\OneDrive\\Desktop\\KeyWord.xlsx";
				
		FileInputStream fi=new FileInputStream(Kpath);
		//creating workbook
		XSSFWorkbook wb=new XSSFWorkbook(fi);

		//sheet
		XSSFSheet ws=wb.getSheet("TestCase");
		XSSFSheet ws1=wb.getSheet("TestSteps");
		//XSSFSheet ws2=wb.getSheet("TestData");

		//Row count
		int tcRc=ws.getLastRowNum();
		System.out.println(tcRc);
		int tsRc=ws1.getLastRowNum();
		System.out.println(tsRc);

		//test cases

		for (int i=1;i<=tcRc;i++)
		{
			String Exe=ws.getRow(i).getCell(2).getStringCellValue();
			//System.out.println(Exe);
			Thread.sleep(1000);
			if (Exe.equalsIgnoreCase("Y"))
			{
				String tcId=ws.getRow(i).getCell(0).getStringCellValue();
				System.out.println(tcId);
				Thread.sleep(1000);
				//test steps

				for (int j=1;j<=tsRc;j++) 
				{
					String tsTcid=ws1.getRow(j).getCell(0).getStringCellValue();
					//System.out.println(tsTcid);
					Thread.sleep(1000);
					if (tcId.equalsIgnoreCase(tsTcid))
					{
						String key=ws1.getRow(j).getCell(3).getStringCellValue();
						System.out.println(key);
						Thread.sleep(1000);
						switch (key) 
						{
						case "rLaunch":
							Res=LB.openApp();
							break;
						case "rLogin":
							FileInputStream fis=new FileInputStream("C:\\Users\\sathe\\OneDrive\\Desktop\\Admin.xlsx");
							XSSFWorkbook WB=new XSSFWorkbook(fis);
							XSSFSheet WS=WB.getSheet("Sheet1");
							System.out.println(WS.getLastRowNum());
							
							for(int k=1;k<=WS.getLastRowNum();k++)
							{
								String uname=WS.getRow(k).getCell(0).getStringCellValue();
								String pword=WS.getRow(k).getCell(1).getStringCellValue();
								LB.Login(uname,pword);
							}
							break;
						case "rPay":
							Res=LB.PayUser();
							break;
						case "rPayUsers":
							Res=LB.PayDetails();
							break;
						case "rClose":
							Res=LB.close();
							break;
						case "rcontacts":
							Res=LB.Contacts();
							break;

						default:
							System.out.println("Pass a Valid Keyword");
							break;
						}

						//result updation in test steps sheet

						ws1.getRow(j).createCell(4).setCellValue(Res);

						//result updation in test case sheet

						if (ws1.getRow(j).getCell(4).getStringCellValue().equalsIgnoreCase("Pass"))
						{
							ws.getRow(i).createCell(3).setCellValue(Res);
						}
						else
						{
							ws.getRow(i).createCell(3).setCellValue("Fail");
						}
					}	
				}
			}
			else
			{
				ws.getRow(i).createCell(3).setCellValue("BLOCKED");
			}
		}
		FileOutputStream fo=new FileOutputStream(Kpath);
		wb.write(fo);
		wb.close();


	}

}
