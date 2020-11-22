package ebot.automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import resources.Initialisation;

public class AddGideonQuotesTest extends Initialisation {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(AddGideonQuotesTest.class.getName());

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException {
        log.debug("Initialisation of the WebDriver");
		driver = initialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void addGideonQuotes() throws IOException {
		String quotesUrl = "https://agoldoffish.wordpress.com/criminal-minds-opening-and-closing-quotes/";
		String applicationUrl = "https://www-5fb2340da48f252fcc5aa6c9.recruit.eb7.io/";
        log.info("Automating adding Gideon quotes to the Sandbox site");
		log.debug("Navigating to the Gideon Quotes url");
		driver.get(quotesUrl);
		log.debug("Converting the data into a list of WebElements");
		List<WebElement> gideon = driver.findElements(By.xpath("//p[strong='Gideon']"));
		log.debug("Using Array list to extract the quotes");
		ArrayList<String> gideonQuotes = new ArrayList<String>();
		for (int i = 0; i < gideon.size(); i++) {
			gideonQuotes.add(gideon.get(i).getText().split(":")[1]);
		}

		// Add the Gideon Quotes extracted to the application
		log.debug("Navigating to the Sandbox site to push the quotes into the server");
		driver.get(applicationUrl);
		WebElement addNewQuote = driver.findElement(By.xpath("//button[@id='show-modal']"));
		
        log.debug("Looping until all Gideon quotes are added");
		for (int i = 0; i < gideonQuotes.size(); i++) {
			addNewQuote.click();
			driver.findElement(By.xpath("//input[@id='autorInput']")).sendKeys("GideonActual");
			driver.findElement(By.xpath("//input[@id='quoteInput']")).sendKeys(gideonQuotes.get(i));
			driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		}
		log.debug("Asserting if the Gideon quotes are added");
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'GideonActual')]")).isDisplayed(),
				"Checking if the Gideon Quotes are added and tab is displayed");
		log.info("Quotes from Gideon added to the given sandbox application");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
