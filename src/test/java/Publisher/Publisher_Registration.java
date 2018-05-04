package Publisher;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Util.Base;

public class Publisher_Registration extends Base {

	static String URL = "https://locexternaldev.service-now.com/pub";

	public void navigateToPublisherRegistrationForm() throws Throwable {
		driver.get(URL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// register a new publisher account
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='btn btn-secondary']")).click();

		Thread.sleep(1500);
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[1]")).click();

	}

	public void yourInfoSection() throws Throwable {

		// Your Information Section
		// (1)---------------------------------------------------------------
		driver.findElement(By.xpath("//INPUT[@id='prevPubName']")).sendKeys("New Publisher Example");

		Thread.sleep(2000);

		List<WebElement> titleOptions = new ArrayList<>();
		titleOptions = driver.findElements(By.xpath("((//*[@class='col-sm-6'])[2]/*/*)"));

		System.out.println("Size of Title Options is " + titleOptions.size());

		for (int y = 0; y < titleOptions.size(); y++) {

			driver.findElement(By.xpath("//*[@name='regTitle']")).click();
			titleOptions.get(y).click();
			System.out.println("Current Title selected is : " + titleOptions.get(y).getText());

		}

		driver.findElement(By.xpath("//INPUT[@id='regFirstName']")).sendKeys("Example1");
		driver.findElement(By.xpath("//INPUT[@id='regLastName']")).sendKeys("Test1");
		driver.findElement(By.xpath("//INPUT[@id='regPhone']")).sendKeys("1111111111");
		driver.findElement(By.xpath("//INPUT[@id='regStreet1']")).sendKeys("testStreet1 example");
		driver.findElement(By.xpath("//INPUT[@id='regStreet2']")).sendKeys("testStreetCont1 example");
		driver.findElement(By.xpath("//INPUT[@id='regCity']")).sendKeys("testCity1");

		List<WebElement> stateList1 = new ArrayList<>();
		stateList1 = driver.findElements(By.xpath("//*[@ng-model='regState']/*"));

		System.out.println("Size of State Options is " + stateList1.size());

		for (int i = 1; i < stateList1.size(); i++) {

			driver.findElement(By.xpath("(//*[@ng-model='regState']/..)[1]")).click();
			stateList1.get(i).click();
			System.out.println(i);
			System.out.println(
					"(1st state dropdown) Current State selected :  " + stateList1.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//INPUT[@id='regZip']")).sendKeys("11111");
		driver.findElement(By.xpath("//INPUT[@id='regEmail']")).sendKeys("Test1@Example");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[2]")).click();

	}

	public void publisherInformationSection() throws Throwable {

		// Publisher Information Section
		// (2)-------------------------------------------------------

		// does your publisher have a U.S address?
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[4]")).click();

		Thread.sleep(1000);

		Boolean PopUp1 = driver.findElements(By.xpath("(//*[@class='modal-content'])[1]")).size() > 0;

		if (PopUp1) {
			System.out.println("Pop-Up is displayed");
		} else {
			System.out.println("Pop-Up is NOT displayed");
			Assert.assertTrue(driver.findElements(By.xpath("(//*[@class='modal-content'])[1]")).size() > 0);
		}

		driver.findElement(By.xpath("(//*[@class='btn btn-default'])[1]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[3]")).click();
		driver.findElement(By.xpath("//INPUT[@id='pubStreet1']")).sendKeys("publisherStreet2");
		driver.findElement(By.xpath("//INPUT[@id='pubStreet2']")).sendKeys("publisherStreetCont2");
		driver.findElement(By.xpath("//INPUT[@id='pubCity']")).sendKeys("publisherCity2");

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

		driver.findElement(By.xpath("//INPUT[@id='pubZip']")).sendKeys("22222");
		driver.findElement(By.xpath("//INPUT[@id='pubPhone']")).sendKeys("2222222222");
		driver.findElement(By.xpath("//INPUT[@id='pubWebsite']")).sendKeys("www.test2.com");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[6]")).click();

	}

	public void primaryContactSection() throws Throwable {

		// Primary Contact Information
		// (3)------------------------------------------------------------------

		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[8]")).click();
		driver.findElement(By.xpath("//INPUT[@id='contFirstName']")).sendKeys("Example3");
		driver.findElement(By.xpath("//INPUT[@id='contLastName']")).sendKeys("Test3");
		driver.findElement(By.xpath("//INPUT[@id='contTitle']")).sendKeys("Title3");
		driver.findElement(By.xpath("//INPUT[@id='contStreet1']")).sendKeys("testStreet3");
		driver.findElement(By.xpath("//INPUT[@id='contStreet2']")).sendKeys("testStreetCont3");
		driver.findElement(By.xpath("//INPUT[@id='contCity']")).sendKeys("testCity3");
		driver.findElement(By.xpath("//INPUT[@id='contZip']")).sendKeys("33333");

		List<WebElement> stateList3 = new ArrayList<>();
		stateList3 = driver.findElements(By.xpath("//*[@ng-model='contState']/*"));

		Assert.assertEquals(stateList3.size(), 60, "Size of state list does NOT match expected number (60)");
		System.out.println("Size of State Options is " + stateList3.size());

		for (int i = 0; i < stateList3.size(); i++) {

			driver.findElement(By.xpath("//*[@ng-model='contState']")).click();
			stateList3.get(i).click();
			System.out.println(
					"(3rd state dropdown) Current State selected :  " + stateList3.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//INPUT[@id='contPhone']")).sendKeys("3333333333");
		driver.findElement(By.xpath("//INPUT[@id='contEmail']")).sendKeys("Test3@Example");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[10]")).click();
		Thread.sleep(2000);

	}

	public void seniorOfficerSection() {

		// Senior Officer Information
		// (4)---------------------------------------------------------------------

		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[12]")).click();
		driver.findElement(By.xpath("//INPUT[@id='officerFirstName']")).sendKeys("Example4");
		driver.findElement(By.xpath("//INPUT[@id='officerLastName']")).sendKeys("Test4");
		driver.findElement(By.xpath("//INPUT[@id='officer_title']")).sendKeys("Title4");
		driver.findElement(By.xpath("//INPUT[@id='officerStreet1']")).sendKeys("testStreet4");
		driver.findElement(By.xpath("//INPUT[@id='officerStreet2']")).sendKeys("testStreetCont4");
		driver.findElement(By.xpath("//INPUT[@id='officerCity']")).sendKeys("testCity4");
		driver.findElement(By.xpath("//INPUT[@id='officerZip']")).sendKeys("44444");

		List<WebElement> stateList4 = new ArrayList<>();
		stateList4 = driver.findElements(By.xpath("//*[@ng-model='officerState']/*"));

		Assert.assertEquals(stateList4.size(), 60, "Size of state list does NOT match expected number (60)");
		System.out.println("Size of State Options is " + stateList4.size());

		for (int i = 0; i < stateList4.size(); i++) {

			driver.findElement(By.xpath("//*[@ng-model='officerState']")).click();
			stateList4.get(i).click();
			System.out.println(
					"(4th state dropdown) Current State selected :  " + stateList4.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//INPUT[@id='officerPhone']")).sendKeys("4444444444");
		driver.findElement(By.xpath("//INPUT[@id='officerEmail']")).sendKeys("Test4@Example");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[14]")).click();

	}

	public void summaryPageSection() throws Throwable {

		// Confirmation & Summary Page
		Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='confirm-submit-sec']/*)[1]")).getText(),
				"CONFIRM AND SUBMIT");

		List<WebElement> isCollapsed = new ArrayList<>();
		isCollapsed = driver.findElements(By.xpath("//*[@class='collapsed']"));
		Assert.assertEquals(isCollapsed.size(), 0);

		// ------Registrant field verification-------------------------------
		List<WebElement> RegistrantInfo = new ArrayList<>();
		RegistrantInfo = driver.findElements(By.xpath("(//*[@id='registrant']/*/*/*)"));

		Assert.assertEquals(RegistrantInfo.size(), 10, "Size of registrant info is NOT (10)");
		System.out.println("Size of Registrant Info fields populated is " + RegistrantInfo.size());

		for (int i = 0; i < RegistrantInfo.size(); i++) {

			System.out.println("(Registrant Info) " + RegistrantInfo.get(i).getText());

			Boolean registrantIsEmpty = RegistrantInfo.get(i).getText().isEmpty();
			if (registrantIsEmpty == true) {
				System.out.println("Error! Registrant Field is Empty!");
			}
		}

		// ------Publisher field verification-------------------------------
		List<WebElement> PublisherInfo = new ArrayList<>();
		PublisherInfo = driver.findElements(By.xpath("((//*[@id='publisher']/*/*/*)/*[2])"));

		// *[@id='publisher']/*/*/*)

		Assert.assertEquals(PublisherInfo.size(), 8, "Size of publisher info is NOT (8)");
		System.out.println("Size of Publisher Info fields populated is " + PublisherInfo.size());

		for (int i = 0; i < PublisherInfo.size(); i++) {

			System.out.println("(Publisher Info) " + PublisherInfo.get(i).getText());

			Boolean publisherIsEmpty = PublisherInfo.get(i).getText().isEmpty();
			if (publisherIsEmpty == true) {
				System.out.println("Error! Publisher Field is Empty!");
			}
		}

		// ------Primary Contact field verification-------------------------------
		List<WebElement> PrimaryContactInfo = new ArrayList<>();
		PrimaryContactInfo = driver.findElements(By.xpath("((//*[@id='primary-contact']/*/*/*)/*[2])"));

		Assert.assertEquals(PrimaryContactInfo.size(), 9, "Size of Primary Contact info is NOT (9)");
		System.out.println("Size of Primary Contact Info fields populated is " + PublisherInfo.size());

		for (int i = 0; i < PrimaryContactInfo.size(); i++) {

			System.out.println("(Primary Contact Info) " + PrimaryContactInfo.get(i).getText());

			Boolean primaryContactIsEmpty = PrimaryContactInfo.get(i).getText().isEmpty();
			if (primaryContactIsEmpty == true) {
				System.out.println("Error! Primary Contact Field is Empty!");
			}
		}

		// ------Senior Officer field verification-------------------------------
		List<WebElement> SeniorOfficerInfo = new ArrayList<>();
		SeniorOfficerInfo = driver.findElements(By.xpath("((//*[@id='senior-officer']/*/*/*)/*[2])"));

		Assert.assertEquals(SeniorOfficerInfo.size(), 9, "Size of Senior Officer info is NOT (9)");
		System.out.println("Size of Senior Officer Info fields populated is " + SeniorOfficerInfo.size());

		for (int i = 0; i < SeniorOfficerInfo.size(); i++) {

			System.out.println("(Senior Officer Info) " + SeniorOfficerInfo.get(i).getText());

			Boolean seniorOfficerIsEmpty = SeniorOfficerInfo.get(i).getText().isEmpty();
			if (seniorOfficerIsEmpty == true) {
				System.out.println("Error! Senior Officer Field is Empty!");
			}
		}

	}

	@Test
	public void NewPublisherRegistration() throws Throwable {

		System.out.println("Beginning of New Publisher Registration");

		navigateToPublisherRegistrationForm();

		yourInfoSection();

		publisherInformationSection();

		primaryContactSection();

		seniorOfficerSection();

		summaryPageSection();

		System.out.println("End of New Publisher Registration");

	}
}
