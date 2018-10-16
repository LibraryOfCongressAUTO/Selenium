package Publisher;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Util.Base;

public class Change_Request extends Base {

	static String PUBLISHERURL = "https://locexternaltest.service-now.com/pub";

	public void login() throws Throwable {

		driver.get(PUBLISHERURL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// register a new publisher account
		Thread.sleep(1000);

		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Publisher.Test1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Publisher.test1!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String changeRequestButton = driver
				.findElement(By.xpath("(//*[@ng-href='pub?id=sc_cat_item&sys_id=9a2de5d9dbe717003e0cf3421f96195f']/*)[2]")).getText();
		System.out.println(changeRequestButton);
		driver
		.findElement(By.xpath("(//*[@ng-href='pub?id=sc_cat_item&sys_id=9a2de5d9dbe717003e0cf3421f96195f']/*)[2]")).click();
		
		//DIV[@class='wrapper-md']
		String changeR = driver.findElement(By.xpath("(//DIV[@class='wrapper-md']/*)[1]")).getText();
		Assert.assertEquals(changeR, "Change Request");
	}

	@Test
	public void changeRequest() throws Throwable {

		login();

	}
}
