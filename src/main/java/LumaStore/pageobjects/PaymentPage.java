package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class PaymentPage extends TestBase{

	public PaymentPage (WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	//Page Elements
	//Message
	@FindBy (xpath="//div[@class='payment-group']")
	WebElement payment;
	@FindBy (xpath="//input[@name='billing-address-same-as-shipping']")
	WebElement addressCheck;
	@FindBy (xpath="//td[@data-th='Order Total']//span[@class='price']")
	WebElement paymentTotal;

	//Button
	@FindBy (xpath="//button[@class='action primary checkout' and @type='submit']")
	WebElement placeOrderBtn;

	private static String paymentTotalText;

	//Page Methods
	public boolean validatePayment() {
		DriverUtils.visibleWait(payment);
		return payment.isDisplayed();
	}

	public String retrievePyPrice() {
		String price = paymentTotal.getText();
		logger.info("Retrieved payment total: " + price);		
		return price;
	}

	public CheckoutPage placeOrder() {
		DriverUtils.justWait(10);
		DriverUtils.clickWait(placeOrderBtn);
		logger.info(placeOrderBtn.getText());
		
		//**Due to ElementClickInterceptedException- using JavaScript Click method here**//
		DriverUtils.scrollIntoView(placeOrderBtn);
		DriverUtils.scrollClick(placeOrderBtn);

		return new CheckoutPage(driver);
	}

	public static void storePaymentTotal(String paymentTotal) {
		paymentTotalText = paymentTotal;
	}

	public static String getStoredPaymentTotal() {
		logger.info("Retrieved stored payment total: " + paymentTotalText);
		return paymentTotalText;
	}
}