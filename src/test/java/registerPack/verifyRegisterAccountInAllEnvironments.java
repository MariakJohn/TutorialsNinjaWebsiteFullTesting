package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class verifyRegisterAccountInAllEnvironments {
	WebDriver driver;
	@Test
	public void RegisterAccountInAllEnvironments() {
		
		String browserName="chrome";
		if (browserName.equals("chrome")) {
		 driver=new ChromeDriver();
	}else if
		 (browserName.equals("firefox")) {
			 driver=new FirefoxDriver();
		}else if
			 (browserName.equals("edge")) {
				 driver=new EdgeDriver();
			}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("MAria");
		driver.findElement(By.id("input-lastname")).sendKeys("Jo");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("04565448696");
		driver.findElement(By.id("input-password")).sendKeys("asdfghj");
		driver.findElement(By.id("input-confirm")).sendKeys("asdfghj");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
	}
}
