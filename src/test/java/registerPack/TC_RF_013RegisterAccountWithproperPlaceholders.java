package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_013RegisterAccountWithproperPlaceholders {
	
	WebDriver driver;
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void AccountWithproperPlaceholders() {
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String ExpPlaceHolderFName = "First Name";
		String ExpPlaceHolderLName = "Last Name";
		String ExpPlaceHolderEMail="E-Mail";
		String ExpPlaceHolderPhNumber="Telephone";
		String ExpPlaceHolderPassWord="Password";
		String ExpPlaceHolderconPass="Password Confirm";
		
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), ExpPlaceHolderFName);
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"), ExpPlaceHolderLName);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), ExpPlaceHolderEMail);
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"), ExpPlaceHolderPhNumber);
		Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"), ExpPlaceHolderPassWord);
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"), ExpPlaceHolderconPass);
	}

}
