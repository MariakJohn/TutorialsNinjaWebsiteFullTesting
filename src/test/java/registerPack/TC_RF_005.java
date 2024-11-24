package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_005 {

	@Test
	public void newsLetterYes() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("MAria");
		driver.findElement(By.id("input-lastname")).sendKeys("K");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567898");
		driver.findElement(By.id("input-password")).sendKeys("QWERTY");
		driver.findElement(By.id("input-confirm")).sendKeys("QWERTY");

		driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();

		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		driver.findElement(By.linkText("Continue")).click();

		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][1]")).isSelected());
		
		Assert.assertTrue(driver.findElement(By.linkText("Newsletter")).isDisplayed());
		
		
	}

}
