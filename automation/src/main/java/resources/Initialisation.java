package resources;

import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Initialisation {
	public WebDriver driver;
	public Properties props;

	public WebDriver initialiseDriver(String browserName) throws IOException {
		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			ChromeOptions choptions = new ChromeOptions();
			if (browserName.contains("headless")) {
				choptions.addArguments("headless");
			}
			driver = new ChromeDriver();
		} else if (browserName.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		return driver;

	}

}
