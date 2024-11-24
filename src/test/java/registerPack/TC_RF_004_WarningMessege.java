package registerPack;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_004_WarningMessege {
	WebDriver driver = new ChromeDriver();

	public void ScreenShot(WebDriver driver, String test) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("/SHWarningMSG" + test + ".png"));

	}

	@Test
	public void checkWarningMessagesForFiirstname() {

		// driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// check warning
		String ExpText1 = "First Name must be between 1 and 32 characters!";
		// String ActText1 = driver.findElement(By.xpath("//div[contains(text(),'First
		// Name must be between 1 and 32 characters!')]")).getText();
		Assert.assertEquals(ExpText1,driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText());
		
		  
		  String ExpText2 = "Last Name must be between 1 and 32 characters!"; 
		  Assert.assertEquals(ExpText2, driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText());
		  
		  String ExpText3 = "E-Mail Address does not appear to be valid!"; 
		  Assert.assertEquals(ExpText3, driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText());
		  
		  String ExpText4 = "Telephone must be between 3 and 32 characters!"; 
		  Assert.assertEquals(ExpText4, driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText());
		  
		  String ExpText5="Password must be between 4 and 20 characters!"; 
		  Assert.assertEquals(ExpText5, driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText());
		  
		 String ExpText6="Warning: You must agree to the Privacy Policy!";
		 Assert.assertEquals(ExpText6, driver.findElement(By.xpath("//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")).getText());
		  
		  
		 

		driver.quit();
	}

}
