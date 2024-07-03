package LumaStore.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import LumaStore.base.TestBase;
import LumaStore.utilConstants.ConfigProp;
import LumaStore.utilConstants.FilePaths;

public class ReportListeners implements ITestListener {

	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	private static WebDriver driver;

	
	@Override
	public void onStart(ITestContext context) {
		extentReports = TestBase.report;
	}

	@Override
	public void onTestStart(ITestResult result) {
		try {
			// Fetch values from properties file
			String author = ReadProperty.getValue(ConfigProp.AUTHOR);
			String device = ReadProperty.getValue(ConfigProp.DEVICE);
			String category = ReadProperty.getValue(ConfigProp.CATEGORY);
			String browser = ReadProperty.getValue(ConfigProp.BROWSER);

			// Assign values to the test
			extentTest = extentReports.createTest(result.getMethod().getMethodName())
					.assignAuthor(author)
					.assignDevice(device)
					.assignCategory(category)
					.assignCategory(browser);
		} catch (Exception e) {
			extentTest = extentReports.createTest(result.getMethod().getMethodName());
			extentTest.log(Status.FAIL, "Failed to assign test metadata: " + e.getMessage());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
		captureScreenshot();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
		extentTest.log(Status.FAIL, result.getThrowable());
		captureScreenshot(result); 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

	//method to log any info message in extent report
	public static void logMessage(String message) {
		extentTest.log(Status.INFO, message);
	}

	//method to capture screenshot and attach to report without saving--PASS scenario
	private void captureScreenshot() {
		driver = TestBase.getDriver();

		//check to ensure driver is not null before attempting to capture the screenshot
		if (driver == null) {
			extentTest.log(Status.FAIL, "Failed to capture screenshot: WebDriver is null");
			return;
		}

		try {
			String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			extentTest.addScreenCaptureFromBase64String(screenshot, "Screenshot");
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
		}
	}

	//method to create a file and save screenshots--FAIL scenario
	private void captureScreenshot(ITestResult result) {
		driver = TestBase.getDriver();

		//check to ensure driver is not null before attempting to capture the screenshot
		if (driver == null) {
			extentTest.log(Status.FAIL, "Failed to capture screenshot: WebDriver is null");
			return;
		}

		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String methodName = result.getMethod().getMethodName();
			String timestamp = DriverUtils.getCurrentTime();

			// Generate the screenshot path
			String screenshotPath = FilePaths.SCREENSHOT.getFormattedPath(methodName, timestamp);

			// Create the directories if they do not exist
			File destinationFile = new File(screenshotPath);
			if (destinationFile.getParentFile() != null) {
				destinationFile.getParentFile().mkdirs();
			}

			// Save the screenshot
			FileUtils.copyFile(screenshotFile, destinationFile);

			// Log and attach the screenshot to the report
			extentTest.log(Status.INFO, "Screenshot saved at " + destinationFile.getAbsolutePath());
			extentTest.addScreenCaptureFromPath(destinationFile.getAbsolutePath());

		} catch (IOException e) {
			extentTest.log(Status.FAIL, "Failed to save screenshot: " + e.getMessage());
			e.printStackTrace();
		}
	}
}