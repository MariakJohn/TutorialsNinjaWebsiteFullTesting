package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonUtilities;

public class TC_RF_019VerifyLeadingAndTrailingSpacesWhileRegisteringAccountWhileRegisteringAccount {

	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test
	public void verifyLeadingAndTrailingSpacesWhileRegistering() {
		
		SoftAssert softAssert=new SoftAssert();
				driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.get("https://tutorialsninja.com/demo/");

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		
		String firstname = "  Maria ";
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
		String lastname = "  Jo   ";
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		String text ="    "+ CommonUtilities.generateBrandnewEmail()+"         ";
		driver.findElement(By.id("input-email")).sendKeys(text);
		
		String telephone = "     1234567898   ";
		driver.findElement(By.id("input-telephone")).sendKeys(telephone);
		String password = "  gfgjhklghjk   ";
		driver.findElement(By.id("input-password")).sendKeys(password);
		String confirm = "  gfgjhklghjk   ";
		driver.findElement(By.id("input-confirm")).sendKeys(confirm);
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("value"), firstname.trim());
	}

}
