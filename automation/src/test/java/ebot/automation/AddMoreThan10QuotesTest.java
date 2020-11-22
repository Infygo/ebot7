package ebot.automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.Initialisation;

public class AddMoreThan10QuotesTest extends Initialisation {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(AddMoreThan10QuotesTest.class.getName());

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException {

		driver = initialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void AddMoreThanTenQuotesTest() {
		log.info("Automating trying to add more than 10 quotes for an author");
		String applicationUrl = "https://www-5fb2340da48f252fcc5aa6c9.recruit.eb7.io/";
		String quoteEleven = "This is my 11th Quote -trying to add”";
		try {
			driver.get(applicationUrl);
			WebElement addNewQuote = driver.findElement(By.xpath("//button[@id='show-modal']"));
			log.info("Trying to add 11th quote for an author having 10 quotes already");
			addNewQuote.click();
			driver.findElement(By.xpath("//input[@id='autorInput']")).sendKeys("Gideon-TestCheck");
			driver.findElement(By.xpath("//input[@id='quoteInput']")).sendKeys(quoteEleven);
			driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
			driver.navigate().refresh();
			String noOfQuotes = driver.findElement(By.xpath("//li[contains(text(),'Gideon-TestCheck ')]")).getText();
			System.out.println(noOfQuotes);
			Assert.assertTrue(noOfQuotes.contains("11"), "Checking if more than 10 quotes can be added to an author");
		} 
		catch (Exception e) {
			// TODO: handle exception
			log.error("Exception on not more than 10 quotes could be added:", e);
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
