package Methods;

import org.openqa.selenium.By;

import Util.Base;

public class CommonMethods extends Base {


	String UserNameDEV = "Loc1.Tester1";
	String PasswordDEV = "LocTester@123!";

	public void publisherLoginDEV() throws Throwable {
		String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys(UserNameDEV);
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys(PasswordDEV);
		Thread.sleep(500);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();

	}

	public void authorLoginDEV() throws Throwable {
		String AUTHORURL = "https://locexternaldev.service-now.com/auth";
		driver.get(AUTHORURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys(UserNameDEV);
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys(PasswordDEV);
		Thread.sleep(500);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
	}

	public void logout() {
		driver.findElement(By.xpath("//*[@class='dropdown-toggle']")).click();
		driver.findElement(By.xpath("(//*[@role='menuitem'])[2]")).click();
	}
}
