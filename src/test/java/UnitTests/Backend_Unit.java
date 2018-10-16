package UnitTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Methods.CommonMethods;
import Util.Base;

public class Backend_Unit extends Base {

	
	static String PUBLISHERURL = "https://locexternaltest.service-now.com";
	static CommonMethods CM = new CommonMethods();
	
	
	public void login(String username, String password) throws Throwable {
		
		Thread.sleep(3000);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//INPUT[@id='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//BUTTON[@id='sysverb_login']")).click();
		System.out.println(username + " has logged in successfully");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//BUTTON[@id='user_info_dropdown']")).click();
		driver.findElement(By.xpath("//A[@href='logout.do'][text()='Logout']")).click();
	}
	
	
	@Test(priority = 1)
	public void LiaisonGS8_Login() throws Throwable {
		
		
		driver.get(PUBLISHERURL);
		
		login("liaison.normal1", "liaison.normal1");
		login("liaison.normal2", "liaison.normal2");
		login("liaison.normal3", "liaison.normal3");
		login("liaison.normal4", "liaison.normal4");
		driver.quit();
		
	}
	
	@Test(priority = 2)
	public void LiaisonGS9_Login() throws Throwable {
		
		
		driver.get(PUBLISHERURL);
		
		login("liaison.GS91", "liaison.GS91");
		driver.quit();
		
	}
	
	@Test(priority = 4)
		public void Liaison_Team_Lead_Login() throws Throwable {
			
			
			driver.get(PUBLISHERURL);
			
			login("liaison.teamlead1", "liaison.teamlead1");
			driver.quit();
			
		}
}
