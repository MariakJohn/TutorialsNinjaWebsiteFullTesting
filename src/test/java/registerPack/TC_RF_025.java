package registerPack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_025 {
	WebDriver driver;

	@Test
	public void verifyURLtitle() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String expTitle = "Register Account";
		Assert.assertEquals(driver.getTitle(), expTitle);

		String ExpURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(driver.getCurrentUrl(), ExpURL);

		Assert.assertTrue(
				driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Register Account");

		driver.quit();
	}

}
