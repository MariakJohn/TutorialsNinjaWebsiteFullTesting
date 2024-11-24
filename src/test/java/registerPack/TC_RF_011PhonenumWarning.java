package registerPack;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_011PhonenumWarning {
	WebDriver driver;
	
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void invalidPhonenum() {

		 driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("dfgfhg");
		driver.findElement(By.id("input-lastname")).sendKeys("sdfghj");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("dfghkj23");
		driver.findElement(By.id("input-password")).sendKeys("123");
		driver.findElement(By.id("input-confirm")).sendKeys("123");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		
		String ExpMessg = "Invalid Phone number.";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")),ExpMessg);
}
}
