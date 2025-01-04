package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountLogin {
	
	WebDriver driver;
	
	public AccountLogin (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	private WebElement continueAccountLoginPage;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Register']")
	private WebElement registerListGroupButton;
	
	
	
	public RegisterPage registerListGroup() {
		 registerListGroupButton.click();
			return new RegisterPage(driver);

	}
	
	public RegisterPage continueAccountLoginButton() {
		continueAccountLoginPage.click();
		return new RegisterPage(driver);

	}
}
