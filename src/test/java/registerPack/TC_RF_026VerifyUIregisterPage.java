package registerPack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_026VerifyUIregisterPage {
	@Test
	public void uiChecklistRegisterpage() throws IOException  {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir")+"\\Screenshots\\ActUI.png");
		
		Assert.assertFalse(CommonUtilities.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\ActUI.png", System.getProperty("user.dir")+"\\Screenshots\\ExpUI.png"));
		
		driver.quit();
		
	}

}
