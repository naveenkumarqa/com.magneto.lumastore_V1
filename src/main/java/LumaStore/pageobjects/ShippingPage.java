package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class ShippingPage extends TestBase {

	public ShippingPage (WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	//Page Elements
	//Tags
	@FindBy (xpath="//div[@id='checkout']")
	WebElement checkout;
	@FindBy (xpath="//div[@class='shipping-address-item selected-item']")
	WebElement address;
	@FindBy (xpath="//div[@class='checkout-shipping-method']")
	WebElement shipMethod;
	
	//Buttons
	@FindBy (xpath="//td[text()='Fixed']/preceding-sibling::td/input[@type='radio']")
	WebElement fixedRateBtn;
	@FindBy (xpath="//span[.='Next']")
	WebElement nextBtn;

	
	//Page Methods
	public boolean validateCheckout() {
		DriverUtils.visibleWait(checkout);
		return checkout.isDisplayed();
	}

	public String getAddress() {
		DriverUtils.scrollIntoView(address);
		return address.getText();
	}

	public PaymentPage selectShipping() {
		DriverUtils.scrollIntoView(shipMethod);
		fixedRateBtn.click();
		logger.info("Shipping Method selected");
		nextBtn.click();
		return new PaymentPage(driver);
	}
}