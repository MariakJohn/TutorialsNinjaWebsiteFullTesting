package registerPack;

	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import javax.mail.*;
	import javax.mail.internet.MimeMultipart;
	import javax.mail.search.FlagTerm;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	public class AmazonIMAP {

	    // Define email and app password as variables
	    private String email = "manudliff@gmail.com";
	    private String appPasscode = "your-app-password";
	    private String link;

	    @Test
	    public void verifyThankyouMail() {
	        WebDriver driver = new ChromeDriver();
	        String URL = "https://www.amazon.com/";
	        driver.get(URL);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	        // Interact with Amazon elements
	        driver.findElement(By.xpath("//span[@class='nav-line-2 ']")).click();
	        driver.findElement(By.className("a-expander-prompt")).click();
	        driver.findElement(By.id("auth-fpp-link-bottom")).click();
	        driver.findElement(By.id("ap_email")).sendKeys(email);
	        driver.findElement(By.id("a-autoid-0")).click();

	        // Email IMAP configuration
	        String host = "imap.gmail.com";
	        String port = "993";
	        String expectedSubject = "amazon.in: Password recovery";
	        String expectedFromEmail = "\"amazon.in\" <account-update@amazon.in>";
	        String expectedBodyContent = "Someone is attempting to reset the password of your account.";

	        try {
	            // Mail server connection properties
	            Properties properties = new Properties();
	            properties.put("mail.store.protocol", "imaps");
	            properties.put("mail.imap.host", host);
	            properties.put("mail.imap.port", port);
	            properties.put("mail.imap.ssl.enable", "true");

	            // Connect to the mail server
	            Session emailSession = Session.getDefaultInstance(properties);
	            Store store = emailSession.getStore("imaps");
	            store.connect(host, email, appPasscode);

	            // Open the inbox folder
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);

	            // Search for unread emails
	            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

	            boolean found = false;
	            for (int i = messages.length - 1; i >= 0; i--) {
	                Message message = messages[i];

	                if (message.getSubject().contains(expectedSubject)) {
	                    found = true;
	                    Assert.assertEquals(message.getSubject(), expectedSubject);
	                    Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
	                    String actualEmailBody = getTextFromMessage(message);
	                    Assert.assertTrue(actualEmailBody.contains(expectedBodyContent));

	                    String[] ar = actualEmailBody.split("600\">");
	                    String linkPart = ar[1];
	                    String[] arr = linkPart.split("</a>");
	                    link = arr[0].trim();
	                    break;
	                }
	            }

	            if (!found) {
	                System.out.println("No confirmation email found.");
	            }

	            // Close the store and folder objects
	            inbox.close(false);
	            store.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        driver.quit();
	    }

	    private String getTextFromMessage(Message message) throws Exception {
	        String result = "";
	        if (message.isMimeType("text/plain")) {
	            result = message.getContent().toString();
	        } else if (message.isMimeType("text/html")) {
	            result = message.getContent().toString();
	        } else if (message.isMimeType("multipart/*")) {
	            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	            result = getTextFromMimeMultipart(mimeMultipart);
	        }
	        return result;
	    }

	    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
	        StringBuilder result = new StringBuilder();
	        int count = mimeMultipart.getCount();
	        for (int i = 0; i < count; i++) {
	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	            if (bodyPart.isMimeType("text/plain")) {
	                result.append(bodyPart.getContent());
	            } else if (bodyPart.isMimeType("text/html")) {
	                result.append(bodyPart.getContent());
	            } else if (bodyPart.getContent() instanceof MimeMultipart) {
	                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
	            }
	        }
	        return result.toString();
	    }
	}



