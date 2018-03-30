package UnitTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Util.Base;

public class Author_Application extends Base {

	static String AUTHORURL = "https://locexternaldev.service-now.com/auth";
	static String AUTHORTITLE = "CIP Author Login - CIP Author Portal";

	// @Test(priority = 0)
	public void Verify_Author_URL() {

		driver.get(AUTHORURL);
		System.out.println("Expected URL is : " + AUTHORURL);
		System.out.println("Actual URL is : " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), AUTHORURL);
	}

	// @Test(priority = 1)
	public void Verify_Author_Title() {
		driver.get(AUTHORURL);
		System.out.println("Expected Title is : " + AUTHORTITLE);
		System.out.println("Actual Title is : " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), AUTHORTITLE);
	}

	// @Test(priority = 2)
	public void Positive_Author_Login() throws Throwable {
		driver.get(AUTHORURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Loc1.Tester1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("LocTester@123!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String requestLCCN = driver.findElement(By.xpath("(//*[@href='?id=request_lccn']/*)[2]")).getText();
		Assert.assertEquals(requestLCCN, "Request Library of Congress Control Number");
	}

	@Test(priority = 3)
	public void Negative_Author_Login() throws Throwable {
		driver.get(AUTHORURL);
		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Test");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Example");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String ErrorMessage = driver.findElement(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']"))
				.getText();
		if (ErrorMessage.contains("kjhgfd") == true) {
			System.out.println("Yes");
		} else {
			System.out.println("No");

		}
	}
}
