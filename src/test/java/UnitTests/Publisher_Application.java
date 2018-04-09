package UnitTests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Util.Base;

public class Publisher_Application extends Base {

	static String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
	static String PUBLISHERTITLE = "CIP Publisher Login - CIP Publisher Portal";
		
	
	@Test(priority = 0)
	public void Verify_Publisher_URL() {
		
		driver.get(PUBLISHERURL);
		System.out.println("Expected URL is : " + PUBLISHERURL);
		System.out.println("Actual URL is : " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), PUBLISHERURL);
	}

	@Test(priority = 1)
	public void Verify_Publisher_Title() {
		driver.get(PUBLISHERURL);
		System.out.println("Expected Title is : " + PUBLISHERTITLE);
		System.out.println("Actual Title is : " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), PUBLISHERTITLE);
	}
	
	@Test(priority = 2)
	public void Positive_Publisher_Login() throws Throwable {
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Loc1.Tester1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("LocTester@123!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String upgradeCIP = driver.findElement(By.xpath("(//A[@ng-href='?id=request_cip']/*)[2]")).getText();
		Assert.assertEquals(upgradeCIP, "Upgrade to CIP");
		driver.quit();
	}

	@Test(priority = 3)
	public void Negative_Publisher_Login() throws Throwable {
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Test");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Example");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String ErrorMessage = driver.findElement(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']"))
				.getText();
		//if error message does not pop-up, fails test
		Assert.assertTrue(ErrorMessage.contains("invalid"));
		driver.quit();
	}
	
	
	@Test(priority = 4)
	public void Verify_New_Publisher_Button_Is_Enabled() throws Throwable {
		driver.get(PUBLISHERURL);
		driver.findElement(By.xpath("//A[@href='pub?id=pub_registration']")).click();
		Thread.sleep(2000);
		String welcomeToPCAT = driver.findElement(By.xpath("(//H3[text()='Welcome to P-CAT'])"))
				.getText();
		//if error message does not pop-up, fails test
		Assert.assertTrue(welcomeToPCAT.equals("Welcome to P-CAT"));
		driver.quit();
	}


	
}
