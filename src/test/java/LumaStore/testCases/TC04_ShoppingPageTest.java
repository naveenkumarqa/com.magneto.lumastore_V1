package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.HomePage;
import LumaStore.pageobjects.ShoppingPage;
import LumaStore.utils.ReportListeners;

public class TC04_ShoppingPageTest extends TestBase {
	
	private ShoppingPage sp;
	private HomePage hp;
	
	@BeforeMethod
	public void ShoppingPage() {
		sp = new ShoppingPage(driver);
		hp = new HomePage(driver);
	}

	//Assertion
	String actualTitle = "Jackets";

	@Test
	public void verifyProdCategory()  {
		//navigate to desired product		
		sp = hp.navigateProduct();
		logger.info("Navigated to desired Product-Jackets");

		//validate selected product category
		String category_Title = sp.getCategoryTitle();
		Assert.assertEquals(category_Title, actualTitle,"Select product validation failed");
		logger.info("Select Product category validated, Product Category: "+category_Title);
		ReportListeners.logMessage("Product Category: "+category_Title);
	}
}