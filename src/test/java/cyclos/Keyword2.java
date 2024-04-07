package cyclos;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Keyword2 {

	@Test
    public void testKeywordActions() throws Exception
	{
		Library2 L=new Library2();
		String Res=null;
		String kpath="C:\\Users\\sathe\\OneDrive\\Desktop\\keyword2.xlsx";
		FileInputStream fi=new FileInputStream(kpath);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet("TestCase");
		XSSFSheet ws1=wb.getSheet("TestSteps");

		int tcRc=ws.getLastRowNum();
		//System.out.println(tcRc);
		int tsRc=ws1.getLastRowNum();		 
		//System.out.println(tsRc);

		for(int i=1;i<=tcRc;i++)
		{
			String Exp=ws.getRow(i).getCell(2).getStringCellValue();
			if(Exp.equalsIgnoreCase("Y"))
			{
				String tcId=ws.getRow(i).getCell(0).getStringCellValue();

				for(int j=1;j<=tsRc;j++)
				{
					String tsId=ws1.getRow(j).getCell(0).getStringCellValue();
					if(tsId.equalsIgnoreCase(tcId))
					{
						System.out.println(tcId);
						String Key=ws1.getRow(j).getCell(3).getStringCellValue();
						System.out.println(Key);

						switch(Key)
						{
						case "ropen":
							L.openapp();
							break;
						case "rlogin":
							L.Login("demo","1234");
							break;
						case "rcontact":
							L.contacts();
							break;
						case "rclose":
							L.close();
							break;
						case "rpay":
							L.PayUser();
							break;
						default:
							System.out.println("enter proper key");
							break;

						}
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
		FileOutputStream fo=new FileOutputStream(kpath);
		wb.write(fo);
		wb.close();

	}

}
