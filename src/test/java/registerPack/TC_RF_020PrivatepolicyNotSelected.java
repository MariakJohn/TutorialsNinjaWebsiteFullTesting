package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TC_RF_020PrivatepolicyNotSelected {
	
	@Test
	public void verifyRegAccountPrivatePlicy() {
		//WebDriver driver= new ChromeDriver();
		// Set ChromeOptions
	    ChromeOptions co = new ChromeOptions();
	    co.addArguments("--remote-allow-origins=*");

	    // Initialize WebDriver with ChromeOptions
	    WebDriver driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	
		
		driver.findElement(By.xpath("//input[@name='agree']")).isEnabled();
		
		driver.quit();
		
	}

}
