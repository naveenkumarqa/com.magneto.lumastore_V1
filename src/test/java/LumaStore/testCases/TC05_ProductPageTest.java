package LumaStore.testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LumaStore.base.TestBase;
import LumaStore.pageobjects.ProductPage;
import LumaStore.pageobjects.ShoppingPage;
import LumaStore.utilConstants.ConfigProp;
import LumaStore.utils.ReadProperty;

public class TC05_ProductPageTest extends TestBase {
	
	private ShoppingPage sp;
	private ProductPage pp;

	@BeforeMethod
	public void ProductPage() {
		sp = new ShoppingPage(driver);
		pp = new ProductPage(driver);
	}

	//Assertions
	String prodName = "Beaumont";

	@Test
	public void verifyProduct()  {

		//sort and select the product
		pp = sp.selectProduct();
		logger.info("Sorted Product selected");

		//validate the selected product
		String prod_name = pp.getProdTitle();
		Assert.assertTrue(prod_name.contains(prodName),"Selected Product failed");
		Reporter.log(prod_name);
		logger.info("Selected Product validation");
	}

	@Test (dependsOnMethods = "verifyProduct")
	public void veriyAddtoCart() throws Exception {
		//select product Size
		pp.addSize(ReadProperty.getValue(ConfigProp.SIZE));
		logger.info("Product Size added");

		//select product Colour
		pp.addColor(ReadProperty.getValue(ConfigProp.COLOR));
		logger.info("Product Colour added");

		//validate AddtoCart
		String cart_Msg = pp.getCartMsg();
		Assert.assertTrue(cart_Msg.contains(prodName),"Cart success message error");
		logger.info("AddtoCart SuccessMsg validated");		
	}

	@Test (dependsOnMethods = "veriyAddtoCart")
	public void verifyCheckout() {
		//validate MiniCart
		boolean isMiniCart= pp.validateMiniCart();
		Assert.assertTrue(isMiniCart,"MiniCart validation failed");
		logger.info("MiniCart validated");
	}
}