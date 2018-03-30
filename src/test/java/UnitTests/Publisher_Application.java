package UnitTests;


import org.testng.annotations.Test;
import Util.Base;

public class Publisher_Application extends Base {

	static String PUBLISHERURL = "https://locexternaldev.service-now.com/pub";
	
	
	
	@Test(priority = 0)
	public void Navigate_To_Publisher_Portal() {
		driver.get(PUBLISHERURL);
		System.out.println(driver.getCurrentUrl());
		System.out.println("b");
	}


	@Test(priority = 1)
	public void Positive_Publisher_Login() {
		System.out.println("d");
	}

	
}
