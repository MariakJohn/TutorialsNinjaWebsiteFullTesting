package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_006 {
	
	@Test
	public void verifyRegisteringAccountByNotSubscribingNewsletter() {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Maria");
		driver.findElement(By.id("input-lastname")).sendKeys("Jo");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1236547897");
		driver.findElement(By.id("input-password")).sendKeys("Jojo");
		driver.findElement(By.id("input-confirm")).sendKeys("Jojo");
		
		driver.findElement(By.xpath("//input[@value=0]")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		driver.findElement(By.linkText("Newsletter")).isDisplayed();
		driver.findElement(By.xpath("//input[@value='0']")).isSelected();
		
		
		
		driver.quit();
		
	}

}
