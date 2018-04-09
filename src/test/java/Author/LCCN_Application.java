package Author;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Util.Base;

public class LCCN_Application extends Base {

	static String AUTHORURL = "https://locexternaldev.service-now.com/auth";

	public void lccnGeneralInformation() throws Throwable {

		driver.findElement(By.xpath("(//*[@href='?id=request_lccn']/*)[2]")).click();

		// GENERAL INFORMATION (1)

		// first question
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = true']")).click();
		int ErrorMSG1 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']")).size();
		System.out.println("1st Message : "
				+ driver.findElement(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG1, 1);
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = false']")).click();

		// second question
		driver.findElement(By.xpath("(//*[@ng-model='formParams.intendedFor'])[1]")).click();
		int ErrorMSG2 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']")).size();
		System.out.println("2nd Message : "
				+ driver.findElement(By.xpath("//*[@class='alert alert-danger ng-binding ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG2, 1);
		driver.findElement(By.xpath("(//*[@ng-model='formParams.intendedFor'])[2]")).click();

		// third question
		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[1]")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[2]")).click();

		// # of pages
		driver.findElement(By.xpath("(//*[@name='approxNumOfPages'])")).sendKeys("50");

		List<WebElement> languageOptions = new ArrayList<>();
		languageOptions = driver.findElements(By.xpath("//*[@name='languageOfText']/*"));

		System.out.println("Size of Language Options is " + languageOptions.size());

		for (int y = 0; y < languageOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='languageOfText']")).click();
			languageOptions.get(y).click();
			System.out.println("Current Language selected is : " + languageOptions.get(y).getText());
		}
		Assert.assertEquals(languageOptions.size(), 14);

		List<WebElement> monthOptions = new ArrayList<>();
		monthOptions = driver.findElements(By.xpath("//*[@name='month']/*"));

		System.out.println("Size of Language Options is " + monthOptions.size());

		for (int y = 0; y < monthOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='month']")).click();
			monthOptions.get(y).click();
			System.out.println("Current Month selected is : " + monthOptions.get(y).getText());
		}
		Assert.assertEquals(monthOptions.size(), 13);

		List<WebElement> yearOptions = new ArrayList<>();
		yearOptions = driver.findElements(By.xpath("//*[@name='year']/*"));

		System.out.println("Size of Year Options is " + yearOptions.size());

		for (int y = 0; y < yearOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='year']")).click();
			yearOptions.get(y).click();
			System.out.println("Current Year selected is : " + yearOptions.get(y).getText());
		}

		//
		Assert.assertEquals(yearOptions.size(), 12);

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();

		Thread.sleep(2000);

	}

	public void loginLCCN() throws Throwable {

		driver.get(AUTHORURL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// register a new author account
		Thread.sleep(1000);

		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Loc1.Tester1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("LocTester@123!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String requestLCCN = driver.findElement(By.xpath("(//*[@href='?id=request_lccn']/*)[2]")).getText();
		Assert.assertEquals(requestLCCN, "Request Library of Congress Control Number");

	}

	public void contributorInformation() throws Throwable {

		// add individual
		String selectedButton = driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[1]")).getText();
		System.out.println(selectedButton);
		Assert.assertEquals(selectedButton, "Individual");
		driver.findElement(By.xpath("//*[@name='fName']")).sendKeys("FirstName");
		driver.findElement(By.xpath("//*[@ng-model='contributor.middleName']")).sendKeys("MiddleName");
		driver.findElement(By.xpath("//*[@name='lname']")).sendKeys("LastName");
		driver.findElement(By.xpath("//*[@ng-model='contributor.birthMonth']")).sendKeys("01");
		driver.findElement(By.xpath("//*[@ng-model='contributor.birthDay']")).sendKeys("02");
		driver.findElement(By.xpath("//*[@ng-model='contributor.birthYear']")).sendKeys("2000");
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType']/*)[2]")).click();

		WebElement addContributor = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
		addContributor.click();

		// add organization
		driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[2]")).click();

		driver.findElement(By.xpath("//*[@name='orgName']")).sendKeys("OrganizationName");
		driver.findElement(By.xpath("//*[@name='city']")).sendKeys("OrganizationCity");

		driver.findElement(By.xpath("//*[@name='state']")).click();
		driver.findElement(By.xpath("(//*[@name='state']/*)[text()='District of Columbia']")).click();

		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType']/*)[2]")).click();

		addContributor.click();
		
		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
		
		//title page information
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.title']")).sendKeys("Example Title");
		
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.title']")).sendKeys("Example Subtitle");
		
		
		driver.findElement(By.xpath("//*[@name='edition']")).sendKeys("Example Edition");
		
		
		driver.findElement(By.xpath("//*[@name='titlePage.pubName']")).sendKeys("Example Title");		
		
		driver.findElement(By.xpath("//*[@name='titlePage.pubCity']")).sendKeys("Example City");
		
		
		List<WebElement> stateList2 = new ArrayList<>();
		stateList2 = driver.findElements(By.xpath("//*[@ng-model='pubState']/*"));

		Assert.assertEquals(stateList2.size(), 60, "Size of state list does NOT match expected number (60)");
		System.out.println("Size of State Options is " + stateList2.size());

		for (int i = 0; i < stateList2.size(); i++) {

			driver.findElement(By.xpath("//*[@ng-model='pubState']")).click();
			stateList2.get(i).click();
			System.out.println(
					"(2nd state dropdown) Current State selected :  " + stateList2.get(i).getText() + " (" + i + ")");
		}

		
		
	}

	@Test
	public void newLCCNRegistration() throws Throwable {

		loginLCCN();
		lccnGeneralInformation();
		contributorInformation();

	}
}
