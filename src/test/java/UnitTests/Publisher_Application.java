package UnitTests;


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

	
}
