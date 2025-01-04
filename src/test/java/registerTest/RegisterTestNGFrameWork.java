package registerTest;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AccountLogin;
import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.MyAccountPage;
import pages.NewsletterSubscriptionPage;
import pages.RegisterPage;
import utils.CommonUtilities;

public class RegisterTestNGFrameWork {

	WebDriver driver;
	String browsersName;
	Properties prop;
	
	HeaderOptions headerOptions;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	MyAccountPage myAccountPage;
	NewsletterSubscriptionPage newsletterSubscriptionPage;
	AccountLogin accountLogin;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void setup() throws IOException {
		

		prop = CommonUtilities.loadProperties();
		browsersName = prop.getProperty("browsersName");

		if (browsersName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browsersName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browsersName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browsersName.equals("internetexplorer")) {
			driver = new InternetExplorerDriver();
		} else if (browsersName.equals("safari")) {
			driver = new SafariDriver();
		}


		//prop = CommonUtilities.loadProperties();
		/*
		 * if (prop == null) { System.out.println("Failed to load properties file.");
		 * throw new IllegalStateException("Properties object is null!"); }
		 */
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6000));
		driver.manage().deleteAllCookies(); // Deletes all cookies
		driver.navigate().refresh(); // Refresh to apply changes

		driver.get(prop.getProperty("appURL"));

		HeaderOptions headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		registerPage = headerOptions.ClickOnRegister();

	}

	@Test(priority = 1, enabled = true)
	public void verifyAccountUsingMandatoryDetails() throws IOException, InterruptedException {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.privacyPolicy();
		// registerPage.continueButton();
		AccountSuccessPage accountSuccessPage = registerPage.continueButton();

		Assert.assertTrue(accountSuccessPage.isLogoutdisplayed());

		String ExpHeading = "Your Account Has Been Created!";
		String ActHeading = accountSuccessPage.navigateToSuccess();
		Assert.assertEquals(ExpHeading, ActHeading);
		//CommonUtilities.takeScreenshot(driver, "Registered Page Successfully");

		String ExpHeading1 = "Congratulations! Your new account has been successfully created!";
		String ExpHeading2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String ExpHeading3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String ExpHeading4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		System.out.println(ActHeading);
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading1));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading2));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading3));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading4));
		myAccountPage = accountSuccessPage.clickContinue();
		//CommonUtilities.takeScreenshot(driver, "Accounts page");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit your account information")));

		Assert.assertTrue(myAccountPage.editAccountButton());

	}

	@Test(priority = 3, enabled = true)
	public void CheckSuccesspage() throws InterruptedException, IOException {
		if (prop == null) {
			throw new IllegalStateException("Properties object is null. Ensure properties are loaded in the setup.");
		}
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.privacyPolicy();
		// registerPage.continueButton();
		accountSuccessPage = registerPage.continueButton();

		Assert.assertTrue(accountSuccessPage.isLogoutdisplayed());

		String ExpHeading = "Your Account Has Been Created!";
		String ActHeading = accountSuccessPage.navigateToSuccess();
		Assert.assertEquals(ExpHeading, ActHeading);

		String ExpHeading1 = "Congratulations! Your new account has been successfully created!";
		String ExpHeading2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String ExpHeading3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String ExpHeading4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading1));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading2));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading3));
		Assert.assertTrue(accountSuccessPage.successContent().contains(ExpHeading4));
		myAccountPage = accountSuccessPage.clickContinue();
		CommonUtilities.takeScreenshot(driver, "Accounts page");

	}

	@Test(priority = 4, enabled = true)
	public void checkWarningMessagesForFiirstname() {

		accountSuccessPage = registerPage.continueButton();
		// check warning
		String ExpText1 = "First Name must be between 1 and 32 characters!";
		// String ActText1 = registerPage.firstNameWarning();
		Assert.assertEquals(ExpText1, registerPage.firstNameWarning());

		String ExpText2 = "Last Name must be between 1 and 32 characters!";
		Assert.assertEquals(ExpText2, registerPage.lastNameWarning());

		String ExpText3 = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(ExpText3, registerPage.emailWarning());

		String ExpText4 = "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(ExpText4, registerPage.telePhoneWarning());

		String ExpText5 = "Password must be between 4 and 20 characters!";
		Assert.assertEquals(ExpText5, registerPage.passwordWarning());

		String ExpText6 = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(ExpText6, registerPage.pasprivatePolicyWarningswordWarning());

	}

	@Test(priority = 5, enabled = true)
	public void newsLetterYes() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.newsletterSelected();
		Assert.assertTrue(registerPage.isnewsletterSelected());

		registerPage.privacyPolicy();
		// registerPage.continueButton();
		accountSuccessPage = registerPage.continueButton();
		Assert.assertTrue(accountSuccessPage.accountSuccessnewsLetter());
	}

	@Test(priority = 6, enabled = true)
	public void verifyRegisteringAccountByNotSubscribingNewsletter() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.noNewsLetter();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();
		accountSuccessPage.clickContinue();
		// accountSuccessPage.SubscribeNunsubscribeButton();

		newsletterSubscriptionPage = accountSuccessPage.SubscribeNunsubscribeButton();
		newsletterSubscriptionPage.newsletterSubscriptionvisible();
		newsletterSubscriptionPage.newsletterSubscriptionCheck();
	}

	@Test(priority = 7, enabled = true)
	public void verifyDifferentWaysofRegisteringAccount() throws InterruptedException {

		Assert.assertTrue(headerOptions.breadcrumbRegisterisDisplayed());
		headerOptions.clickOnMyAccount();
		accountLogin = headerOptions.ClickOnLogin();
		registerPage = accountLogin.continueAccountLoginButton();

		Assert.assertTrue(registerPage.registerBreadcrumb());

		headerOptions.clickOnMyAccount();
		accountLogin = headerOptions.ClickOnLogin();
		registerPage = accountLogin.registerListGroup();

		Assert.assertTrue(registerPage.registerBreadcrumb());
	}

	@Test(priority = 8, enabled = true)
	public void DifferentPasswords() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("missMatchPassword"));

		registerPage.privacyPolicy();
		Assert.assertTrue(registerPage.isprivacyPolicySelected());
		accountSuccessPage = registerPage.continueButton();

		String ExpTxt = "Password confirmation does not match password!";

		Assert.assertEquals(registerPage.passwordNotMatchWarning(), ExpTxt);

	}

	@Test(priority = 9, enabled = true)
	public void VerifydifferentwaysofnavigatingtoRegisterAccountpage() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(prop.getProperty("existingEmail"));
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.privacyPolicy();
		Assert.assertTrue(registerPage.isprivacyPolicySelected());
		accountSuccessPage = registerPage.continueButton();

		String ExpTxt = "Warning: E-Mail Address is already registered!";

		Assert.assertEquals(registerPage.emailAlreadyRegisteredWarning(), ExpTxt);

	}

	@Test(priority = 10, enabled = true)
	public void invalidEmailAddress() throws InterruptedException {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(prop.getProperty("invalidEmail"));
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.newsletterSelected();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		if (browsersName.equals("chrome") || browsersName.equals("edge")) {
			String expectedWarningMessageOne = "Please include an '@' in the email address. 'ghjkk' is missing an '@'.";
			String actualWarningMessageOne = registerPage.emailWarning1();
			Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
		//} else if  (browsersName.equals("firefox")) {
		} else{
			String expectedWarningMessageOne = "Please enter an email address.";
			String actualWarningMessageOne = registerPage.emailfirefoxWarning();
			Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
		}

		registerPage.emailClear();
		registerPage.email(prop.getProperty("invalidEmail22"));
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		if (browsersName.equals("chrome") || browsersName.equals("edge")) {
			String expectedWarningMessageTwo = "Please enter a part following '@'. 'ghjkk1234@' is incomplete.";
			String actualWarningMessageTwo = registerPage.emailWarning1();
			Assert.assertEquals(actualWarningMessageTwo, expectedWarningMessageTwo);
		} else if (browsersName.equals("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			String actualWarningMessageOne = registerPage.emailfirefoxWarning2();
			Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
		}

		registerPage.emailClear();
		registerPage.email(prop.getProperty("invalidEmail2"));
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		String expectedWarningMessageThree = "Please include an '@' in the email address. 'ghjkk1234' is missing an '@'.";
		String actualWarningMessageThree = registerPage.emailWarning1();
		Assert.assertEquals(actualWarningMessageThree, expectedWarningMessageThree);
		Thread.sleep(5000);
		registerPage.emailClear();
		registerPage.email(prop.getProperty("invalidEmail3"));
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		if (browsersName.equals("chrome") || browsersName.equals("edge")) {
			String expectedWarningMessageFour = "'.' is used at a wrong position in 'gmail.'.";
			String actualWarningMessageFour = registerPage.emailWarning2();
			Assert.assertEquals(actualWarningMessageFour, expectedWarningMessageFour);
		} else if (browsersName.equals("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			String actualWarningMessageOne = registerPage.emailfirefoxWarning2();
			Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
		}

	}

	@Test(priority = 11, enabled = true)
	public void invalidPhonenum() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(prop.getProperty("invalidEmail"));
		registerPage.telePhone(prop.getProperty("invalidPhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.newsletterSelected();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		String ExpMessg = "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(registerPage.telePhoneWarning(), ExpMessg);
	}

	@Test(priority = 12, enabled = true)
	public void registerUsingKeybordKeys() throws InterruptedException {

		Actions actions = new Actions(driver);

		for (int i = 0; i < 23; i++) {
			actions.sendKeys(Keys.TAB).perform();

		}
		actions.sendKeys(prop.getProperty("firstName")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("lastName"))
				.sendKeys(Keys.TAB).sendKeys(CommonUtilities.generateBrandnewEmail()).sendKeys(Keys.TAB)
				.sendKeys(prop.getProperty("telePhone")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("password"))
				.sendKeys(Keys.TAB).sendKeys(prop.getProperty("password")).sendKeys(Keys.TAB).sendKeys(Keys.TAB)
				.sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		Assert.assertTrue(accountSuccessPage.successbreadcrumb());
		Assert.assertTrue(accountSuccessPage.isLogoutdisplayed());
	}

	@Test(priority = 13, enabled = true)
	public void AccountWithproperPlaceholders() {

		String ExpPlaceHolderFName = "First Name";
		String ExpPlaceHolderLName = "Last Name";
		String ExpPlaceHolderEMail = "E-Mail";
		String ExpPlaceHolderPhNumber = "Telephone";
		String ExpPlaceHolderPassWord = "Password";
		String ExpPlaceHolderconPass = "Password Confirm";

		Assert.assertEquals(accountSuccessPage.firstnamePlaceholders(), ExpPlaceHolderFName);
		Assert.assertEquals(accountSuccessPage.lastnamePlaceholders(), ExpPlaceHolderLName);
		Assert.assertEquals(accountSuccessPage.emailPlaceholders(), ExpPlaceHolderEMail);
		Assert.assertEquals(accountSuccessPage.telephonePlaceholders(), ExpPlaceHolderPhNumber);
		Assert.assertEquals(accountSuccessPage.passwordPlaceholders(), ExpPlaceHolderPassWord);
		Assert.assertEquals(accountSuccessPage.passwordconfirmPlaceholders(), ExpPlaceHolderconPass);
	}

	@Test(priority = 14, enabled = true)
	public void verifyAllMandatoryFields() {

		String ExpCont = "\"* \"";
		String Expcolor = "rgb(255, 0, 0)";

		WebElement firstName = driver.findElement(By.cssSelector("label[for='input-firstname']"));
		WebElement LastName = driver.findElement(By.cssSelector("label[for='input-lastname']"));
		WebElement email = driver.findElement(By.cssSelector("label[for='input-email']"));
		WebElement phone = driver.findElement(By.cssSelector("label[for='input-telephone']"));
		WebElement password = driver.findElement(By.cssSelector("label[for='input-password']"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Object firstNameLabelContent = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", firstName);
		System.out.println(firstNameLabelContent);
		Assert.assertEquals(firstNameLabelContent, ExpCont);
		Object firstNameLabelColor = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", firstName);
		System.out.println(firstNameLabelColor);
		Assert.assertEquals(Expcolor, firstNameLabelColor);

		Object LastNameLabelContent = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", LastName);
		Assert.assertEquals(LastNameLabelContent, ExpCont);
		Object LastNameLabelColor = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", LastName);
		Assert.assertEquals(Expcolor, LastNameLabelColor);

		Object emailNameLabelContent = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", email);
		Assert.assertEquals(emailNameLabelContent, ExpCont);
		Object emailtNameLabelColor = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", email);
		Assert.assertEquals(Expcolor, emailtNameLabelColor);

		Object PhoneLabelContent = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", phone);
		Assert.assertEquals(PhoneLabelContent, ExpCont);
		Object PhoneLabelColor = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", phone);
		Assert.assertEquals(Expcolor, PhoneLabelColor);

		Object PasswordLabelContent = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')", password);
		Assert.assertEquals(PasswordLabelContent, ExpCont);
		Object PasswordLabelColor = jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color')", password);
		Assert.assertEquals(Expcolor, PasswordLabelColor);

	}

	@Test(priority = 15, enabled = false)
	public void VerifyTest() throws InterruptedException {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(prop.getProperty("invalidEmail"));
		registerPage.telePhone(prop.getProperty("invalidPhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));
		Actions actions = new Actions(driver);
		WebElement newsl = driver.findElement(By.id("input-newsletter"));
		WebElement agr = driver.findElement(By.name("agree"));

		actions.sendKeys(newsl, Keys.ENTER).sendKeys(Keys.TAB).sendKeys(agr, Keys.ENTER).sendKeys(Keys.TAB).build()
				.perform();

		// SQL query to retrieve data
		String query = "SELECT id, name, email FROM users"; // Replace with your table and columns

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Step 1: Load the database driver (Optional for modern JDBC drivers)
			// Class.forName("com.mysql.cj.jdbc.Driver"); // Uncomment if necessary

			// Step 2: Establish a connection
			// connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database!");

			// Step 3: Create a PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Step 4: Execute the query
			resultSet = preparedStatement.executeQuery();

			// Step 5: Process the ResultSet
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");

				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Step 6: Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Test(priority = 16)
	public void registrationWithOnlySpace() {

		registerPage.enterFirstName("     ");
		registerPage.enterLastName("     ");
		registerPage.email("     ");
		registerPage.telePhone("               ");
		registerPage.password("     ");
		registerPage.passwordConfirm("     ");

		registerPage.newsletterSelected();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone does not appear to be valid!";

		Assert.assertEquals(registerPage.firstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.lastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.emailWarning(), expectedEmailWarning);
		Assert.assertEquals(registerPage.telePhoneWarning(), expectedTelephoneWarning);

	}

	@Test(priority = 17, dataProvider = "passwordSupplier", enabled = true)
	public void verifyPasswordComplexityStandards(String passwordText) {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(prop.getProperty("invalidEmail"));
		registerPage.telePhone(prop.getProperty("invalidPhone"));
		registerPage.password(prop.getProperty("passwordText"));
		registerPage.passwordConfirm(prop.getProperty("passwordText"));

		registerPage.newsletterSelected();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		String exppass = "Enter pasword which follows password complexity Standard!";
		System.out.println("Actual warning message: " + registerPage.passwordWarning());
		Assert.assertEquals(registerPage.passwordWarning(), exppass);
		// Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-siblings::div")).getText(),exppass);

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplypasswords() {
		Object[][] data = { { "123465" }, { "asdfg123" }, { "ASD1233" }, { "asdddf!@#$$%" } };
		return data;
	}

	@Test(priority = 18, enabled = true)
	public void verifyHtWtNumberofChar() throws InterruptedException, IOException {

		String ExpHT = "34px";
		String ExpWdth = "701.25px";

		// FIRST NAME––

		System.out.println("FIRST NAME");
		WebElement firstNameField = driver.findElement(By.id("input-firstname"));
		String ActfirstNameFieldHT = firstNameField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActfirstNameFieldHT);
		String ActFirstNameWidth = firstNameField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActFirstNameWidth);

		Thread.sleep(30);

		accountSuccessPage = registerPage.continueButton();
		String ExpFNameWarning = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(registerPage.firstNameWarning(), ExpFNameWarning);

		accountSuccessPage = registerPage.continueButton();
		// WebElement firstNameField1 = driver.findElement(By.id("input-firstname"));
		// firstNameField1.sendKeys(prop.getProperty("firstName"));
		registerPage.enterFirstName(prop.getProperty("firstName"));
		accountSuccessPage = registerPage.continueButton();

		boolean fistNameWarningStatus = false;
		try {
			fistNameWarningStatus = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			fistNameWarningStatus = false;
		}
		Assert.assertFalse(fistNameWarningStatus, "Warning message should not be displayed for valid input!");

		// boolean isWarningDisplayed =
		// driver.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")).size()
		// > 0;
		// Assert.assertFalse(isWarningDisplayed, "Warning message should not be
		// displayed for valid input!");
		accountSuccessPage = registerPage.continueButton();
		// WebElement firstNameField11 = driver.findElement(By.id("input-firstname"));
		registerPage.fNameClear();
		registerPage.enterFirstName(prop.getProperty("longfirstName"));

		// firstNameField11.sendKeys(prop.getProperty("longfirstName"));
		accountSuccessPage = registerPage.continueButton();

		boolean fistNameWarningStatus2 = false;
		try {
			fistNameWarningStatus2 = driver
					.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed();
		} catch (NoSuchElementException e) {
			fistNameWarningStatus2 = false;
		}
		Assert.assertTrue(fistNameWarningStatus2, "First Name must be between 1 and 32 characters!");

		// boolean isWarningDisplayed1 =
		// driver.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div"))
		// != null;
		// Assert.assertTrue(isWarningDisplayed1, "First Name must be between 1 and 32
		// characters!");

		// LAST NAME
		System.out.println("LAST NAME");
		accountSuccessPage = registerPage.continueButton();

		WebElement LNameField = driver.findElement(By.id("input-lastname"));
		String ActLNameFieldHT = LNameField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActLNameFieldHT);
		String ActLNameWidth = LNameField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActLNameWidth);
		accountSuccessPage = registerPage.continueButton();

		accountSuccessPage = registerPage.continueButton();
		String ExpLNameWarning = "Last Name must be between 1 and 32 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
				ExpLNameWarning);

		accountSuccessPage = registerPage.continueButton();
		WebElement LastNameField1 = driver.findElement(By.id("input-lastname"));
		LastNameField1.sendKeys(prop.getProperty("lastName"));
		accountSuccessPage = registerPage.continueButton();

		// Thread.sleep(30);
		boolean isLWarningDisplayed = driver
				.findElements(By.xpath("//input[@id='input-lastname']/following-sibling::div")).size() > 0;
		Assert.assertFalse(isLWarningDisplayed, "Warning message should not be displayed for valid input!");

		// Thread.sleep(20);

		WebElement LNameField1 = driver.findElement(By.id("input-lastname"));
		LNameField1.clear();
		LNameField1.sendKeys(prop.getProperty("longlastName"));
		accountSuccessPage = registerPage.continueButton();

		boolean isLWarningDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-lastname']/following-sibling::div")) != null;
		Assert.assertTrue(isLWarningDisplayed1, "Last Name must be between 1 and 32 characters!");

		// EMAIL
		System.out.println("EMAIL");
		WebElement EmailField = driver.findElement(By.id("input-email"));
		String ActEmailFieldHT = EmailField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActEmailFieldHT);
		EmailField.getCssValue("width");
		accountSuccessPage = registerPage.continueButton();

		accountSuccessPage = registerPage.continueButton();
		String EmailWarning = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				EmailWarning);

		accountSuccessPage = registerPage.continueButton();
		WebElement EmailField1 = driver.findElement(By.id("input-email"));
		EmailField1.sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		boolean isEmailDisplayed = driver.findElements(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.size() > 0;
		Assert.assertFalse(isEmailDisplayed, "Warning message should not be displayed for valid input!");

		WebElement EmailField11 = driver.findElement(By.id("input-email"));
		EmailField11.clear();
		EmailField11.sendKeys(prop.getProperty("longEmail"));
		accountSuccessPage = registerPage.continueButton();

		boolean isEWarningDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-email']/following-sibling::div")) != null;
		Assert.assertTrue(isEWarningDisplayed1, "E-Mail Address does not appear to be valid!");

		// PHONE
		System.out.println("PHONE");
		WebElement phoneField = driver.findElement(By.id("input-telephone"));
		String ActphoneFieldHT = phoneField.getCssValue("height");
		Assert.assertEquals(ExpHT, ActphoneFieldHT);
		String ActphoneWidth = phoneField.getCssValue("width");
		Assert.assertEquals(ExpWdth, ActphoneWidth);
		accountSuccessPage = registerPage.continueButton();

		accountSuccessPage = registerPage.continueButton();
		String phoneFieldWarning = "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				phoneFieldWarning);

		accountSuccessPage = registerPage.continueButton();
		WebElement phoneField1 = driver.findElement(By.id("input-telephone"));
		phoneField1.sendKeys("1236547898");
		accountSuccessPage = registerPage.continueButton();

		boolean isPhonelDisplayed = driver
				.findElements(By.xpath("//input[@id='input-telephone']/following-sibling::div")).size() > 0;
		Assert.assertFalse(isPhonelDisplayed, "Warning message should not be displayed for valid input!");

		WebElement phoneField11 = driver.findElement(By.id("input-telephone"));
		phoneField11.clear();
		phoneField11.sendKeys("sdfghh23333333333333333333333333333333335");
		accountSuccessPage = registerPage.continueButton();

		boolean isPhonelDisplayed1 = driver
				.findElements(By.xpath("//input[@id='input-telephone']/following-sibling::div")) != null;
		Assert.assertTrue(isPhonelDisplayed1, "Telephone must be between 3 and 32 characters!");

		// PASSWORD
		System.out.println("PASSWORD");
		WebElement passwordField = driver.findElement(By.id("input-password"));
		String ActPasswordHT = passwordField.getCssValue("height");
		Assert.assertEquals(ActPasswordHT, ExpHT);
		String ActPasswordwd = passwordField.getCssValue("width");
		Assert.assertEquals(ActPasswordwd, ExpWdth);
		accountSuccessPage = registerPage.continueButton();

		String passwordWarning = "Password must be between 4 and 20 characters!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				passwordWarning);

		accountSuccessPage = registerPage.continueButton();
		WebElement passwordField1 = driver.findElement(By.id("input-password"));
		passwordField1.sendKeys(prop.getProperty("password"));
		accountSuccessPage = registerPage.continueButton();

		boolean passwordWarningStatus = false;
		try {
			passwordWarningStatus = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);

		WebElement passwordField11 = driver.findElement(By.id("input-password"));
		passwordField11.clear();
		passwordField11.sendKeys(prop.getProperty("longpassword"));
		accountSuccessPage = registerPage.continueButton();

		boolean passwordWarningStatus1 = false;
		try {
			passwordWarningStatus1 = driver
					.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus1 = false;
		}
		Assert.assertTrue(passwordWarningStatus1);

		// PASSWORD CONFIRM
		System.out.println("PASSWORD CONFIRM");
		WebElement passwordField111 = driver.findElement(By.id("input-confirm"));
		String ActPasswordHT1 = passwordField111.getCssValue("height");
		Assert.assertEquals(ActPasswordHT1, ExpHT);
		String ActPasswordwd1 = passwordField111.getCssValue("width");
		Assert.assertEquals(ActPasswordwd1, ExpWdth);

		// CONTINUE BUTTON
		WebElement contButton = driver.findElement(By.xpath("//input[@type='submit']"));
		String ActbuttonColor = contButton.getCssValue("color");
		Assert.assertEquals("rgba(255, 255, 255, 1)", ActbuttonColor);

		Object ActBackGColor = contButton.getCssValue("background-color");
		Assert.assertEquals(ActBackGColor, "rgba(34, 154, 200, 1)");

		String ActFontSize = contButton.getCssValue("font-size");
		Assert.assertEquals(ActFontSize, "12px");

		// SCREENSHOT COMPARISON
		HeaderOptions headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		registerPage = headerOptions.ClickOnRegister();

		CommonUtilities.screenshot(driver, "Registration Page");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		// String test = null;
		// FileUtils.copyFile(srcFile, new File("./Register/"+test+".png"));

		try {
			FileHandler.copy(srcFile,
					new File(System.getProperty("user.dir") + "\\ScreenShots\\registerPageActualAligment.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png",
				System.getProperty("user.dir") + "\\Screenshots\\registerPageExpectedAligment.png"));

	}

	@Test(priority = 19)
	public void verifyLeadingAndTrailingSpacesWhileRegistering() {

		SoftAssert softAssert = new SoftAssert();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));

		String firstname = "  " + prop.getProperty("firstName") + "  ";
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
		String lastname = "  " + prop.getProperty("lastName") + "  ";
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		String text = "    " + CommonUtilities.generateBrandnewEmail() + "         ";
		driver.findElement(By.id("input-email")).sendKeys(text);

		String telephone = "  " + prop.getProperty("tepePhone") + "  ";
		driver.findElement(By.id("input-telephone")).sendKeys(telephone);
		String password = "  " + prop.getProperty("password") + "  ";
		driver.findElement(By.id("input-password")).sendKeys(password);
		String confirm = "  " + prop.getProperty("password") + "  ";
		driver.findElement(By.id("input-confirm")).sendKeys(confirm);

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("value"),
				firstname.trim());
		// softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("value"),
		// firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getDomAttribute("value"),
				firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("value"), firstname.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("value"), firstname.trim());
		softAssert.assertAll();
	}

	@Test(priority = 20, enabled = true)
	public void verifyRegAccountPrivatePlicy() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.noNewsLetter();
		registerPage.privacyPolicy();
		registerPage.privacyPolicyEnabled();

		accountSuccessPage = registerPage.continueButton();

	}

	@Test(priority = 21, enabled = true)
	public void verifyRegAccountbyAddingDetailsPrivatePlicy() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.noNewsLetter();
		// registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();
		// accountSuccessPage.clickContinue();

		String ExpMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(ExpMessage, registerPage.privatePolicyWarning());

		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();
		accountSuccessPage.clickContinue();
	}

	@Test(priority = 22, enabled = true)
	public void PasswordFunctionToggled() {
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();
		Assert.assertEquals("password", registerPage.getPasswordType());
		Assert.assertEquals(registerPage.getPasswordConfirmType(), "password");

	}

	@Test(priority = 23, enabled = true)
	public void Navigations() throws InterruptedException {

		registerPage.phoneIconButton();
		Assert.assertEquals(registerPage.getTitle(), "Contact Us");
		driver.navigate().back();

		registerPage.hearticonButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.WishlistButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.shoppingCartButton();
		Assert.assertEquals(registerPage.getTitle(), "Shopping Cart");
		driver.navigate().back();

		registerPage.shoppingCartButton1();
		Assert.assertEquals(registerPage.getTitle(), "Shopping Cart");
		driver.navigate().back();

		registerPage.checkOuticonButton();
		Assert.assertEquals(registerPage.getTitle(), "Shopping Cart");
		driver.navigate().back();

		registerPage.checkOuticonButton1();
		Assert.assertEquals(registerPage.getTitle(), "Shopping Cart");
		driver.navigate().back();

		registerPage.HomePageButton();
		Assert.assertEquals(registerPage.getTitle(), "Your Store");
		driver.navigate().back();

		registerPage.SearchiconButton();
		Assert.assertEquals(registerPage.getTitle(), "Search");
		driver.navigate().back();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//i[@class='fa fa-home']"));
		js.executeScript("arguments[0].click();", element);
		Assert.assertEquals(registerPage.getTitle(), "Your Store");
		driver.navigate().back();

		registerPage.breadcrumbAccountButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.breadcrumbRegisterButton();
		Assert.assertEquals(registerPage.getTitle(), "Register Account");

		registerPage.loginListButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.loginListButton1();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.registerListButton1();
		Assert.assertEquals(registerPage.getTitle(), "Register Account");

		registerPage.ForgottenPasswordButton();
		Assert.assertEquals(registerPage.getTitle(), "Forgot Your Password?");
		driver.navigate().back();

		registerPage.MyAccountButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.AddressBookButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Wish List']")).click();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.OrderHistoryButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.DownloadsButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.RecurringpaymentsButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.RewardPointsButton1();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.ReturnsButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();


		registerPage.TransactionsButton();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.NewsletterButton4();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.AboutUsButton();
		Assert.assertEquals(registerPage.getTitle(), "About Us");
		driver.navigate().back();

		registerPage.DeliveryInformationButton();
		Assert.assertEquals(registerPage.getTitle(), "Delivery Information");
		driver.navigate().back();

		registerPage.PrivacyPolicyButton6();
		Assert.assertEquals(registerPage.getTitle(), "Privacy Policy");
		driver.navigate().back();

		registerPage.TermsButton1();
		Assert.assertEquals(registerPage.getTitle(), "Terms & Conditions");
		driver.navigate().back();

		registerPage.ContactUsButton();
		Assert.assertEquals(registerPage.getTitle(), "Contact Us");
		driver.navigate().back();

		registerPage.ReturnsButton11();
		Assert.assertEquals(registerPage.getTitle(), "Product Returns");
		driver.navigate().back();

		registerPage.SiteMapButton();
		Assert.assertEquals(registerPage.getTitle(), "Site Map");
		driver.navigate().back();

		registerPage.BrandsButton();
		Assert.assertEquals(registerPage.getTitle(), "Find Your Favorite Brand");
		driver.navigate().back();

		registerPage.GiftCertificatesButton();
		Assert.assertEquals(registerPage.getTitle(), "Purchase a Gift Certificate");
		driver.navigate().back();

		registerPage.AffiliateButton();
		Assert.assertEquals(registerPage.getTitle(), "Affiliate Program");
		driver.navigate().back();

		registerPage.SpecialsButton();
		Assert.assertEquals(registerPage.getTitle(), "Special Offers");
		driver.navigate().back();

		registerPage.MyAccount1111Button();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.OrderHistory22222Button();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.WishList444Button();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();

		registerPage.Newsletter666Button();
		Assert.assertEquals(registerPage.getTitle(), "Account Login");
		driver.navigate().back();
	}

	@Test(priority = 24, enabled = true)
	public void verifywithoutcPassword() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		//registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.noNewsLetter();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();
		
		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.passwordconfWarning(), expectedWarning);
	}

	@Test(priority = 25, enabled = true)
	public void verifyURLtitle() {

		String expTitle = "Register Account";
		Assert.assertEquals(registerPage.getTitle(), expTitle);

		String ExpURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(registerPage.getCurrentUrl(), ExpURL);

		Assert.assertTrue(registerPage.registerBreadcrumb());

		Assert.assertEquals(registerPage.RegisterAccount333Button(), "Register Account");
	}

	@Test(priority = 26, enabled = true)
	public void uiChecklistRegisterpage() throws IOException {

		CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\Screenshots\\ActUI.png");

		Assert.assertFalse(
				CommonUtilities.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\ActUI.png",
						System.getProperty("user.dir") + "\\Screenshots\\ExpUI.png"));

	}

	@Test(priority = 27, enabled = true)
	public void RegisterAccountInAllEnvironments() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.email(CommonUtilities.generateBrandnewEmail());
		registerPage.telePhone(prop.getProperty("telePhone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.passwordConfirm(prop.getProperty("password"));

		registerPage.noNewsLetter();
		registerPage.privacyPolicy();
		accountSuccessPage = registerPage.continueButton();

		Assert.assertTrue(accountSuccessPage.successbreadcrumb());
	}

}
