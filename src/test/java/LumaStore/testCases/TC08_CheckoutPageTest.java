package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.CheckoutPage;
import LumaStore.pageobjects.PaymentPage;

public class TC08_CheckoutPageTest extends TestBase {

	private CheckoutPage cp ;
	private PaymentPage pyp;

	@BeforeMethod
	public void CheckoutPage() {
		cp = new CheckoutPage(driver);	
		pyp = new PaymentPage(driver);
	}

	//Assertion
	String pageTitle = "Success Page";

	@Test 
	public void verifyOrderPurchase() {
		//validate placing order
		cp = pyp.placeOrder();
		logger.info("Order placed");
		
		//validate checkoutPage
		String check_out = cp.getOrderStatus();
		Assert.assertEquals(check_out, pageTitle);
		logger.info("Order Purchase status validated");
	}

	@Test (dependsOnMethods = "verifyOrderPurchase")
	public void verifyOrderNum() {
		//Retrieve OrderNumber
		String order_num = cp.getOrderNum();
		logger.info("Order Confirmed: "+order_num);
		
		//Retrieve Pending OrderNumber
		String pend_num = cp.getPendOrderNum();
		logger.info("Pending Order Num is: "+pend_num);
		
		//Compare and validate
		Assert.assertEquals(order_num, pend_num);
		logger.info("Order number validated");
	}

	@Test (dependsOnMethods = "verifyOrderNum")
	public void verifyTotalPrice() {
		
		String retrieve_price = PaymentPage.getStoredPaymentTotal();
		logger.info("Retrived stored price is: "+retrieve_price);
		
		String total_price = cp.checkTotal();
		logger.info("Total price is: "+total_price);
		Assert.assertEquals(retrieve_price, total_price);
		logger.info("Total Order price validated");
	}
}