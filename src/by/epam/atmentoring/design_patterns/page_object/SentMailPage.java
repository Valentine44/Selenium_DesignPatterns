package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Sent mail page class
 * @author Valiantsin_Ivashynka
 *
 */
public class SentMailPage extends GmailPage{
	
	private static final By EMAIL_TIME = By.xpath("//*[@role='main']/div[4]//table/tbody/tr[1]/td[8]/span");
	/**
	 * SentMailPage class constructor
	 * @param driver
	 */
	public SentMailPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * parse the email's sending time
	 * @return
	 */
	public String getEmailTime() {
		return (driver.findElement(EMAIL_TIME).getText());
	}
}