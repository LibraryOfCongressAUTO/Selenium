package UnitTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Methods.CommonMethods;
import Util.Base;

public class Publisher_Interface_Unit extends Base {

	static String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
	static String PUBLISHERTITLE = "CIP Publisher Login - CIP Publisher Portal";
	static CommonMethods CM = new CommonMethods();

	@Test(priority = 0)
	public void Verify_Publisher_URL() {

		driver.get(PUBLISHERURL);
		System.out.println("Expected URL is : " + PUBLISHERURL);
		System.out.println("Actual URL is : " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), PUBLISHERURL);
		driver.quit();
	}

	@Test(priority = 1)
	public void Verify_Publisher_Title() {
		driver.get(PUBLISHERURL);
		System.out.println("Expected Title is : " + PUBLISHERTITLE);
		System.out.println("Actual Title is : " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), PUBLISHERTITLE);
		driver.quit();
	}

	@Test(priority = 2)
	public void Positive_Publisher_Login() throws Throwable {
		CM.publisherLogin();
		
		String cipRequestData = driver.findElement(By.xpath("(//A[@ng-href='?id=request_cip']/*)[2]")).getText();
		Assert.assertEquals(cipRequestData, "CIP Request Data");
		driver.quit();
	}

	@Test(priority = 3)
	public void Negative_Publisher_Login() throws Throwable {
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Test");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Example");	
		Thread.sleep(500);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		
		String ErrorMessage = driver.findElement(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']"))
				.getText();
		// if error message does not pop-up, fails test
		Assert.assertTrue(ErrorMessage.contains("invalid"));
		driver.quit();
	}

	@Test(priority = 4)
	public void Verify_New_Publisher_Button_Is_Enabled() throws Throwable {
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//A[@href='pub?id=pub_registration']")).click();
		Thread.sleep(500);
		String welcomeToPCAT = driver.findElement(By.xpath("(//H3[text()='Welcome to P-CAT'])")).getText();
		// if error message does not pop-up, fails test
		Assert.assertTrue(welcomeToPCAT.equals("Welcome to P-CAT"));
		driver.quit();
	}
	
	@Test(priority = 5)
	public void Verify_Publisher_LogOut() throws Throwable {
		CM.publisherLogin();
		CM.logout();	
		driver.quit();

	}

}
