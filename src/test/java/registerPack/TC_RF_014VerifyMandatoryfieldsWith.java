package registerPack;

import java.awt.Window;
import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_014VerifyMandatoryfieldsWith {
	WebDriver driver;
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void verifyAllMandatoryFields() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String ExpCont = "\"* \"";
		String Expcolor = "rgb(255, 0, 0)";
		
		WebElement firstName = driver.findElement(By.cssSelector("label[for='input-firstname']"));
		WebElement LastName = driver.findElement(By.cssSelector("label[for='input-lastname']"));
		WebElement email = driver.findElement(By.cssSelector("label[for='input-email']"));
		WebElement phone = driver.findElement(By.cssSelector("label[for='input-telephone']"));
		WebElement password = driver.findElement(By.cssSelector("label[for='input-password']"));
		
		
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		Object firstNameLabelContent = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", firstName);
		System.out.println(firstNameLabelContent);
		Assert.assertEquals(firstNameLabelContent, ExpCont);
		Object firstNameLabelColor = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", firstName);
		System.out.println(firstNameLabelColor);
		Assert.assertEquals(Expcolor, firstNameLabelColor);
	
		Object LastNameLabelContent = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", LastName);
		Assert.assertEquals(LastNameLabelContent, ExpCont);
		Object LastNameLabelColor = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", LastName);
		Assert.assertEquals(Expcolor, LastNameLabelColor);
		
		Object emailNameLabelContent = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", email);
		Assert.assertEquals(emailNameLabelContent, ExpCont);
		Object emailtNameLabelColor = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", email);
		Assert.assertEquals(Expcolor, emailtNameLabelColor);
		

		Object PhoneLabelContent = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", phone);
		Assert.assertEquals(PhoneLabelContent, ExpCont);
		Object PhoneLabelColor = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", phone);
		Assert.assertEquals(Expcolor, PhoneLabelColor);
		
		Object PasswordLabelContent = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", password);
		Assert.assertEquals(PasswordLabelContent, ExpCont);
		Object PasswordLabelColor = jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", password);
		Assert.assertEquals(Expcolor, PasswordLabelColor);
		
	}
}
