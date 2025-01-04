package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Mandatory

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telePhoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButtonField;

	public AccountSuccessPage continueButton() {
		continueButtonField.click();
		return new AccountSuccessPage(driver);
	}

	public String getPasswordConfirmType() {
		return passwordConfirmField.getDomAttribute("type");
	}

	public String getPasswordType() {
		return passwordField.getDomAttribute("type");
	}

	public void privacyPolicyEnabled() {
		privacyPolicyField.isEnabled();
	}

	public void privacyPolicy() {
		privacyPolicyField.click();
	}

	public void passwordConfirm(String passwordConfirmText) {
		passwordConfirmField.sendKeys(passwordConfirmText);
	}

	public void password(String passworddText) {
		passwordField.sendKeys(passworddText);
	}

	public void telePhone(String telePhoneText) {
		telePhoneField.sendKeys(telePhoneText);
	}

	public void email(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterLastName(String firstNameText) {
		lastNameField.sendKeys(firstNameText);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	// Warnings######################

	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameFieldWarning;

	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameFieldWarning;

	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailFieldWarning;

	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telePhoneFieldWarning;

	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordFieldWarning;
	
	@FindBy(xpath = "//div[@class='text-danger']")
	private WebElement passwordconfFieldWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privatePolicyFieldWarning;

	public String privatePolicyWarning() {
		return privatePolicyFieldWarning.getText();
	}

	public String passwordconfWarning() {
		return passwordconfFieldWarning.getText();
	}
	
	public String passwordWarning() {
		return passwordFieldWarning.getText();
	}

	public String telePhoneWarning() {
		return telePhoneFieldWarning.getText();
	}

	public String emailWarning() {
		return emailFieldWarning.getText();
	}

	public String lastNameWarning() {
		return lastNameFieldWarning.getText();
	}

	public String firstNameWarning() {
		return firstNameFieldWarning.getText();
	}

	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordFieldNotMatchWarning;

	@FindBy(id = "input-email")
	private WebElement emailFieldWarning1;

	@FindBy(id = "input-email")
	private WebElement emailFieldfirefoxWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailFieldAlreadyRegisteredWarning;

	@FindBy(xpath = "//input[@name='newsletter'][1]")
	private WebElement newsLetterFieldSelect;

	@FindBy(xpath = "//label[normalize-space()='Yes']//input[@name='newsletter']")
	private WebElement newsLetterFieldSelected;

	@FindBy(xpath = "//input[@value=0]")
	private WebElement NotSelectednewsLetterButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerBreadcrumbButton;

	public boolean registerBreadcrumb() {
		return registerBreadcrumbButton.isDisplayed();
	}

	public void noNewsLetter() {
		NotSelectednewsLetterButton.click();
	}

	public boolean isnewsletterSelected() {
		return newsLetterFieldSelect.isSelected();
	}

	public void newsletterSelected() {
		newsLetterFieldSelected.click();
	}

	public String pasprivatePolicyWarningswordWarning() {
		return newsLetterFieldSelect.getText();
	}

	public String passwordNotMatchWarning() {
		return passwordFieldNotMatchWarning.getText();
	}

	public String emailWarning1() {
		return emailFieldWarning1.getDomProperty("validationMessage");
	}

	public String emailWarning2() {
		return emailFieldWarning1.getDomProperty("validationMessage");
	}

	public String emailfirefoxWarning() {
		return emailFieldfirefoxWarning.getDomProperty("validationMessage");
	}

	public String emailfirefoxWarning2() {
		return emailFieldfirefoxWarning.getDomProperty("validationMessage");
	}

	public void emailClear() {
		emailField.clear();
	}

	public void fNameClear() {
		firstNameField.clear();
	}

	public void lNameClear() {
		lastNameField.clear();
	}

	public String emailAlreadyRegisteredWarning() {
		return emailFieldAlreadyRegisteredWarning.getText();
	}

	public boolean isprivacyPolicySelected() {
		return privacyPolicyField.isSelected();
	}

	// ****************************************
	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement phoneIcon;

	public void phoneIconButton() {
		phoneIcon.click();
	}

	@FindBy(xpath = "//i[@class='fa fa-heart']")
	private WebElement hearticon;

	public void hearticonButton() {
		hearticon.click();
	}

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(),'Wish List')]")
	private WebElement Wishlist;

	public void WishlistButton() {
		Wishlist.click();
	}

	@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
	private WebElement shoppingCart;

	public void shoppingCartButton() {
		shoppingCart.click();
	}

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCart1;

	public void shoppingCartButton1() {
		shoppingCart1.click();
	}

	@FindBy(xpath = "//i[@class='fa fa-share']")
	private WebElement checkOuticon;

	public void checkOuticonButton() {
		checkOuticon.click();
	}

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkOuticon1;

	public void checkOuticonButton1() {
		checkOuticon1.click();
	}

	@FindBy(linkText = "Qafox.com")
	private WebElement HomePage;

	public void HomePageButton() {
		HomePage.click();
	}

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement Searchicon;

	public void SearchiconButton() {
		Searchicon.click();
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement breadcrumbAccount;

	public void breadcrumbAccountButton() {
		breadcrumbAccount.click();
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement breadcrumbRegister;

	public void breadcrumbRegisterButton() {
		breadcrumbRegister.click();
	}

	@FindBy(linkText = "login page")
	private WebElement loginList;

	public void loginListButton() {
		loginList.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Login']")
	private WebElement loginList1;

	public void loginListButton1() {
		loginList1.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Register']")
	private WebElement registerList1;

	public void registerListButton1() {
		registerList1.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Forgotten Password']")
	private WebElement ForgottenPassword;

	public void ForgottenPasswordButton() {
		ForgottenPassword.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='My Account']")
	private WebElement MyAccount;

	public void MyAccountButton() {
		MyAccount.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Address Book']")
	private WebElement AddressBook;

	public void AddressBookButton() {
		AddressBook.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Order History']")
	private WebElement OrderHistory;

	public void OrderHistoryButton() {
		OrderHistory.click();
	}

	@FindBy(xpath = "//a[normalize-space()='Downloads']")
	private WebElement Downloads;

	public void DownloadsButton() {
		Downloads.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Recurring payments']")
	private WebElement Recurringpayments;

	public void RecurringpaymentsButton() {
		Recurringpayments.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Reward Points']")
	private WebElement RewardPoints;

	public void RewardPointsButton1() {
		RewardPoints.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Returns']")
	private WebElement Returns;

	public void ReturnsButton() {
		Returns.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Transactions']")
	private WebElement Transactions;

	public void TransactionsButton() {
		Transactions.click();
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Newsletter']")
	private WebElement Newsletter;

	public void NewsletterButton4() {
		Newsletter.click();
	}

	@FindBy(xpath = "//footer//a[text()='About Us']")
	private WebElement AboutUs;

	public void AboutUsButton() {
		AboutUs.click();
	}

	@FindBy(xpath = "//footer//a[text()='Delivery Information']")
	private WebElement DeliveryInformatio;

	public void DeliveryInformationButton() {
		DeliveryInformatio.click();
	}

	@FindBy(xpath = "//footer//a[text()='Privacy Policy']")
	private WebElement PrivacyPolicy6;

	public void PrivacyPolicyButton6() {
		PrivacyPolicy6.click();
	}

	@FindBy(xpath = "//footer//a[text()='Terms & Conditions']")
	private WebElement Terms;

	public void TermsButton1() {
		Terms.click();
	}

	@FindBy(xpath = "//footer//a[text()='Contact Us']")
	private WebElement ContactUs;

	public void ContactUsButton() {
		ContactUs.click();
	}

	@FindBy(xpath = "//footer//a[text()='Returns']")
	private WebElement Returns11;

	public void ReturnsButton11() {
		Returns11.click();
	}

	@FindBy(xpath = "//footer//a[text()='Site Map']")
	private WebElement SiteMap;

	public void SiteMapButton() {
		SiteMap.click();
	}

	@FindBy(xpath = "//footer//a[text()='Brands']")
	private WebElement Brands;

	public void BrandsButton() {
		Brands.click();
	}
	@FindBy(xpath = "//footer//a[text()='Gift Certificates']")
	private WebElement GiftCertificates;
	
	public void GiftCertificatesButton() {
		GiftCertificates.click();
	}
	
	@FindBy(xpath = "//footer//a[text()='Affiliate']")
	private WebElement Affiliate;
	
	public void AffiliateButton() {
		Affiliate.click();
	}
	
	@FindBy(xpath = "//footer//a[text()='Specials']")
	private WebElement Specials;
	
	public void SpecialsButton() {
		Specials.click();
	}

	@FindBy(xpath = "//footer//a[text()='My Account']")
	private WebElement MyAccount1111;

	public void MyAccount1111Button() {
		MyAccount1111.click();
	}

	@FindBy(xpath = "//footer//a[text()='Order History']")
	private WebElement OrderHistory22222;

	public void OrderHistory22222Button() {
		OrderHistory22222.click();
	}

	@FindBy(xpath = "//footer//a[text()='Wish List']")
	private WebElement WishList444;

	public void WishList444Button() {
		WishList444.click();
	}

	@FindBy(xpath = "//footer//a[text()='Newsletter']")
	private WebElement Newsletter666;

	public void Newsletter666Button() {
		Newsletter666.click();
	}
	
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement RegisterAccount333;
	
	public String RegisterAccount333Button() {
		return RegisterAccount333.getText();
	}

	
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public String getCurrentUrl() {
		String currentURL=driver.getCurrentUrl();
		return currentURL;
	}
	
}
