package USDA;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Util.Base;

public class LoginLoad extends Base {

	
	
	@Test(priority=1)
	public void USDALogin1() throws Throwable {
		
		driver.get("https://ias.train.nfc.usda.gov/OA_HTML/AppsLocalLogin.jsp");
		driver.findElement(By.xpath("//*[@id='unamebean']")).sendKeys("WBRODER");
		driver.findElement(By.xpath("//*[@id='pwdbean']")).sendKeys("Welcome1234$");
		driver.findElement(By.xpath("//*[@title='Login']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[@href='/OA_HTML/OALogout.jsp?menu=Y'])[1]")).click();
		driver.quit();
	}
	
	@Test(priority=2)
	public void USDALogin2() throws Throwable {
		
		driver.get("https://ias.train.nfc.usda.gov/OA_HTML/AppsLocalLogin.jsp");
		driver.findElement(By.xpath("//*[@id='unamebean']")).sendKeys("DTHOMPSON");
		driver.findElement(By.xpath("//*[@id='pwdbean']")).sendKeys("Welcome1234$");
		driver.findElement(By.xpath("//*[@title='Login']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[@href='/OA_HTML/OALogout.jsp?menu=Y'])[1]")).click();
		driver.quit();
	}
	
	@Test(priority=3)
	public void USDALogin3() throws Throwable {
		
		driver.get("https://ias.train.nfc.usda.gov/OA_HTML/AppsLocalLogin.jsp");
		driver.findElement(By.xpath("//*[@id='unamebean']")).sendKeys("UNLAA01");
		driver.findElement(By.xpath("//*[@id='pwdbean']")).sendKeys("Welcome1234$");
		driver.findElement(By.xpath("//*[@title='Login']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[@href='/OA_HTML/OALogout.jsp?menu=Y'])[1]")).click();
		driver.quit();
	}
	
	@Test(priority=4)
	public void USDALogin4() throws Throwable {
		
		driver.get("https://ias.train.nfc.usda.gov/OA_HTML/AppsLocalLogin.jsp");
		driver.findElement(By.xpath("//*[@id='unamebean']")).sendKeys("UGGAA02");
		driver.findElement(By.xpath("//*[@id='pwdbean']")).sendKeys("Welcome1234$");
		driver.findElement(By.xpath("//*[@title='Login']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[@href='/OA_HTML/OALogout.jsp?menu=Y'])[1]")).click();
		driver.quit();
	}
	
	@Test(priority=5)
	public void USDALogin5() throws Throwable {
		
		driver.get("https://ias.train.nfc.usda.gov/OA_HTML/AppsLocalLogin.jsp");
		driver.findElement(By.xpath("//*[@id='unamebean']")).sendKeys("ULTHA02");
		driver.findElement(By.xpath("//*[@id='pwdbean']")).sendKeys("Welcome1234$");
		driver.findElement(By.xpath("//*[@title='Login']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[@href='/OA_HTML/OALogout.jsp?menu=Y'])[1]")).click();
		driver.quit();
	}
	
}
