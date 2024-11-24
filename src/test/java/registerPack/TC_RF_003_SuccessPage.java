package registerPack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_003_SuccessPage {

	public void ScreenShot(WebDriver driver, String test) throws IOException {
		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./ScreenshotSuccessReg/"+test+".png"));
	}
	
	
	WebDriver driver= new ChromeDriver();
	
	
	@Test
	public void CheckSuccesspage() throws InterruptedException, IOException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Maria");
		driver.findElement(By.id("input-lastname")).sendKeys("Vince");
		
		//String x = "maria" + new Date().toString().replaceAll("\\s|\\:","") + "@gmail.com";
		
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("fdufilee");
		driver.findElement(By.id("input-confirm")).sendKeys("fdufilee");
	
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());

		String ExpHeading = "Your Account Has Been Created!";
		String ActHeading = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")).getText();
		Assert.assertEquals(ExpHeading, ActHeading);
		
		String ExpHeading1 = "Congratulations! Your new account has been successfully created!";
		String ExpHeading2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String ExpHeading3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String ExpHeading4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";
		
		
		System.out.println(ActHeading);
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(ExpHeading1));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(ExpHeading2));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(ExpHeading3));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(ExpHeading4));
		driver.findElement(By.linkText("Continue")).click();
		ScreenShot(driver,"Account page");
		Thread.sleep(3000);
		
	driver.quit();
		
	}
}