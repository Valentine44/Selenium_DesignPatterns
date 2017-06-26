package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Google account page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFGoogleAccountPage extends PFPage{
	
	@FindBy (xpath = "//a[@aria-label='Mail']")
	WebElement gmail;
	/**
	 * PFGoogleAccountPage class constructor
	 * @param driver
	 */
	public PFGoogleAccountPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * go to mail
	 * @return
	 */
	public PFInboxPage selectMail() {
		waitElementVisible(gmail);
		gmail.click();
		return new PFInboxPage(driver);
	}
}