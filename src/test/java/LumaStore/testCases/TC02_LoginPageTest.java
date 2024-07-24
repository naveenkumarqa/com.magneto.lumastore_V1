package LumaStore.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.HomePage;
import LumaStore.pageobjects.IndexPage;
import LumaStore.pageobjects.LoginPage;

public class TC02_LoginPageTest extends TestBase {

	private LoginPage lp;
	private IndexPage ip;
	private HomePage hp;
	
	@BeforeMethod
	public void LoginPage() {
		lp = new LoginPage(driver);
		ip = new IndexPage(driver);
		hp = new HomePage (driver);
	}
	
	@Test
	public void verifyLogin() throws Exception  {
		//Clicking SignIn
		lp = ip.clickSignIn();
		logger.info("Signin link clicked");
		
		//Validate Loginpage
		String Cust_Login = lp.validateLoginPage();
		Assert.assertTrue(Cust_Login.contains("Login"),"Login Page navigated failed");
		logger.info("Login page navigated");

		//Entering user creds
		lp.enterCreds();

		//Validate Login
		boolean isLoginSuccess = hp.validateLogin();
		Assert.assertTrue(isLoginSuccess,"Login validation failure");
	}
		
}