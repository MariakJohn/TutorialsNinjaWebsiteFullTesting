package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_012_UsingKeyboardKeysRegister {

	WebDriver driver =new ChromeDriver();
	
	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void registerUsingKeybordKeys() throws InterruptedException {
		
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		Actions actions =new Actions(driver);
		
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(4)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(3)).sendKeys(Keys.ENTER).build().perform();
		
		for (int i = 0; i < 23; i++) {
			actions.sendKeys(Keys.TAB).perform();
			
		}
		actions.sendKeys("Maria").sendKeys(Keys.TAB).sendKeys("Jo").sendKeys(Keys.TAB)
		.sendKeys(CommonUtilities.generateBrandnewEmail()).sendKeys(Keys.TAB)
		.sendKeys("1236547893").sendKeys(Keys.TAB).sendKeys("JoJo").sendKeys(Keys.TAB)
		.sendKeys("JoJo").sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB)
		.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
		driver.quit();
	}
}
