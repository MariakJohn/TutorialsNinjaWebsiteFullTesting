package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_024VerifyWithoutConfirmPassword {
	
	WebDriver driver;
	

	@Test
	public void verifywithoutcPassword() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("MAria");
		driver.findElement(By.id("input-lastname")).sendKeys("Jo");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("04565448696");
		driver.findElement(By.id("input-password")).sendKeys("asdfghj");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), expectedWarning);
		

	}
}
