package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.ProductPage;
import LumaStore.pageobjects.ShippingPage;

public class TC06_ShippingPageTest  extends TestBase {

	private ShippingPage sip;
	private ProductPage pp;

	@BeforeMethod
	public void ShippingPage() {
		sip = new ShippingPage(driver);
		pp = new ProductPage(driver);
	}

	String addressname = "Luma";

	@Test
	public void verifyShipping() {
		
		//proceed to checkout
		sip = pp.proceedCheckout();
		logger.info("Proceed to Checkout");

		//validate checkout page
		boolean isCheckoutpage = sip.validateCheckout();
		Assert.assertTrue(isCheckoutpage,"Checkout page navigation failed");
		logger.info("Checkout page navigated");

		//validate Address
		String address_name = sip.getAddress();
		Assert.assertTrue(address_name.contains(addressname),"Address");
		logger.info("Shipping Address validated");
	}
}