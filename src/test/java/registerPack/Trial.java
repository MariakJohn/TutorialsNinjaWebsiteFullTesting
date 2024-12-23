package registerPack;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Trial {
	@Test
	public void navi() throws InterruptedException {
		
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.manage().window().maximize();

	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Register")).click();

	// Phone icon
	driver.findElement(By.xpath("//i[@class='fa fa-phone']")).click();
	Assert.assertEquals(driver.getTitle(), "Contact Us");
	Thread.sleep(30);
	driver.navigate().back();

	// Wishlist
	driver.findElement(By.xpath("//i[@class='fa fa-heart']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Wishlist
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
	// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='hidden-xs
	// hidden-sm hidden-md'][contains(text(),'Wish List')]"))).click();
	driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(),'Wish List')]"))
			.click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Shopping Cart
	driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(),'Shopping Cart')]"))
			.click();
	Assert.assertEquals(driver.getTitle(), "Shopping Cart");
	Thread.sleep(30);
	driver.navigate().back();

	// Shopping Cart
	driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();
	Assert.assertEquals(driver.getTitle(), "Shopping Cart");
	Thread.sleep(30);
	driver.navigate().back();

	// Checkout
	driver.findElement(By.linkText("Checkout")).click();
	Assert.assertEquals(driver.getTitle(), "Shopping Cart");
	Thread.sleep(30);
	driver.navigate().back();

	// Check out
	driver.findElement(By.xpath("//i[@class='fa fa-share']")).click();
	Assert.assertEquals(driver.getTitle(), "Shopping Cart");
	Thread.sleep(30);
	driver.navigate().back();

	// Qafox.com
	driver.findElement(By.linkText("Qafox.com")).click();
	Assert.assertEquals(driver.getTitle(), "Your Store");
	Thread.sleep(30);
	driver.navigate().back();

	// Search
	driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	Assert.assertEquals(driver.getTitle(), "Search");
	Thread.sleep(30);
	driver.navigate().back();
	
	 // Home button
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement element = driver.findElement(By.xpath("//i[@class='fa fa-home']"));
	js.executeScript("arguments[0].click();", element);
	Assert.assertEquals(driver.getTitle(), "Your Store");
	driver.navigate().back();

	// Account
	Thread.sleep(3000);
	driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Account']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Login
	driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Register']")).click();
	Assert.assertEquals(driver.getTitle(), "Register Account");
	Thread.sleep(30);
	driver.navigate().back();

	// Login
	driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Login']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Register
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Register']")).click();
	Assert.assertEquals(driver.getTitle(), "Register Account");
	Thread.sleep(30);

	// Forgot password
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Forgotten Password']")).click();
	Assert.assertEquals(driver.getTitle(), "Forgot Your Password?");
	Thread.sleep(30);
	driver.navigate().back();

	// My Account
	Thread.sleep(3000); // Wait for 3 seconds
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='My Account']")).click();

	// driver.findElement(By.xpath("//a[@class='list-group-item'][text()='My
	// Account']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Address Book
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Address Book']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Wish List
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Wish List']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Order History
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Order History']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Downloads
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Downloads']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Recurring payments
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Recurring payments']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Reward Points
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Reward Points']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Returns
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Returns']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Transactions
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Transactions']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// Newsletter
	driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Newsletter']")).click();
	Assert.assertEquals(driver.getTitle(), "Account Login");
	Thread.sleep(30);
	driver.navigate().back();

	// About Us
	driver.findElement(By.linkText("About Us")).click();
	Assert.assertEquals(driver.getTitle(), "About Us");
	Thread.sleep(30);
	driver.navigate().back();

	driver.quit();
	}

}
