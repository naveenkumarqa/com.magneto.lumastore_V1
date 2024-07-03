package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.HomePage;
import LumaStore.utils.ReportListeners;

public class TC03_HomePageTest extends TestBase {
	
	private HomePage hp;
	
	@BeforeMethod
	public void HomePage() {
		hp = new HomePage(driver);
	}
	
	@Test
	public void verifyProductList()  {
		
		//validate product count
		int prodCount = hp.productList();
		Assert.assertTrue(prodCount>0,"No Products are listed");
		String count = Integer.toString(prodCount);
		logger.info("Product List validated, Product count is: " + count);
		ReportListeners.logMessage("Product count is: " + count);
	}
}