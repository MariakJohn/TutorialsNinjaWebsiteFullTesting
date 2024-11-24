package utils;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
		
		public static String firstName() {
			String fName = "Maria";
			return fName;
			
			}
		public static   String lastName() {
			 String lName = "jo";
			return lName;
		}
		
		public static String phoneNumber() {
			String phone = "23456789098";
			return phone;
		}
		
		public static String passWord() {
			String passWord = "ASDFGH";
			return passWord;
		}
}
