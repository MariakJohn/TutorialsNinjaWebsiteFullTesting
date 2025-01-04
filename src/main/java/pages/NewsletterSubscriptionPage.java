package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsletterSubscriptionPage {
	WebDriver driver;
	
	public NewsletterSubscriptionPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Newsletter")
	private WebElement newsletterSubscription;

	@FindBy(xpath="//input[@value='0']")
	private WebElement newsletterSubscriptionNo;
	
	public void newsletterSubscriptionCheck() {
		newsletterSubscriptionNo.isSelected();
	}
	
	public void newsletterSubscriptionvisible() {
		newsletterSubscription.isDisplayed();
	}
	
	
}
