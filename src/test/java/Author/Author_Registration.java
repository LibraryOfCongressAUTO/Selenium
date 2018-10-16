package Author;

import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Util.Base;

public class Author_Registration extends Base {

	static private String AUTHORURL = "https://locexternaltest.service-now.com/auth";
	String chars = "abcdefghijklmnopqrstuvwxyz";
	Random rnd = new Random();
	char c = chars.charAt(rnd.nextInt(chars.length()));
	LocalDate now = LocalDate.now();

	@Test
	private void NewPublisherRegistration() throws Throwable {

		String txt = "authorlccn" + now + c + "@gmail.com";

		System.out.println("Beginning of New Author Registration");
		driver.get(AUTHORURL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// register a new author account
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@href='auth?id=auth_registration']")).click();
		driver.findElement(By.xpath("//*[@ng-model='c.first_name']")).sendKeys("authorAUT");
		driver.findElement(By.xpath("//*[@ng-model='c.last_name']")).sendKeys("Test");
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(txt);
		driver.findElement(By.xpath("//*[@ng-model='c.password']")).sendKeys("11111111");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(2000);

		if (driver.findElements(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']")).size() > 0) {
			System.out.println("The E-Mail " + txt + " has already been used. User did NOT create account");
		} else {
			System.out.println("Successfully created Author account with E-Mail : " + txt);
		}

	}
}
