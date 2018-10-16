package Author;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Util.Base;

public class LCCN extends Base {

	static String AUTHORURL = "https://locexternaltest.service-now.com/auth";

	@Test
	public void newLCCNRegistration() throws Throwable {

		System.out.println("Beginning of Author LCCN Form");

		loginLCCN();
		lccnGeneralInformation();
		contributorInformation();
		titlePageInfo();
		seriesVolumeInfo();
		additionalInfo();
		confirmAndSubmit();

		System.out.println("End of Author LCCN Form");

		// driver.quit();

	}

	// -------------------------------------------------------------------------------------------
	public void lccnGeneralInformation() throws Throwable {

		driver.findElement(By.xpath("(//*[@href='?id=request_lccn']/*)[2]")).click();

		// GENERAL INFORMATION (1)

		// first question
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = true']")).click();
		Thread.sleep(1000);
		int ErrorMSG1 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-scope']")).size();
		System.out.println(
				"1st Message : " + driver.findElement(By.xpath("//*[@class='alert alert-danger ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG1, 1);
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = false']")).click();

		// second question
		driver.findElement(By.xpath("//*[@ng-model='formParams.periodicIntervals'][1]")).click();
		int ErrorMSG2 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-scope']")).size();
		System.out.println(
				"2nd Message : " + driver.findElement(By.xpath("//*[@class='alert alert-danger ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG2, 1);
		driver.findElement(By.xpath("//*[@ng-model='formParams.periodicIntervals'][2]")).click();

		// third question
		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[1]")).click();

		// # of pages
		driver.findElement(By.xpath("(//*[@name='approxNumOfPages'])")).sendKeys("50");

		// ---------------------------------------------------------------------------------------DO
		// WORK KIDS CHOICES

		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[2]")).click();

		List<WebElement> languageOptions = new ArrayList<>();
		languageOptions = driver.findElements(By.xpath("//*[@name='languageOfText']/*"));

		System.out.println("Size of Language Options is " + languageOptions.size());

		for (int y = 0; y < languageOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='languageOfText']")).click();
			languageOptions.get(y).click();
			System.out.println("Current Language selected is : " + languageOptions.get(y).getText() + "(" + y + ")");
		}
		Assert.assertEquals(languageOptions.size(), 16);

		List<WebElement> monthOptions = new ArrayList<>();
		monthOptions = driver.findElements(By.xpath("//*[@name='month']/*"));

		System.out.println("Size of month Options is " + monthOptions.size());

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

		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Publisher.Test1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Publisher.test1!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String requestLCCN = driver.findElement(By.xpath("(//*[@href='?id=request_lccn']/*)[2]")).getText();
		Assert.assertEquals(requestLCCN, "Request LCCN");

	}

	public void contributorInformation() throws Throwable {

		// add individual
		String selectedButton = driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[1]")).getText();
		System.out.println(selectedButton);
		Assert.assertEquals(selectedButton, "Individual");
		driver.findElement(By.xpath("//*[@name='fName']")).sendKeys("FirstName");
		driver.findElement(By.xpath("//*[@ng-model='contributor.middleName']")).sendKeys("MiddleName");
		driver.findElement(By.xpath("//*[@name='lname']")).sendKeys("LastName");
		driver.findElement(By.xpath("//*[@name='birthYear']")).sendKeys("1900");
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType']/*)[2]")).click();

		WebElement addContributor = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
		addContributor.click();

		// add organization

		String selectedButton2 = driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[2]"))
				.getText();
		System.out.println(selectedButton2);
		Assert.assertEquals(selectedButton2, "Organization");

		driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[2]")).click();

		driver.findElement(By.xpath("//*[@name='orgName']")).sendKeys("OrganizationName");
		driver.findElement(By.xpath("//*[@name='city']")).sendKeys("OrganizationCity");

		driver.findElement(By.xpath("//*[@name='state']")).click();
		driver.findElement(By.xpath("(//*[@name='state']/*)[text()='District of Columbia']")).click();

		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType']/*)[2]")).click();

		addContributor.click();

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();

	}

	public void seriesVolumeInfo() throws Throwable {
		// SERIES
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.isSeries'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.isSeries'][1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.title']")).sendKeys("Series Title");
		driver.findElement(By.xpath("//*[@name='series_volume']")).sendKeys("1");
		driver.findElement(By.xpath("//*[@name='series_issn']")).sendKeys("2049-3630");
		driver.findElement(By.xpath("//*[@class='btn btn-primary large-add'][1]")).click();
		Thread.sleep(1000);

		// driver.findElement(By.xpath("//*[@ng-click='c.removeIndex(c.series_collection,
		// $index)']")).click();

		// VOLUME
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.largeVolume'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.largeVolume'][1]")).click();

		// (PRINT ISBN)
		driver.findElement(By.xpath("//*[@name='isbn']")).sendKeys("978-3-16-148410-0");

		List<WebElement> FormatList = new ArrayList<>();
		FormatList = driver.findElements(By.xpath("//*[@name='format']/*"));

		Assert.assertEquals(FormatList.size(), 11, "Size of Formats list does NOT match expected number (11)");
		System.out.println("Size of Format Options is " + FormatList.size());

		for (int i = 0; i < FormatList.size(); i++) {

			driver.findElement(By.xpath("//*[@name='format']")).click();
			FormatList.get(i).click();
			System.out.println(
					"(1st Format Dropdown) Current Format selected :  " + FormatList.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//*[@ng-model='formParams.series.other']")).sendKeys("ExampleOther");

		driver.findElement(By.xpath("//*[@name='vol_num']")).sendKeys("4");

		driver.findElement(By.xpath("//*[@name='vol_title']")).sendKeys("Example Title");

		driver.findElement(By.xpath("(//*[@class='btn btn-primary large-add'])[2]")).click();

		// ELECTRONIC ISBN
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.isEformat'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.series.isEformat'][1]")).click();

		driver.findElement(By.xpath("//*[@ng-required='!formParams.series.eformat_isbn']")).sendKeys("978-0613685726");

		List<WebElement> FormatList2 = new ArrayList<>();
		FormatList2 = driver.findElements(By.xpath("//*[@ng-model='formParams.series.eformat_format']/*"));

		Assert.assertEquals(FormatList2.size(), 10,
				"Size of Electronic Formats list does NOT match expected number (10)");
		System.out.println("Size of Format Options is " + FormatList2.size());

		for (int i = 0; i < FormatList2.size(); i++) {

			driver.findElement(By.xpath("//*[@ng-model='formParams.series.eformat_format']")).click();
			FormatList2.get(i).click();
			System.out.println("(2nd Format Dropdown) Current Format selected :  " + FormatList2.get(i).getText() + " ("
					+ i + ")");
		}

		driver.findElement(By.xpath("//*[@ng-model='formParams.series.eformat_other']")).sendKeys("ExampleEBookOther");
		driver.findElement(By.xpath("//*[@ng-click='c.addEformatISBN(eISBNAdd)']")).click();
		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();

	}

	public void additionalInfo() {

		// ADDITIONAL INFO AND SUMMARY
		driver.findElement(By.xpath("//*[@ng-model='formParams.bookSum.summary']"))
				.sendKeys("Example Summary....................");
		driver.findElement(By.xpath("//*[@ng-model='formParams.bookSum.info']")).sendKeys("Account Info");
		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
	}

	public void confirmAndSubmit() throws Throwable {

		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='edit-button'])[1]")).getText(),
				"GENERAL INFORMATION");

		// String HeaderVerify2 =
		// driver.findElement(By.xpath("(//*[@class='edit-button'])[2]")).getText();
		// Assert.assertEquals(HeaderVerify2, "CONTRIBUTION INFORMATION");

		// String HeaderVerify3 =
		// driver.findElement(By.xpath("(//*[@class='edit-button'])[3]")).getText();
		// Assert.assertEquals(HeaderVerify3, "BOOK SUMMARY");
	}

	public void titlePageInfo() throws Throwable {
		// title page information
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.title']")).sendKeys("LCCN Title Example");

		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.subtitle']")).sendKeys("LCCN Subtitle Example");

		driver.findElement(By.xpath("//*[@name='edition']")).sendKeys("LCCN Edition Example");

		driver.findElement(By.xpath("//*[@name='titlePage.pubName']")).sendKeys("LCCN Publisher Name Example");

		driver.findElement(By.xpath("//*[@name='titlePage.pubCity']")).sendKeys("LCCN City Example");

		List<WebElement> stateList2 = new ArrayList<>();
		stateList2 = driver.findElements(By.xpath("//*[@ng-model='formParams.titlePage.state']/*"));

		Assert.assertEquals(stateList2.size(), 59, "Size of state list does NOT match expected number (59)");
		System.out.println("Size of State Options is " + stateList2.size());

		for (int i = 0; i < stateList2.size(); i++) {

			driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.state']")).click();
			stateList2.get(i).click();
			System.out.println(
					"Current State selected :  " + stateList2.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
	}
}
