package Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PDI_ecip_login {
	
	static WebDriver driver;
	static String URL = "https://dev41209.service-now.com/ecip_landing";

	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Erkin\\Desktop\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get(URL);
		System.out.println("The URL captured is : " + driver.getCurrentUrl());
		System.out.println("The title of the URL is : " + driver.getTitle());

		// making sure there are 2 options for ECIP landing page.. author/publisher
		List<WebElement> landingpageoptions = new ArrayList<>();
		landingpageoptions = driver.findElements(By.xpath("//*[@class='btn btn-primary btn-lg']"));

		Boolean equalToTwo = landingpageoptions.size() == 2;
		if (equalToTwo) {
			System.out.println("ECIP landing page clickable options is equal to 2 - PASS");
		} else {
			System.out.println("ECIP landing page clickable Options NOT equal to 2. Actual size is : "
					+ landingpageoptions.size() + " - FAIL");
		}

		driver.findElement(By.xpath("(//*[@class='btn btn-primary btn-lg'])[2]")).click();

		// register a new publisher account
		driver.findElement(By.xpath("//*[@href=\"pub?id=loc_publisher_registration_updated\"]")).click();
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[1]")).click();
		driver.findElement(By.xpath("//INPUT[@id='prevPubName']")).sendKeys("New Publisher Example");

		Thread.sleep(2000);

		List<WebElement> titleOptions = new ArrayList<>();
		titleOptions = driver.findElements(By.xpath("((//*[@class='col-sm-6'])[2]/*/*)"));

		System.out.println("Size of Title Options is " + titleOptions.size());

		for (int y = 0; y < titleOptions.size(); y++) {

			driver.findElement(By.xpath("//*[@name='regTitle']")).click();
			titleOptions.get(y).click();
			System.out.println("Title selected is " + titleOptions.get(y).getText());

		}

		driver.findElement(By.xpath("//INPUT[@id='regFirstName']")).sendKeys("Example");
		driver.findElement(By.xpath("//INPUT[@id='regLastName']")).sendKeys("Test");

		driver.findElement(By.xpath("//INPUT[@id='regPhone']")).sendKeys("1234567890");

		driver.findElement(By.xpath("//INPUT[@id='regStreet1']")).sendKeys("Test Street");

		driver.findElement(By.xpath("//INPUT[@id='regCity']")).sendKeys("Test");

		List<WebElement> stateList1 = new ArrayList<>();
		stateList1 = driver.findElements(By.xpath("//*[@ng-model='regState']/*"));

		System.out.println("Size of State Options is " + stateList1.size());

		for (int i = 0; i < stateList1.size(); i++) {

			driver.findElement(By.xpath("(//*[@ng-model='regState']/..)[1]")).click();
			stateList1.get(i).click();
			System.out.println("First State list selected is " + stateList1.get(i).getText() + " : " + i);
		}

		driver.findElement(By.xpath("//INPUT[@id='regZip']")).sendKeys("12345");

		// INPUT[@id='regEmail']

		driver.findElement(By.xpath("//INPUT[@id='regEmail']")).sendKeys("Test@Example");

		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[2]")).click();

		
		//does your publisher have a U.S address?
		
		driver.findElement(By.xpath("(//*[@class='btn btn-default btn-lg'])[4]")).click();
		
		
	}
}
