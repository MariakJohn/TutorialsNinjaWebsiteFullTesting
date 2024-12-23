package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_017PasswordComplexityStandards {
	WebDriver driver;
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	
	@Test(dataProvider="passwordSupplier")
	public void verifyPasswordComplexityStandards(String passwordText ) {
		 driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("MAria");
		driver.findElement(By.id("input-lastname")).sendKeys("K");
		driver.findElement(By.id("input-email")).sendKeys(
				CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1223654789");
		driver.findElement(By.id("input-password")).sendKeys(passwordText);
		driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String exppass = "Enter pasword which follows password complexity Standard!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-siblings::div")).getText(),exppass);
		
		
		
	}
	@DataProvider(name="passwordSupplier")
	public Object[][] supplypasswords() {
		Object[][] data= {{"123465"},{"asdfg123"},{"ASD1233"},{"asdddf!@#$$%"}};
		return data;
	}

}
