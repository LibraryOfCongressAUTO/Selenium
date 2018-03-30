package UnitTests;

import org.testng.annotations.Test;
import Util.Base;

public class Author_Application extends Base {

	static String AUTHORURL = "https://locexternaldev.service-now.com/auth";

	@Test(priority = 0)
	public void Navigate_To_Author_Portal() {
		driver.get(AUTHORURL);
		System.out.println(driver.getCurrentUrl());
		System.out.println("b");
	}


	@Test(priority = 1)
	public void Positive_Author_Login() {
		System.out.println("d");
	}

	
}
