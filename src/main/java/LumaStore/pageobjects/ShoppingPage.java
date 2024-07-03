package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class ShoppingPage extends TestBase {

	public ShoppingPage (WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Page Elements
	//Tags
	@FindBy (xpath="(//select[@id='sorter'])[1]")
	WebElement sortTag;

	//Title
	@FindBy (xpath="//span[@class='base' and contains(text(),'Jackets')]")
	WebElement pageHeader;
	@FindBy (xpath="//a[@class='product-item-link' and contains(text(),'Beaumont')]")
	WebElement prodItem;


	//Page Methods
	public String getCategoryTitle() {
		return pageHeader.getText();
	}

	public ProductPage selectProduct() {
		DriverUtils.scrollIntoView(sortTag);
		sortTag.click();
		DriverUtils.selectByText(sortTag, "Price");
		logger.info("Sorted based on Price");
		DriverUtils.visibleWait(prodItem);
		prodItem.click();
		
		return new ProductPage(driver);
	}
}