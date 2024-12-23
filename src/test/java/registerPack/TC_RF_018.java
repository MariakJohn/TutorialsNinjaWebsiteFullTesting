package registerPack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_018 {

	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();

	}

	@Test
	public void verifyHtWtNumberofChar() throws InterruptedException, IOException{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		driver.get("https://tutorialsninja.com/demo/");

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();

		String ExpHT = "34px";
		String ExpWdth = "701.25px";

		// FIRST NAME

		System.out.println("FIRST NAME");
		WebElement firstNameField = driver.findElement(By.id("input-firstname"));
		String ActfirstNameFieldHT = firstNameField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActfirstNameFieldHT);
		String ActFirstNameWidth = firstNameField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActFirstNameWidth);

		Thread.sleep(30);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String ExpFNameWarning = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(),
				ExpFNameWarning);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement firstNameField1 = driver.findElement(By.id("input-firstname"));
		firstNameField1.sendKeys("Asdfgjkkll");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isWarningDisplayed = driver
				.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")).size() > 0;
		Assert.assertFalse(isWarningDisplayed, "Warning message should not be displayed for valid input!");

		WebElement firstNameField11 = driver.findElement(By.id("input-firstname"));
		firstNameField11.clear();
		firstNameField11.sendKeys("Asdfgjkkllllllllllllllllllllllllllllmmmmmmm");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isWarningDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")) != null;
		Assert.assertTrue(isWarningDisplayed1, "First Name must be between 1 and 32 characters!");

		// LAST NAME
		System.out.println("LAST NAME");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement LNameField = driver.findElement(By.id("input-lastname"));
		String ActLNameFieldHT = LNameField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActLNameFieldHT);
		String ActLNameWidth = LNameField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActLNameWidth);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String ExpLNameWarning = "Last Name must be between 1 and 32 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
				ExpLNameWarning);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement LastNameField1 = driver.findElement(By.id("input-lastname"));
		LastNameField1.sendKeys("ghjkpdotp");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Thread.sleep(30);
		boolean isLWarningDisplayed = driver
				.findElements(By.xpath("//input[@id='input-lastname']/following-sibling::div")).size() > 0;
		Assert.assertFalse(isLWarningDisplayed, "Warning message should not be displayed for valid input!");

		// Thread.sleep(20);

		WebElement LNameField1 = driver.findElement(By.id("input-lastname"));
		LNameField1.clear();
		LNameField1.sendKeys("adffghjjklikjhjkjlnjghjkkjhjklhfvnbmhiklk");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isLWarningDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-lastname']/following-sibling::div")) != null;
		Assert.assertTrue(isLWarningDisplayed1, "Last Name must be between 1 and 32 characters!");

		// EMAIL
		System.out.println("EMAIL");
		WebElement EmailField = driver.findElement(By.id("input-email"));
		String ActEmailFieldHT = EmailField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActEmailFieldHT);
		String ActEmailWidth = EmailField.getCssValue("width");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String EmailWarning = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				EmailWarning);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement EmailField1 = driver.findElement(By.id("input-email"));
		EmailField1.sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isEmailDisplayed = driver.findElements(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.size() > 0;
		Assert.assertFalse(isEmailDisplayed, "Warning message should not be displayed for valid input!");

		WebElement EmailField11 = driver.findElement(By.id("input-email"));
		EmailField11.clear();
		EmailField11.sendKeys("adffghjjklikjhjkjlnjghjvvvvvvvvvvvvvvkkjhjklhfvnbmhiklk@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isEWarningDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-email']/following-sibling::div")) != null;
		Assert.assertTrue(isEWarningDisplayed1, "E-Mail Address does not appear to be valid!");

		// PHONE
		System.out.println("PHONE");
		WebElement phoneField = driver.findElement(By.id("input-telephone"));
		String ActphoneFieldHT = phoneField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActphoneFieldHT);
		String ActphoneWidth = phoneField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActphoneWidth);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String phoneFieldWarning = "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				phoneFieldWarning);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement phoneField1 = driver.findElement(By.id("input-telephone"));
		phoneField1.sendKeys("1236547898");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isPhonelDisplayed = driver
				.findElements(By.xpath("//input[@id='input-telephone']/following-sibling::div")).size() > 0;
		Assert.assertFalse(isPhonelDisplayed, "Warning message should not be displayed for valid input!");

		WebElement phoneField11 = driver.findElement(By.id("input-telephone"));
		phoneField11.clear();
		phoneField11.sendKeys("sdfghh23333333333333333333333333333333335");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isPhonelDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-telephone']/following-sibling::div")) != null;
		Assert.assertTrue(isPhonelDisplayed1, "Telephone must be between 3 and 32 characters!");

		// PASSWORD
		System.out.println("PASSWORD");
		WebElement passwordField = driver.findElement(By.id("input-password"));
		String ActPasswordHT = passwordField.getCssValue("height");
		Assert.assertEquals(ActPasswordHT, ExpHT);
		String ActPasswordwd = passwordField.getCssValue("width");
		Assert.assertEquals(ActPasswordwd,ExpWdth );
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String passwordWarning = "Password must be between 4 and 20 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				passwordWarning);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement passwordField1 = driver.findElement(By.id("input-password"));
		passwordField1.sendKeys("qwrretttttyu");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean passwordWarningStatus = false;
		try {
			passwordWarningStatus = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);

		WebElement passwordField11 = driver.findElement(By.id("input-password"));
		passwordField11.clear();
		passwordField11.sendKeys("qwrretttttydfghjkasdfghjasdzfxghjkasdfghjkasdfghjkasdfghjku");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean passwordWarningStatus1 = false;
		try {
			passwordWarningStatus1 = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus1 = false;
		}
		Assert.assertTrue(passwordWarningStatus1);
		
	//PASSWORD CONFIRM
		System.out.println("PASSWORD CONFIRM");
		WebElement passwordField111 = driver.findElement(By.id("input-confirm"));
		String ActPasswordHT1 = passwordField111.getCssValue("height");
		Assert.assertEquals(ActPasswordHT1, ExpHT);
		String ActPasswordwd1 = passwordField111.getCssValue("width");
		Assert.assertEquals(ActPasswordwd1,ExpWdth );

		
		//CONTINUE BUTTON
		WebElement contButton = driver.findElement(By.xpath("//input[@type='submit']"));
		String ActbuttonColor = contButton.getCssValue("color");
		Assert.assertEquals("rgba(255, 255, 255, 1)", ActbuttonColor);
		
		Object ActBackGColor = contButton.getCssValue("background-color");   
		Assert.assertEquals(ActBackGColor, "rgba(34, 154, 200, 1)");
		
		String ActFontSize = contButton.getCssValue("font-size");
		Assert.assertEquals(ActFontSize, "12px");
		
		
		//SCREENSHOT COMPARISON
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		CommonUtilities.screenshot(driver,"Registration Page");
		
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		//String test = null;
		//FileUtils.copyFile(srcFile, new File("./Register/"+test+".png"));
		
		try {
			FileHandler.copy(srcFile, new File(System.getProperty("user.dir")+"\\ScreenShots\\registerPageActualAligment.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

Assert.assertFalse(CommonUtilities.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\registerPageActualAligment.png", System.getProperty("user.dir")+"\\Screenshots\\registerPageExpectedAligment.png"));
		
		driver.quit();		
	}

}
