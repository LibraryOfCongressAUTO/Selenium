package Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Publisher_Loc {

	static WebDriver driver;
	static String URL = "https://locexternaldev.service-now.com/pub";

	@Test
	public void pubLogin() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Erkin\\Desktop\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

		driver.manage().window().maximize();
		driver.get(URL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// // making sure there are 2 options for ECIP landing page.. author/publisher
		// List<WebElement> landingpageoptions = new ArrayList<>();
		// landingpageoptions = driver.findElements(By.xpath("//*[@class='btn
		// btn-primary btn-lg']"));
		//
		// Boolean equalToTwo = landingpageoptions.size() == 2;
		// if (equalToTwo) {
		// System.out.println("ECIP landing page clickable options is equal to 2 -
		// PASS");
		// } else {
		// System.out.println("ECIP landing page clickable Options NOT equal to 2.
		// Actual size is : "
		// + landingpageoptions.size() + " - FAIL");
		// }

		// driver.findElement(By.xpath("(//*[@class='btn btn-primary
		// btn-lg'])[2]")).click();

		// register a new publisher account
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='btn btn-secondary']")).click();
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[1]")).click();
		
		// Your Information Section (1)---------------------------------------------------------------
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
		driver.findElement(By.xpath("//INPUT[@id='regStreet1']")).sendKeys("testStreet1");
		driver.findElement(By.xpath("//INPUT[@id='regCity']")).sendKeys("testCity1");

		List<WebElement> stateList1 = new ArrayList<>();
		stateList1 = driver.findElements(By.xpath("//*[@ng-model='regState']/*"));

		System.out.println("Size of State Options is " + stateList1.size());

		for (int i = 0; i < stateList1.size(); i++) {

			driver.findElement(By.xpath("(//*[@ng-model='regState']/..)[1]")).click();
			stateList1.get(i).click();
			System.out.println(
					"(1st state dropdown) Current State selected :  " + stateList1.get(i).getText() + " (" + i + ")");
		}

		driver.findElement(By.xpath("//INPUT[@id='regZip']")).sendKeys("11111");
		driver.findElement(By.xpath("//INPUT[@id='regEmail']")).sendKeys("Test1@Example");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[2]")).click();

		// Publisher Information Section (2)-------------------------------------------------------

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
		driver.findElement(By.xpath("//INPUT[@id='pubWebsite']")).sendKeys("Test2@Example");
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[6]")).click();

		// Primary Contact Information (3)------------------------------------------------------------------
		
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[8]")).click();
		driver.findElement(By.xpath("//INPUT[@id='contFirstName']")).sendKeys("Example3");
		driver.findElement(By.xpath("//INPUT[@id='contLastName']")).sendKeys("Test3");
		driver.findElement(By.xpath("//INPUT[@id='contTitle']")).sendKeys("Title3");
		driver.findElement(By.xpath("//INPUT[@id='contStreet1']")).sendKeys("testStreet3");
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
		
		// Senior Officer Information (4)---------------------------------------------------------------------
		
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[12]")).click();
		driver.findElement(By.xpath("//INPUT[@id='officerFirstName']")).sendKeys("Example4");
		driver.findElement(By.xpath("//INPUT[@id='officerLastName']")).sendKeys("Test4");
		driver.findElement(By.xpath("//INPUT[@id='officer_title']")).sendKeys("Title4");
		driver.findElement(By.xpath("//INPUT[@id='officerStreet1']")).sendKeys("testStreet4");
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
		
		//Confirmation & Summary Page
		Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='confirm-submit-sec']/*)[1]")).getText(), "CONFIRM AND SUBMIT");
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
		
				List<WebElement> RegistrantInfo = new ArrayList<>();
				RegistrantInfo = driver.findElements(By.xpath("(//*[@id='registrant']/*/*/*)"));
				
				Assert.assertEquals(RegistrantInfo.size(), 10, "Size of registrant info is NOT (10)");
				System.out.println("Size of Registrant Info is " + RegistrantInfo.size());

				for (int i = 0; i < RegistrantInfo.size(); i++) {

					System.out.println("(Registrant Info) " +  RegistrantInfo.get(i).getText());
				}

				
				
				List<WebElement> PublisherInfo = new ArrayList<>();
				PublisherInfo = driver.findElements(By.xpath("(//*[@id='publisher']/*/*/*)"));
				
				Assert.assertEquals(PublisherInfo.size(), 8 , "Size of publisher info is NOT (8)");
				System.out.println("Size of Publisher Info is " + PublisherInfo.size());

				for (int i = 0; i < PublisherInfo.size(); i++) {

					System.out.println("(Publisher Info) " +  PublisherInfo.get(i).getText());
				}
		
	}
}
