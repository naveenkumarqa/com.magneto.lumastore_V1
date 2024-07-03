package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class CheckoutPage extends TestBase{
	public CheckoutPage (WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Page Elements
	//Message
	@FindBy (xpath="//div[@class='checkout-success']")
	WebElement checkoutSuccess;
	@FindBy (xpath="//a[@class='order-number']")
	WebElement orderNum;

	//Tag
	@FindBy (xpath="//span[@class='base' and contains (text(),'Order')]")
	WebElement pendingOrder;
	@FindBy (xpath="//div[@class='block block-order-details-view']")
	WebElement orderInfo;
	@FindBy (xpath="//td[@data-th='Grand Total']")
	WebElement checkoutTotal;

	//Assertions
	String checkTotal;

	//Page Methods
	public String getOrderStatus() {
		DriverUtils.visibleWait(checkoutSuccess);
		return driver.getTitle();
	}

	public String getOrderNum() {
		DriverUtils.scrollIntoView(orderNum);
		return orderNum.getText();
	}

	public String getPendOrderNum() {
		orderNum.click();
		DriverUtils.visibleWait(pendingOrder);
		return pendingOrder.getText().replaceAll("[^0-9]", "");
	}

	public String checkTotal() {
		DriverUtils.scrollIntoView(checkoutTotal);
		return checkoutTotal.getText();
	}
}