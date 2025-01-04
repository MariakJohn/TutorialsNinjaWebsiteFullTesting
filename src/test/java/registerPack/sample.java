package registerPack;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.junit.Test;

public class sample {
    WebDriver driver;

    @Test
    public void testRegistrationSuccessPage() {
        // Automatically set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-notifications");

        // Initialize WebDriver with ChromeOptions
        driver = new ChromeDriver(chromeOptions);

        try {
            // Open the target website
            driver.get("https://tutorialsninja.com/demo/");

            // Perform registration actions
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();
            driver.findElement(By.id("input-firstname")).sendKeys("Maria");
            driver.findElement(By.id("input-lastname")).sendKeys("Vince");
            driver.findElement(By.id("input-email")).sendKeys("maria" + System.currentTimeMillis() + "@gmail.com");
            driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
            driver.findElement(By.id("input-password")).sendKeys("password123");
            driver.findElement(By.id("input-confirm")).sendKeys("password123");
            driver.findElement(By.xpath("//input[@name='agree']")).click();
            driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

            // Validate success page
            String successHeading = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
            Assert.assertEquals("Your Account Has Been Created!", successHeading);

            System.out.println("Test passed: Registration successful!");

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
