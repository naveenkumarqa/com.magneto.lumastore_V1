package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.IndexPage;

public class TC01_IndexPageTest extends TestBase {
	
	private IndexPage ip;
	
	//Assertions
	String actualTitle = "Home Page";
	
	/**method to initialize the PageClass object. 
	It is now available to all test methods, avoiding the need to initialize it in each test method.**/
	@BeforeMethod
	public void IndexPage() {
		ip = new IndexPage(driver);
	}

	@Test
	public void verifyIndexPage() {
		
		//validate Homepage
		boolean isIndexPage = ip.validateIndexPage();
		Assert.assertTrue(isIndexPage,"IndexPage validation failed");
		logger.info("Indexpage validated");

		//validate Homepage title
		String page_Title = ip.validateIndexTitle();
		Assert.assertEquals(page_Title, actualTitle,"IndexPage title failed");
		logger.info("Indexpage Title validated");
	}
}