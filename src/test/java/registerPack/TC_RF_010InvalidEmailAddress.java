package registerPack;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_010InvalidEmailAddress {

	@Test
	public void invalidEmailAddress() {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("dfgfhg");
		driver.findElement(By.id("input-lastname")).sendKeys("sdfghj");
		driver.findElement(By.id("input-email")).sendKeys("asdfg");
		driver.findElement(By.id("input-telephone")).sendKeys("1236547898");
		driver.findElement(By.id("input-password")).sendKeys("123");
		driver.findElement(By.id("input-confirm")).sendKeys("123");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		// Check for validation message
		String ExpWarningMsg = "Please include an '@' in the email address. 'asdfg' is missing an '@'.";
		String ActualWarningMsg = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		//System.out.println(ActualWarningMsg);
		Assert.assertEquals(ActualWarningMsg, ExpWarningMsg);
		
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("asdfg@");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ExpWarningMsg1 = "Please enter a part following '@'. 'asdfg@' is incomplete.";
		String ActualWarningMsg1 = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		Assert.assertEquals(ActualWarningMsg1, ExpWarningMsg1);

		
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("asdfg@gmail");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ExpWarningMsg2 = "E-Mail Address does not appear to be valid!";
		String ActualWarningMsg2 = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		//System.out.println(ActualWarningMsg2);
		Assert.assertEquals(ActualWarningMsg2, ExpWarningMsg2);
		driver.quit();
	}

}
