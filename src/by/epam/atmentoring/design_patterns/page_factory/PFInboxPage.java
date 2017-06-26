package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
/**
 * Page Factory Inbox page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFInboxPage extends PFGmailPage{

	/**
	 * PFInboxPage class constructor
	 * @param driver
	 */
	public PFInboxPage(WebDriver driver) {
		super(driver);
	}
}