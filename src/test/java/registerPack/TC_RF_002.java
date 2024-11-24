package registerPack;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_002 {
	@Test
	public void verifyThankyouMail() {

		WebDriver driver = new ChromeDriver();
		String URL = "https://www.amazon.com/";// EvidenceScreenshot
		driver.get(URL);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//span[@class='nav-line-2 ']")).click();
		//driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.className("a-expander-prompt")).click();
		driver.findElement(By.id("auth-fpp-link-bottom")).click();
		driver.findElement(By.id("ap_email")).sendKeys("manudliff@gmail.com");
		driver.findElement(By.id("a-autoid-0")).click();
		
		
		// driver.quit();
	}

}
