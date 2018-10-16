package Publisher;

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

public class CIP extends Base {

	static String PUBLISHERURL = "https://locexternaltest.service-now.com/pub";

	@Test
	public void newCIPApplication() throws Throwable {
		System.out.println("Beginning of Publisher CIP Form");

		loginCIP();
		cipGeneralInformation();
		contributorInformationCIP();
		titlePageInfo();
		seriesVolumeInfo();
		galley();
		additionalInfoSummary();

		System.out.println("End of Publisher CIP Form");
	}

	public void additionalInfoSummary() throws Throwable {

		driver.findElement(By.xpath("//*[@ng-model='formParams.bookSum.summary']"))
				.sendKeys("This is a Book Summary that is over 35 characters long...");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@ng-model='formParams.sum.subContact']")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.sum.subContact']/*)[2]")).click();

		driver.findElement(By.xpath("(//*[@ng-model='formParams.sum.sendContact'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.sum.sendContact']/*)[2]")).click();

		driver.findElement(By.xpath("(//*[@ng-model='formParams.sum.recipient'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.sum.recipient']/*)[2]")).click();
		driver.findElement(By.xpath("(//*[@ng-click='c.addEmailRecipient()'])")).click();

		driver.findElement(By.xpath("//*[@ng-model='formParams.bookSum.info']"))
				.sendKeys("This is some additional info...");

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
	}

	public void galley() throws Throwable {

		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@ng-model='formParams.galley.isSingle'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.galley.isSingle'][1]")).click();

		driver.findElement(By.xpath("//*[@class='btn btn-link']")).click();

		Thread.sleep(3000);

		StringSelection ss = new StringSelection("C:\\Users\\Erkin\\Desktop\\SampleGalley.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
	}

	public void seriesVolumeInfo() throws Throwable {

		Thread.sleep(2000);

		// will the book belong to a series? (ISSN Info)
		driver.findElement(By.xpath("((//*[@class='col-md-12 col-sm-12 col-xs-12']/*)[1]/*)[2]")).click();

		driver.findElement(By.xpath("((//*[@class='col-md-12 col-sm-12 col-xs-12']/*)[1]/*)[1]")).click();

		driver.findElement(By.xpath("//*[@name='series_title']")).sendKeys("Series Title Example");

		driver.findElement(By.xpath("//*[@name='series_volume']")).sendKeys("2");

		driver.findElement(By.xpath("//*[@name='series_issn']")).sendKeys("2049-3630");

		driver.findElement(By.xpath("//*[@name='series_volume']")).sendKeys("2");

		driver.findElement(By.xpath("(//*[@class='btn btn-primary large-add'])[1]")).click();

		// is it more than one volume?
		driver.findElement(By.xpath("(//*[@ng-model=\"formParams.series.largeVolume\"])[2]")).click();
		driver.findElement(By.xpath("(//*[@ng-model=\"formParams.series.largeVolume\"])[1]")).click();

		// sending ISBN
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

		driver.findElement(By.xpath("//*[@ng-model='formParams.series.receiveSingleDataBlock']")).click();

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

	public void cipGeneralInformation() throws Throwable {

		driver.findElement(By.xpath("(//*[@ng-href='?id=request_cip']/*)[2]")).click();

		Thread.sleep(2000);

		// GENERAL INFORMATION (1)

		String section1 = driver.findElement(By.xpath("((//*[@class='stepwizard-row']/*)[1]/*)[2]")).getText();

		System.out.println("Currently on the " + section1
				+ " section______________________________________________________________");

		// which publisher does this submission belong to
		// driver.findElement(By.xpath("//*[@name='formParams.selectedPub']")).click();
		// Thread.sleep(500);
		// driver.findElement(By.xpath("(//*[@name='formParams.selectedPub']/*)[2]")).click();

		// first question
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = true']")).click();
		Thread.sleep(1000);
		int ErrorMSG1 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-scope']")).size();
		System.out.println(
				"1st Message : " + driver.findElement(By.xpath("//*[@class='alert alert-danger ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG1, 1);
		driver.findElement(By.xpath("//*[@ng-click='formParams.eFormat = false']")).click();

		// second question
		driver.findElement(By.xpath("//*[@ng-model='formParams.intendedFor'][1]")).click();
		int ErrorMSG2 = driver.findElements(By.xpath("//*[@class='alert alert-danger ng-scope']")).size();
		System.out.println(
				"2nd Message : " + driver.findElement(By.xpath("//*[@class='alert alert-danger ng-scope']")).getText());
		Assert.assertEquals(ErrorMSG2, 1);
		driver.findElement(By.xpath("//*[@ng-model='formParams.intendedFor'][2]")).click();

		// third question
		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[1]")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.cyAdults'])[2]")).click();

		// fourth question (graphic novel)
		driver.findElement(By.xpath("(//*[@ng-model='formParams.isGraphicNovel'])[2]")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.isGraphicNovel'])[1]")).click();

		// fifth question (large print format)
		driver.findElement(By.xpath("(//*[@ng-model='formParams.isLargeFormat'])[2]")).click();
		driver.findElement(By.xpath("(//*[@ng-model='formParams.isLargeFormat'])[1]")).click();

		// language of book
		List<WebElement> languageOptions = new ArrayList<>();
		languageOptions = driver.findElements(By.xpath("//*[@name='languageOfText']/*"));

		System.out.println("Size of Language Options is " + languageOptions.size());

		for (int y = 0; y < languageOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='languageOfText']")).click();
			languageOptions.get(y).click();
			System.out.println("Current Language selected is : " + languageOptions.get(y).getText() + "(" + y + ")");
		}
		Assert.assertEquals(languageOptions.size(), 8);

		// projected publication date (MONTH)
		List<WebElement> monthOptions = new ArrayList<>();
		monthOptions = driver.findElements(By.xpath("//*[@name='month']/*"));
		System.out.println("Size of publication date month Options is " + monthOptions.size());
		for (int y = 0; y < monthOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='month']")).click();
			monthOptions.get(y).click();
			System.out.println("Current publication date Month selected is : " + monthOptions.get(y).getText());
		}
		Assert.assertEquals(monthOptions.size(), 13);

		// projected publication date (YEAR)
		List<WebElement> yearOptions = new ArrayList<>();
		yearOptions = driver.findElements(By.xpath("//*[@name='year']/*"));
		System.out.println("Size of publication date Year Options is " + yearOptions.size());
		for (int y = 0; y < yearOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='year']")).click();
			yearOptions.get(y).click();
			System.out.println("Current publication date Year selected is : " + yearOptions.get(y).getText());
		}
		Assert.assertEquals(yearOptions.size(), 12);

		// projected print date (MONTH)
		List<WebElement> monthOptions2 = new ArrayList<>();
		monthOptions2 = driver.findElements(By.xpath("//*[@name='printMonth']/*"));
		System.out.println("Size of print date month Options is " + monthOptions2.size());
		for (int y = 0; y < monthOptions2.size(); y++) {
			driver.findElement(By.xpath("//*[@name='printMonth']")).click();
			monthOptions2.get(y).click();
			System.out.println("Current print date Month selected is : " + monthOptions2.get(y).getText());
		}
		Assert.assertEquals(monthOptions2.size(), 13);

		// projected print date (DAY)
		List<WebElement> dayOptions = new ArrayList<>();
		dayOptions = driver.findElements(By.xpath("//*[@name='printDay']/*"));
		System.out.println("Size of print date day Options is " + dayOptions.size());
		for (int y = 0; y < dayOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='printDay']")).click();
			dayOptions.get(y).click();
			System.out.println("Current print date day selected is : " + dayOptions.get(y).getText());
		}
		Assert.assertEquals(dayOptions.size(), 32);

		// projected print date (YEAR)
		List<WebElement> yearOptions2 = new ArrayList<>();
		yearOptions2 = driver.findElements(By.xpath("//*[@name='printYear']/*"));
		System.out.println("Size of print date Year Options is " + yearOptions2.size());
		for (int y = 0; y < yearOptions2.size(); y++) {
			driver.findElement(By.xpath("//*[@name='printYear']")).click();
			yearOptions2.get(y).click();
			System.out.println("Current print date Year selected is : " + yearOptions2.get(y).getText());
		}
		Assert.assertEquals(yearOptions2.size(), 12);

		// subject area
		List<WebElement> subject = new ArrayList<>();
		subject = driver.findElements(By.xpath("//*[@ng-model='formParams.broadSubject']/*"));
		System.out.println("Size of selectable subjects areas are : " + subject.size());
		for (int y = 0; y < subject.size(); y++) {
			driver.findElement(By.xpath("//*[@ng-model='formParams.broadSubject']")).click();
			subject.get(y).click();
			System.out.println("Current subject area selected is : " + subject.get(y).getText() + " (" + y + ")");
		}
		Assert.assertEquals(subject.size(), 48);

		// geographical focus
		driver.findElement(By.xpath("//*[@ng-model='formParams.isGeoFocused'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.isGeoFocused'][1]")).click();

		List<WebElement> geoFocus = new ArrayList<>();
		geoFocus = driver.findElements(By.xpath("//*[@ng-model='formParams.region']/*"));
		System.out.println("Size of geographical focus is : " + geoFocus.size());
		for (int y = 0; y < geoFocus.size(); y++) {
			driver.findElement(By.xpath("//*[@ng-model='formParams.region']")).click();
			geoFocus.get(y).click();
			System.out
					.println("Current geographical focus selected is : " + geoFocus.get(y).getText() + " (" + y + ")");
		}
		Assert.assertEquals(geoFocus.size(), 9);

		// next page
		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();

	}

	public void loginCIP() throws Throwable {
		driver.get(PUBLISHERURL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// register a new publisher account
		Thread.sleep(1000);

		driver.findElement(By.xpath("//INPUT[@id='username']")).sendKeys("Publisher.Test1");
		driver.findElement(By.xpath("//INPUT[@id='password']")).sendKeys("Publisher.test1!");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//BUTTON[@name='login'][text()=' Login ']")).click();
		String requestCIP = driver.findElement(By.xpath("(//*[@ng-href='?id=request_cip']/*)[2]")).getText();
		Assert.assertEquals(requestCIP, "Request CIP Data");

	}

	public void contributorInformationCIP() throws Throwable {

		Thread.sleep(2000);

		// individual is pre-selected
		// add individual
		String selectedButton = driver.findElement(By.xpath("(//*[@ng-model='contributor.contribType'])[1]")).getText();
		System.out.println(selectedButton);
		Assert.assertEquals(selectedButton, "Individual");
		driver.findElement(By.xpath("//*[@name='fName']")).sendKeys("FirstName");
		driver.findElement(By.xpath("//*[@ng-model='contributor.middleName']")).sendKeys("MiddleName");
		driver.findElement(By.xpath("//*[@name='lname']")).sendKeys("LastName");
		driver.findElement(By.xpath("//*[@ng-model='contributor.birthYear']")).sendKeys("2000");
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType'])")).click();
		driver.findElement(By.xpath("(//*[@ng-model='contributor.entryType']/*)[2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='contributor.url']")).sendKeys("www.oaqurlexample.com");
		driver.findElement(By.xpath("//*[@ng-model='contributor.info']")).sendKeys("individual additional info text");

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
		driver.findElement(By.xpath("//*[@ng-model='contributor.info']")).sendKeys("organization additional info text");
		addContributor.click();

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();

	}

	public void titlePageInfo() throws Throwable {

		driver.findElement(By.xpath("//*[@name='titlePage.title']")).sendKeys("CIP Title Example");

		driver.findElement(By.xpath("//*[@name='subtitle']")).sendKeys("CIP Subtitle Example");

		driver.findElement(By.xpath("//*[@name='edition']")).sendKeys("CIP Edition Example");

		driver.findElement(By.xpath("//*[@name='titlePage.pubName']")).sendKeys("CIP Publisher Name Example");

		driver.findElement(By.xpath("//*[@name='titlePage.pubCity']")).sendKeys("CIP City Example");

		List<WebElement> stateOptions = new ArrayList<>();
		stateOptions = driver.findElements(By.xpath("//*[@name='titlePage.state']/*"));

		System.out.println("Size of State Options is " + stateOptions.size());

		for (int y = 0; y < stateOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@name='titlePage.state']")).click();
			stateOptions.get(y).click();
			System.out.println("Current State selected is : " + stateOptions.get(y).getText());
			// Thread.sleep(500);
		}
		Assert.assertEquals(stateOptions.size(), 59);

		// TRANSLATION AND RELATED
		// TITLES-------------------------------------------------------------------

		// is your book a translation of another book? (Original Title/Language)
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.isTranslation'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.isTranslation'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.translationOriginalTitle']"))
				.sendKeys("Translation Title Example");

		List<WebElement> langOptions = new ArrayList<>();
		langOptions = driver
				.findElements(By.xpath("//*[@ng-model='formParams.titlePage.translationOriginalLanguage']/*"));
		System.out.println("Size of Translation Language options is " + langOptions.size());
		for (int y = 0; y < langOptions.size(); y++) {
			driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.translationOriginalLanguage']")).click();
			langOptions.get(y).click();
			System.out.println("Current translation language selected is : " + langOptions.get(y).getText());
			Thread.sleep(200);
		}
		Assert.assertEquals(langOptions.size(), 8);

		// is your book a version of another work? (Previous Title)
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.previousVersion'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.previousVersion'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.previousTitle']"))
				.sendKeys("Previous Title Example");

		// is your book a reprint of a previously published work?
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.reprint'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.reprint'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.reprintPreviousTitle']"))
				.sendKeys("Reprint Title Example");

		// contains bibliographic references?
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.hasBibliography'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.hasBibliography'][1]")).click();

		// contain index or indices?
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.hasIndex'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-model='formParams.titlePage.hasIndex'][1]")).click();

		driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right']")).click();
	}
}
