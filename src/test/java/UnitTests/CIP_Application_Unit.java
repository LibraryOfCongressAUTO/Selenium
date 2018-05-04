package UnitTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Methods.CommonMethods;
import Util.Base;

public class CIP_Application_Unit extends Base {

	static String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
	static CommonMethods CM = new CommonMethods();

	@Test(priority = 0)
	public void Navigate_To_CIP_Application() throws Throwable {
		CM.publisherLogin();
		Thread.sleep(1000);
		String cipRequestData = driver.findElement(By.xpath("(//*[@ng-href='?id=request_cip'])/*[2]")).getText();
		Assert.assertEquals(cipRequestData, "CIP Request Data");

		driver.findElement(By.xpath("(//*[@ng-href='?id=request_cip'])/*[1]")).click();

		Thread.sleep(1000);

		String Header_Verify = driver.findElement(By.xpath("(//*[@class='page-header text-center']/*)[1]")).getText();
		Assert.assertEquals(Header_Verify, "Request CIP Data");
		driver.quit();

	}

	@Test(priority = 2)
	public void verify_CIP_WireFrames_Are_Enabled() throws Throwable {

		CM.publisherLogin();
		driver.findElement(By.xpath("(//*[@ng-href='?id=request_cip'])/*[1]")).click();

		Thread.sleep(1000);

		List<WebElement> numberOfFrames = new ArrayList<>();
		numberOfFrames = driver.findElements(By.xpath("((//*[@class='page-header text-center']/*)/*/*)"));
		System.out.println("The # of Wireframes Present is: " + numberOfFrames.size());
		Assert.assertEquals(numberOfFrames.size(), 7);

		driver.quit();
	}
//	@Test(priority = 3)
//	public void verify_Seven_WireFrames_Are_Present() throws Throwable{
//		Thread.sleep(5000);
//	}
//	
//	@Test(priority = 4)
//	public void complete_General_Information_Section() throws Throwable {
//		Thread.sleep(20000);
//	}
//	
//	@Test(priority = 5)
//	public void complete_Contributor_Information_Section() throws Throwable {
//		Thread.sleep(18000);
//	}
//	
//	@Test(priority = 6)
//	public void complete_Title_Page_Section() throws Throwable {
//		Thread.sleep(23000);
//	}
//	
//	@Test(priority = 7)
//	public void complete_Series_And_Volume_Information_Section() throws Throwable  {
//		Thread.sleep(20000);
//	}
//	
//	@Test(priority = 8)
//	public void complete_Galley_Section() throws Throwable {
//		Thread.sleep(10000);
//	}
//	
//	@Test(priority = 9)
//	public void complete_Additional_Info_And_Summary_Section() throws Throwable {
//		Thread.sleep(11000);
//	}
//	
//	@Test(priority = 10)
//	public void navigate_To_Confirmation_Section() throws Throwable {
//		Thread.sleep(9000);
//	}
}
