package LumaStore.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import LumaStore.base.TestBase;

import org.openqa.selenium.JavascriptExecutor;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DriverUtils extends TestBase {

	public static void justWait (int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		
	}
	public static void visibleWait (WebElement loc) {
		wait.until(ExpectedConditions.visibilityOf(loc));
	}

	public static void clickWait (WebElement loc) {
		wait.until(ExpectedConditions.elementToBeClickable(loc));
	}

	public static void scrollIntoView (WebElement loc) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", loc);
	}
	
	public static void scrollClick (WebElement loc) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loc);
	}

	public static void hoverTo (WebElement loc){
		Actions builder = new Actions (driver);
		builder.moveToElement(loc).perform();				
	}

	public static void selectByText (WebElement selectloc, String textVal) {
		Select obj = new Select (selectloc);
		obj.selectByVisibleText(textVal);
	}
	
	//returns string value of currentDateTime
	public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }
}