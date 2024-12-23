package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
public class CommonUtilities {
	
	static WebDriver driver=new ChromeDriver();

	
		public static String  generateBrandnewEmail() {
			Date date = new Date();
			String dateString = date.toString();
			
			String dateWithoutSpace = dateString.replaceAll("\\s", "");		
			String dateWithoutSpaceColon = dateWithoutSpace.replaceAll("\\:","");
			String mail = "maria"+dateWithoutSpaceColon+"@gmail.com";
			return mail;
		}
		
			
		
		public static  void screenshot(WebDriver driver, String test) {
			
			
			TakesScreenshot screen = (TakesScreenshot) driver;
			File srcFile=screen.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("'/ScreenShotNinjaDemo/"+test+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public static void takeScreenshot(WebDriver driver,String screenshotPath) {
	        
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
			
			try {
				FileHandler.copy(srcScreenshot,new File(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 public static boolean compareTwoScreenshots(String actualImagePath,String expectedImagePath) throws IOException {
				
				BufferedImage acutualBImg = ImageIO.read(new File(actualImagePath));
				BufferedImage expectedBImg = ImageIO.read(new File(expectedImagePath));
				
				ImageDiffer imgDiffer = new ImageDiffer();
				ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);
				
				return imgDifference.hasDiff();
			
			
		}
}
