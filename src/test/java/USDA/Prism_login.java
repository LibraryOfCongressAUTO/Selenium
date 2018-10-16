package USDA;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Util.Base;

public class Prism_login extends Base {

	@Test(priority=1)
	public void prismcredentials1() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("FSSS0018");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
	@Test(priority=2)
	public void prismcredentials2() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("FSLH0002");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
	@Test(priority=3)
	public void prismcredentials3() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("UI00B345");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
	@Test(priority=4)
	public void prismcredentials4() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("UABOH01");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
	@Test(priority=5)
	public void prismcredentials5() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("UDKIN02");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
	@Test(priority=6)
	public void prismcredentials6() {
		driver.get("https://ias.train.nfc.usda.gov/prism.net/Security/LogOn.aspx");
		driver.findElement(By.xpath("//*[@id='UserName_text']")).sendKeys("DAJS0004");
		driver.findElement(By.xpath("//*[@id='Password_text']")).sendKeys("wELCOME#1234");
		driver.findElement(By.xpath("//*[@name='SignIn']")).click();
	}
	
}
