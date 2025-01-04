package pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	private WebElement logOutButton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	private WebElement successMessage;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement successMessagebreadcrumb;
	
	
	@FindBy(id="content")
	private WebElement accountSuccessContent;
	
	@FindBy(linkText="Continue")
	private WebElement continueButton;
	
	@FindBy(linkText="Newsletter")
	private WebElement accountSuccessnewsLetterButton;
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	private WebElement SubscribeNunsubscribe;
	
	///////Placeholders
	@FindBy(id="input-firstname")
	private WebElement firstnamePlaceholdersField;
	
	@FindBy(id="input-lastname")
	private WebElement lastnamePlaceholdersField;
	
	@FindBy(id="input-email")
	private WebElement emailPlaceholdersField;
	
	@FindBy(id="input-telephone")
	private WebElement telephonePlaceholdersField;
	
	@FindBy(id="input-password")
	private WebElement passwordPlaceholdersField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPlaceholdersField;
	
	
	public String  passwordconfirmPlaceholders() {
		return confirmPlaceholdersField.getDomAttribute("placeholder");
	}
	
	public String  passwordPlaceholders() {
		return passwordPlaceholdersField.getDomAttribute("placeholder");
	}
	
	public String  telephonePlaceholders() {
		return telephonePlaceholdersField.getDomAttribute("placeholder");
	}
	
	public String  emailPlaceholders() {
		return emailPlaceholdersField.getDomAttribute("placeholder");
	}
	
	public String  lastnamePlaceholders() {
		return lastnamePlaceholdersField.getDomAttribute("placeholder");
			}
	
	public String  firstnamePlaceholders() {
		return firstnamePlaceholdersField.getDomAttribute("placeholder");
		}
	
	public NewsletterSubscriptionPage SubscribeNunsubscribeButton() {
		 SubscribeNunsubscribe.click();
		 return new NewsletterSubscriptionPage(driver);
	}
	
	public boolean accountSuccessnewsLetter() {
		return accountSuccessnewsLetterButton.isDisplayed();
	}
	
	public MyAccountPage  clickContinue() {
		continueButton.click();
		return new MyAccountPage(driver);
	}
	
		public String successContent() {
		return accountSuccessContent.getText();
	}
	
	public String navigateToSuccess() {
		return successMessage.getText();
	}
	
	public boolean successbreadcrumb() {
		return successMessagebreadcrumb.isDisplayed();
	}
	
	public boolean isLogoutdisplayed() {
		return logOutButton.isDisplayed();
		
	}
	
	

}
