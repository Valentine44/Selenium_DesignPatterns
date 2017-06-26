package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Letter page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFLetterPage extends PFGmailPage{
	
	@FindBy (xpath = "//img[@alt='In new window']")
	WebElement inNewWindowButton;
	
	/**
	 * PFLetterPage class constructor
	 * @param driver
	 */
	public PFLetterPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open letter in new window
	 * @return
	 */
	public PFLetterWindow inNewWindow() {
		waitElementVisible(inNewWindowButton);
		inNewWindowButton.click();
		return new PFLetterWindow(driver);
	}
}
