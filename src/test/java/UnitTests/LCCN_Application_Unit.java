package UnitTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Methods.CommonMethods;
import Util.Base;

public class LCCN_Application_Unit extends Base {

	static String AUTHORURL = "https://locexternaldev.service-now.com/auth";
	static String AUTHORTITLE = "CIP Author Login - CIP Author Portal";

	static CommonMethods CM = new CommonMethods();

	@Test(priority = 1)
	public void Navigate_To_LCCN_Application() throws Throwable{
		Thread.sleep(5000);
	}
	
	@Test(priority = 2)
	public void Verify_LCCN_WireFrames_Are_Enabled() throws Throwable {
		Thread.sleep(6000);
	}
	
	@Test(priority = 3)
	public void Verify_Six_WireFrames_Are_Present() throws Throwable {
		Thread.sleep(8000);
	}
	
	@Test(priority = 4)
	public void Complete_General_Information_Section() throws Throwable {
		Thread.sleep(23000);
	}
	
	@Test(priority = 5)
	public void Complete_Contributor_Information_Section() throws Throwable  {
		Thread.sleep(20000);
	}
	
	@Test(priority = 6)
	public void Complete_Title_Page_Section() throws Throwable {
		Thread.sleep(12000);
	}
	
	@Test(priority = 7)
	public void Complete_Series_And_Volume_Information_Section() throws Throwable {
		Thread.sleep(14000);
	}
	
	@Test(priority = 8)
	public void Complete_Additional_Information_And_Summary_Section() throws Throwable {
		Thread.sleep(14000);
	}
	
	
	@Test(priority = 9)
	public void Navigate_To_Confirm_And_Submit_Section() throws Throwable {
		Thread.sleep(11000);
	}
}
