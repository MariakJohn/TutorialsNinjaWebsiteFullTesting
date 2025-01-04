package registerPack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CommonUtilities;

public class TC_RF_001 {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized", "--incognito", "--disable-notifications");
		driver = new ChromeDriver(chromeOptions);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void ScreenShot(WebDriver driver, String test) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./Screenshots/" + test + ".png"));

	}

	@Test
	public void verifyAccountUsingMandatoryDetails() throws IOException, InterruptedException {

		// Automatically set up ChromeDriver
		WebDriverManager.chromedriver().setup();

		// Configure ChromeOptions
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--disable-notifications");

		// Initialize WebDriver with ChromeOptions
		driver = new ChromeDriver(chromeOptions);

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40000));
		// System.out.println("Navigating to the URL:
		// https://tutorialsninja.com/demo/");

		try {
			driver.get("https://tutorialsninja.com/demo/");

			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Register")).click();
			ScreenShot(driver, "Registeration page");

			driver.findElement(By.id("input-firstname")).sendKeys("Maria");
			driver.findElement(By.id("input-lastname")).sendKeys("Vince");

			driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
			driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
			driver.findElement(By.id("input-password")).sendKeys("fdufilee");
			driver.findElement(By.id("input-confirm")).sendKeys("fdufilee");

			driver.findElement(By.xpath("//input[@name='agree']")).click();
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

			Assert.assertTrue(
					driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());

			String ExpHeading = "Your Account Has Been Created!";
			String ActHeading = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"))
					.getText();
			Assert.assertEquals(ExpHeading, ActHeading);
			ScreenShot(driver, "Registered Successfully");

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
			ScreenShot(driver, "Account page");
			Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		} catch (Exception e) {
			System.err.println("Test failed: " + e.getMessage());
		}
	}
}
