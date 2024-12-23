package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_021_verifyAddingDataWithoutSelectingPrivatepolicy {

			
		@Test
		public void verifyRegAccountbyAddingDetailsPrivatePlicy() {
			WebDriver driver= new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			

			driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
			driver.findElement(By.linkText("My Account")).click();
			driver.findElement(By.linkText("Register")).click();
			driver.findElement(By.id("input-firstname")).sendKeys("MAria");
			driver.findElement(By.id("input-lastname")).sendKeys("K");
			driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
			driver.findElement(By.id("input-telephone")).sendKeys("1234567898");
			driver.findElement(By.id("input-password")).sendKeys("QWERTY");
			driver.findElement(By.id("input-confirm")).sendKeys("QWERTY");

			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
			String ExpMessage = "Warning: You must agree to the Privacy Policy!";
					Assert.assertEquals(ExpMessage, driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText());
			driver.quit();
	
		}
	
}
