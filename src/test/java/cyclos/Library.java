package cyclos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library {
	public static WebDriver driver;
	String Exp,Act;
	
	public String openApp() throws Exception
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
			System.out.println("Launch succesful");
			return "Pass"; 
		}
		else
		{
			System.out.println("launch failed");
		return "Fail";
		}
	}
	public String Login(String Uname,String Pword)
	{
		driver.findElement(By.id("login-link")).click();
		WebElement u=driver.findElement(By.xpath("//input[@type='text']"));
		u.clear();
		u.sendKeys(Uname);
		WebElement p=driver.findElement(By.xpath("//input[@type='password']"));
		p.clear();
		p.sendKeys(Pword);

		driver.findElement(By.xpath("//span[contains(.,'Submit')]")).click();
		Exp="Cyclos";
		Act=driver.getTitle();
	if(Act.contains(Exp))
	{
		System.out.println("Login Succesful");
		return "Pass";
	}
	else
	{
		System.out.println("Login Failed");
		return "Fail";
	}

	}
	public String PayUser() throws Exception
	{
		Thread.sleep(3000);

		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Banking")).click();
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Payment to user")).click();
		Exp="Payment to";
		Thread.sleep(6000);

		Act=driver.findElement(By.xpath("//*[text()=' Payment to user ']")).getText();
		if(Act.contains(Exp))
		{
			System.out.println("Navigated to Pay Users Tab");
			return "Pass";
		}
		else
		{
			System.out.println("can't go to pay users tab");
			return "Fail";
		}
	}
	public String PayDetails() throws Exception
	{

		driver.findElement(By.cssSelector(".btn .bi")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Hotel Oasis')]")).click();
		driver.findElement(By.xpath("//input")).sendKeys("500");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.text-left")).click();
		driver.findElement(By.linkText("Pay now")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn > span")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("(//*[@type='button'])[2]")).click();
        Exp="You have exceeded";
		Thread.sleep(2000);
        Act=driver.findElement(By.xpath("//*[@class='notification-message']")).getText();
        if(Act.contains(Exp))
        {
        	System.out.println("The payment is succesful");
        	return "Pass";
        }
        else
        {
        	System.out.println("Transaction is Failed");
        	return "Fail";
        }
	}
	public String close()
	{
		driver.quit();
		return "Pass";
	}
	public String Contacts() throws Exception
	{ 
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@class='quick-access-icon'])[4]")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("(//*[@class='avatar-container full-size'])[1]")).click();
	    Exp="Contact";
		Thread.sleep(2000);
	    Act=driver.findElement(By.xpath(" //*[text()=' Contact list ']")).getText();
	    if(Act.contains(Exp))
	    {
	    	System.out.println("contacts fetched succesfully");
	    	return "Pass";
	    }
	    else
	    {
	    System.out.println("No contacts available");
	    return "Fail";
	    }
	}
	public void Logout()
	{
		driver.findElement(By.id("logout-trigger")).click();

	}

  
}
