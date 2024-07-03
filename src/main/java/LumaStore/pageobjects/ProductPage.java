package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class ProductPage extends TestBase {

	public ProductPage (WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Page Elements
	//Title
	@FindBy (xpath="//span[@class='base']")
	WebElement prodNameEle;
	@FindBy (xpath="//div[@class='message-success success message']")
	WebElement cartSuccessMsg;

	//Buttons
	@FindBy (xpath="//div[@option-label='XS']")
	WebElement sizeXS;
	@FindBy (xpath="//div[@option-label='S']")
	WebElement sizeS;
	@FindBy (xpath="//div[@option-label='M']")
	WebElement sizeM;
	@FindBy (xpath="//div[@option-label='L']")
	WebElement sizeL;
	@FindBy (xpath="//div[@option-label='XL']")
	WebElement sizeXL;

	@FindBy (xpath="//div[@option-label='Yellow']")
	WebElement yellowCol;
	@FindBy (xpath="//div[@option-label='Orange']")
	WebElement orangeCol;
	@FindBy (xpath="//div[@option-label='Red']")
	WebElement redCol;

	@FindBy (xpath="//button[@id='product-addtocart-button']")
	WebElement addToCartBtn;
	@FindBy (xpath="//span[@class='counter-number']")
	WebElement miniCartBtn;
	@FindBy (xpath="//button[@id='top-cart-btn-checkout']")
	WebElement checkOutBtn;

	
	//Page Methods
	public String getProdTitle() {
		DriverUtils.visibleWait(prodNameEle);
		return prodNameEle.getText();
	}

	public void addSize(String Size) {
		switch (Size) {
		case "xs":
			DriverUtils.scrollIntoView(sizeXS);
			sizeXS.click();
			break;
		case "s":
			DriverUtils.scrollIntoView(sizeS);
			sizeS.click();
			break;
		case "m":
			DriverUtils.scrollIntoView(sizeM);
			sizeM.click();
			break;
		case "l":
			DriverUtils.scrollIntoView(sizeL);
			sizeL.click();
			break;
		case "xl":
			DriverUtils.scrollIntoView(sizeXL);
			sizeXL.click();
			break;
		}
	}

	public void addColor(String Color) {
		switch (Color) {
		case "yellow":
			DriverUtils.scrollIntoView(yellowCol);
			yellowCol.click();		
			break;
		case "orange":
			DriverUtils.scrollIntoView(orangeCol);
			orangeCol.click();
			break;
		case "red":
			DriverUtils.scrollIntoView(redCol);
			redCol.click();
			break;	
		}
	}

	public String getCartMsg() {
		DriverUtils.scrollIntoView(addToCartBtn);
		addToCartBtn.click();
		logger.info("AddtoCart clicked");
		
		DriverUtils.visibleWait(cartSuccessMsg);
		DriverUtils.scrollIntoView(cartSuccessMsg);

		return cartSuccessMsg.getText();
	}

	public boolean validateMiniCart() {
		driver.navigate().refresh();
		DriverUtils.justWait(5);
		DriverUtils.clickWait(miniCartBtn);
		miniCartBtn.click();
		logger.info("MiniCart clicked");
		DriverUtils.visibleWait(checkOutBtn);

		return checkOutBtn.isDisplayed();
	}

	public ShippingPage proceedCheckout() {
		checkOutBtn.click();	
		return new ShippingPage(driver);
	}
}