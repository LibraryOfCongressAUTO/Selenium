package Methods;

import org.openqa.selenium.By;

import Util.Base;

public class CommonMethods extends Base {


	String UserName = "Loc1.Tester1";
	String Password = "LocTester@123!";

	public void publisherLogin() throws Throwable {
		String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys(UserName);
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys(Password);
		Thread.sleep(500);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();

	}

	public void authorLogin() throws Throwable {
		String AUTHORURL = "https://locexternaldev.service-now.com/auth";
		driver.get(AUTHORURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys(UserName);
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys(Password);
		Thread.sleep(500);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
	}

	public void logout() {
		driver.findElement(By.xpath("//*[@class='dropdown-toggle']")).click();
		driver.findElement(By.xpath("(//*[@role='menuitem'])[2]")).click();
	}
}
