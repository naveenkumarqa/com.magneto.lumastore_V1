package LumaStore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LumaStore.base.TestBase;
import LumaStore.utilConstants.ConfigProp;
import LumaStore.utils.ReadProperty;
import LumaStore.utils.DriverUtils;

public class LoginPage extends TestBase {

	public LoginPage (WebDriver driver){
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Page Elements
	//Buttons
	@FindBy (xpath="(//button[@id='send2'])[1]")
	WebElement SignInBtn;

	//Headers
	@FindBy (xpath="//span[@class='base' and contains(text(),'Login')]")
	WebElement custLogin;

	//TextBox
	@FindBy (id="email")
	WebElement uname;
	@FindBy (id="pass")
	WebElement pwd;


	//Page Methods
	public String validateLoginPage() {
		DriverUtils.visibleWait(custLogin);
		return custLogin.getText();
	}

	public void enterCreds() throws Exception {
		DriverUtils.visibleWait(uname);
		uname.click();
		uname.sendKeys(ReadProperty.getValue(ConfigProp.USERNAME));
		pwd.sendKeys(ReadProperty.getValue(ConfigProp.PASSWORD));
		logger.info("User creds entered");
		DriverUtils.clickWait(SignInBtn);
		SignInBtn.click();
		logger.info("Sign in clicked");
	}
}