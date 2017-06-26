package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Sent mail page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFSentMailPage extends PFGmailPage{
	
	@FindBy (xpath = "//*[@role='main']/div[4]//table/tbody/tr[1]/td[8]/span")
	WebElement emailTime;
	/**
	 * PFSentMailPage class constructor
	 * @param driver
	 */
	public PFSentMailPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * parse the email's sending time
	 * @return
	 */
	public String getEmailTime() {
		return (emailTime.getText());
	}
}