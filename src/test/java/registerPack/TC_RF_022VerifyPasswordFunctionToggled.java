package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_022VerifyPasswordFunctionToggled {
	
	@Test
	public void PasswordFunctionToggled() {
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		Assert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("type"), "password");
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("type"), "password");
		
	driver.quit();
	
	}

}
