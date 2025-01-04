package registerPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_015VerifyDatabaseTestingRegistration {
	WebDriver driver;
	

	@Test(enabled=false)
	public void VerifyDatabaseTest() throws InterruptedException {
		
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("http://localhost/opencart/");
		
		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Mannu");
		driver.findElement(By.id("input-lastname")).sendKeys("JOHNN");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandnewEmail());
		driver.findElement(By.id("input-password")).sendKeys("SSSSSSSS");
		Actions actions=new Actions(driver);
		WebElement newsl = driver.findElement(By.id("input-newsletter"));
		WebElement agr = driver.findElement(By.name("agree"));
		
		actions.sendKeys(newsl,Keys.ENTER).sendKeys(Keys.TAB).sendKeys(agr, Keys.ENTER).sendKeys(Keys.TAB).build().perform();
		
		 // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/opencart_db"; // Replace with your DB URL
        String username = "root"; // Replace with your username
        String password = ""; // Replace with your password

        

        // SQL query to retrieve data
        String query = "SELECT id, name, email FROM users"; // Replace with your table and columns

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Load the database driver (Optional for modern JDBC drivers)
            //Class.forName("com.mysql.cj.jdbc.Driver"); // Uncomment if necessary

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
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
	}}
