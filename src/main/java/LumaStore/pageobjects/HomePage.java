package LumaStore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class HomePage extends TestBase {

	public HomePage(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Elements
	//Headers
	@FindBy (xpath=("(//span[@class='logged-in'])[1]"))
	WebElement LoggedIn;
	@FindBy (xpath=("//div[@class='product-item-info']"))
	List <WebElement> prodList;
	
	//Tags
	@FindBy (xpath="//span[.='Men']")
	WebElement menTag;
	@FindBy (xpath="(//span[.='Tops'])[2]")
	WebElement topsTag;
	@FindBy (xpath="(//span[.='Jackets'])[2]")
	WebElement jacketsTag;
	
	//Page Methods
	public boolean validateLogin() {		
		DriverUtils.justWait(5);
		DriverUtils.visibleWait(LoggedIn);
		return LoggedIn.isDisplayed();
	}
	
	public int productList() {
		DriverUtils.scrollIntoView(prodList.get(0));
		return prodList.size();
	}
	
	public ShoppingPage navigateProduct() {
		DriverUtils.visibleWait(menTag);
		DriverUtils.hoverTo(menTag);
		logger.info("Mens tag clicked");
		DriverUtils.hoverTo(topsTag);
		DriverUtils.hoverTo(jacketsTag);
		DriverUtils.clickWait(jacketsTag);
		jacketsTag.click();	
		return new ShoppingPage(driver);
	}
}