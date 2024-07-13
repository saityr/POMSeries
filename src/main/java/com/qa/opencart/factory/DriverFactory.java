package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;

public class DriverFactory {
	Properties prop;
	WebDriver driver;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		highlight = prop.getProperty("highlight");

		String bn = prop.getProperty("browser");
		switch (bn.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
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
