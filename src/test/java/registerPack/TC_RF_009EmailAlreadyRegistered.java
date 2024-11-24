package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_009EmailAlreadyRegistered {
	@Test
	public void VerifydifferentwaysofnavigatingtoRegisterAccountpage()  {
	
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		driver.get("https://tutorialsninja.com/demo");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Mar");
		driver.findElement(By.id("input-lastname")).sendKeys("Jo");
		driver.findElement(By.id("input-email")).sendKeys("aria@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("12345678901");
		driver.findElement(By.id("input-password")).sendKeys("qwert");
		driver.findElement(By.id("input-confirm")).sendKeys("qwert");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='agree']")).isSelected());
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String ExpTxt = "Warning: E-Mail Address is already registered!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),ExpTxt);
		
		driver.quit();
	}

}
