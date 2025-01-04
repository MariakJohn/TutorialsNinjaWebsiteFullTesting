package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderOptions {
	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement MyAccountDropMenu;

	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement breadcrumbRegister;

	@FindBy(linkText = "Register")
	private WebElement RegisterAccount;
	
	@FindBy(linkText = "Login")
	private WebElement loginAccount;
	
	

	
	public AccountLogin ClickOnLogin() {
		loginAccount.click();
		return new AccountLogin(driver);
	}
	
	public RegisterPage ClickOnRegister() {
		RegisterAccount.click();
		return new RegisterPage(driver);
	}

	public boolean breadcrumbRegisterisDisplayed() {
		return breadcrumbRegister.isDisplayed();
			}
	
	public void clickOnMyAccount() {
		MyAccountDropMenu.click();
	}

}
