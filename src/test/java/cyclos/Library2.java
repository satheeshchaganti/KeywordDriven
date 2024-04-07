package cyclos;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library2 
{
	public static WebDriver driver;
	String Exp,Act;

	public String openapp() throws Exception
	{

		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.cyclos.org/ui/home");

		Exp="Cyclos website";

		Thread.sleep(3000);
		Act=driver.findElement(By.xpath("(//a[@class='btn btn-primary'])[1]")).getText();
		if(Act.contains(Exp))
		{
			System.out.println("Launch Successful");
			return "pass";
		}
		else
		{
			System.out.println("Launch Failed");
			return "fail";
		}
	}
	public String Login(String uname,String pwd)
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.id("login-link")).click();
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys(uname);
		driver.findElement(By.xpath("//*[@type='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("(//*[@type='button'])[2]")).click();
		Exp="Cyclos";
		Act=driver.getTitle();

		if(Act.contains(Exp))
		{
			System.out.println("Login Successful");
			return "pass";
		}
		else
		{
			System.out.println("Login failed");
			return "fail";
		}
	}
	public String PayUser() throws  Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.findElement(By.xpath("(//*[@class='menu-text'])[34]")).click();
		driver.findElement(By.xpath("(//*[@class='nav-item-text'])[2]")).click();
		driver.findElement(By.xpath("(//*[@type='button'])[2]")).click();
		driver.findElement(By.partialLinkText("Hotel Oasis")).click();
		driver.findElement(By.cssSelector("input.ng-valid")).sendKeys("500");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button.d-flex")).click();
		driver.findElement(By.xpath("(//*[@type='button'])[2]")).click();
		Exp="Transaction";
		Act=driver.findElement(By.xpath("(//*[@class='label-value-label'])[7]")).getText();

		if(Act.contains(Exp))
		{
			System.out.println("payment successful");
			return "pass";
		}
		else
		{
			System.out.println("payment declined");
			return "fail";
		}
	}
	public String contacts() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(3000);

		driver.findElement(By.xpath("(//*[@class='quick-access-text'])[4]")).click();
		Exp="Contact list";
		Act=driver.findElement(By.cssSelector("div.title-text")).getText();
		if(Act.contains(Exp))
		{
			System.out.println("contacts fetched successfully");
			return "pass";
		}
		else
		{
			System.out.println("contacts are hidden");
			return "fail";
		}
	}
	public String close()
	{
		driver.quit();
		return "pass";
	}

}
