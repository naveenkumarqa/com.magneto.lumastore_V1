package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.PaymentPage;
import LumaStore.pageobjects.ShippingPage;

public class TC07_PaymentPageTest extends TestBase {

	private PaymentPage pyp;
	private ShippingPage sip;

	@BeforeMethod
	public void PaymentPage() {
		pyp = new PaymentPage(driver);
		sip = new ShippingPage(driver);
	}

	@Test 
	public void verifyPayment() {

		//proceed shipping
		pyp = sip.selectShipping();

		//validate payment page
		boolean isPaymentPage = pyp.validatePayment();
		Assert.assertTrue(isPaymentPage,"Payment page navigation failed");
		logger.info("Payment page navigated ");

		//validate price
		String paymentTotal = pyp.retrievePyPrice();
		PaymentPage.storePaymentTotal(paymentTotal);
		logger.info("Retrieved and stored price: " + paymentTotal);
	}
}