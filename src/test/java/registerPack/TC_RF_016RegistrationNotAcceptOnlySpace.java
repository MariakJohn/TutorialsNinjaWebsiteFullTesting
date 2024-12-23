package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.service.DriverFinder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_016RegistrationNotAcceptOnlySpace {
	@Test
		public void registrationWithOnlySpace() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("     ");
		driver.findElement(By.id("input-lastname")).sendKeys("     ");
		driver.findElement(By.id("input-email")).sendKeys("     ");
		driver.findElement(By.id("input-telephone")).sendKeys("      ");
		driver.findElement(By.id("input-password")).sendKeys("     ");
		driver.findElement(By.id("input-confirm")).sendKeys("     ");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	
		String ExpNamefield = "First Name must be between 1 and 32 characters!";
	String ExpLastnamefielsd = "Last Name must be between 1 and 32 characters!";
	String ExpEmailfield = "E-Mail Address does not appear to be valid!";
	String ExpTelefield = "Phone does not appear to be valid!;";
	

	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), ExpNamefield);
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), ExpLastnamefielsd);
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), ExpEmailfield);
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), ExpTelefield);
	}

}
