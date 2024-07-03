package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utils.DriverUtils;

public class IndexPage extends TestBase{

	public IndexPage (WebDriver driver){
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Page Elements
	//Link
	@FindBy (xpath="(//a[contains(text(),'Sign In')])[1]")
	WebElement SignInLink;

	//Header
	@FindBy (xpath="(//span[@class='not-logged-in'])[1]")
	WebElement welcome;


	//Page Methods
	public boolean validateIndexPage()  {
		DriverUtils.visibleWait(welcome);
		return welcome.isDisplayed();
	}

	public String validateIndexTitle()  {
		return driver.getTitle();
	}
	
	public LoginPage clickSignIn() {
		SignInLink.click();
		return new LoginPage(driver);
	}
}