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

public class AddDuplicateQuoteTest extends Initialisation{
	
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(AddDuplicateQuoteTest.class.getName());

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException {

		driver = initialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void addDuplicateQuote() {
		log.info("Automating of trying to add a plagiarised quote into the Site");
		String applicationUrl = "https://www-5fb2340da48f252fcc5aa6c9.recruit.eb7.io/";
		String plagarisedQuote = "Emerson said, “All is riddle, and the key to a riddle is another riddle.”";
		
		driver.get(applicationUrl);
		WebElement addNewQuote = driver.findElement(By.xpath("//button[@id='show-modal']"));
		
		log.debug("Adding a new plagiarised quote");
		try {
		addNewQuote.click();
		driver.findElement(By.xpath("//input[@id='autorInput']")).sendKeys("Gideon-TestCheck");
		driver.findElement(By.xpath("//input[@id='quoteInput']")).sendKeys(plagarisedQuote);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		log.debug("Checking if the alert is triggered");
		Assert.assertTrue(driver.switchTo().alert().getText().contains("This quote violates rule 1."), "Checking if the Alert is triggerred on trying to add plagiarised quote");
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Exception on No java alert in browser:",e);
		}
		
	}
			
		
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
