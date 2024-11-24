package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_008DifferentPasswords {
	
	@Test
	public void VerifydifferentwaysofnavigatingtoRegisterAccountpage()  {
	
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		driver.get("https://tutorialsninja.com/demo");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Mar");
		driver.findElement(By.id("input-lastname")).sendKeys("Jo");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("12345678901");
		driver.findElement(By.id("input-password")).sendKeys("qwert");
		driver.findElement(By.id("input-confirm")).sendKeys("123365");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='agree']")).isSelected());
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String ExpTxt = "Password confirmation does not match password!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText(),ExpTxt);
		
		driver.quit();
	}

}
