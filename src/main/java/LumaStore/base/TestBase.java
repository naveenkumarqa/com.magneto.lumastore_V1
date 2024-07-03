package LumaStore.base;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import LumaStore.utilConstants.ConfigProp;
import LumaStore.utilConstants.FilePaths;
import LumaStore.utils.ReadProperty;
import LumaStore.utils.DriverUtils;

public class TestBase {

	public static Properties prop;
	protected static WebDriver driver;
	public static WebDriverWait wait;
	public static Logger logger;
	public static ExtentReports report;
	private String batchRunID = String.valueOf(System.currentTimeMillis());
	private File sparkConfigFile = new File(FilePaths.REPORTCONFIG.getPath());

	@BeforeSuite
	public void launch() throws Exception {
		// Logger initialization
		logger = LogManager.getLogger(TestBase.class.getName());

		// Driver initialization
		initializeDriver();

		// Wait initialization
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Report initialization
		String timestamp = DriverUtils.getCurrentTime();
		String reportPath = FilePaths.REPORT.getPath(timestamp);
		// use this for generic path (jenkins)
		//String reportPath = "LumaStore_Report.html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		report = new ExtentReports();
		report.attachReporter(sparkReporter);

		// Report properties setup
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		report.setSystemInfo("HostName", "LocalHost");
		report.setSystemInfo("Env", "QA");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("Java version", System.getProperty("java.version"));
		report.setSystemInfo("Browser name", cap.getBrowserName());
		report.setSystemInfo("Browser version", cap.getBrowserVersion());
		sparkReporter.loadJSONConfig(sparkConfigFile);
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized");
		}
		return driver;
	}

	private void initializeDriver() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(ReadProperty.getValue(ConfigProp.URL));
		logger.info("Batch Run:" + batchRunID);
	}

	@AfterSuite
	public void tearDown() {
		DriverUtils.justWait(5);
		driver.quit();
		logger.info("Batch Run:" + batchRunID + " completed");
	}
}