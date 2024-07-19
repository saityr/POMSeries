package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;

public class DriverFactory {
	Properties prop;
	WebDriver driver;
	public static String highlight;
	OptionsManager om;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		highlight = prop.getProperty("highlight");
		om = new OptionsManager(prop);

		String bn = prop.getProperty("browser");
		switch (bn.toLowerCase().trim()) {
		case "chrome":
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
				// run on grid
				initRemoteDriver("chrome");
				}
			else {
				

				// driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver());
			
			}
			break;
		case "firefox":
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("pls pass correct driver");
			throw new BrowserExceptions(AppError.BROWSER_NOT_FOUND);

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}
	
	private void initRemoteDriver(String browserName) {
		System.out.println("Rnning it on GRID...with browser: " + browserName);

		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), om.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), om.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), om.getEdgeOptions()));
				break;

			default:
				System.out.println("plz pass the right browser on grid..");
				throw new BrowserExceptions(AppError.BROWSER_NOT_FOUND);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init() {

		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		prop = new Properties();
		FileInputStream fis = null;

		if (envName == null) {
			System.out.println("env name is not given, hence running it on QA environment....");
			try {
				fis = new FileInputStream("./src/test/resources/config/qa.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/qa.properties");
					break;
				case "stage":
					fis = new FileInputStream("./src/test/resources/config/stage.properties");
					break;
				case "config":
					fis = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp location

		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
