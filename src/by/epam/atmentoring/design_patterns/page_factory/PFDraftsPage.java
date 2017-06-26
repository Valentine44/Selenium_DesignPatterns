package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Gmail drafts page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFDraftsPage extends PFGmailPage{
	
	@FindBy (xpath = "//tr[1]/td[4]/div[2]/font[contains(text(), 'Draft')]")
	WebElement draft;
	
	/**
	 * PFDraftsPage class constructor
	 * @param driver
	 */
	public PFDraftsPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open draft
	 * @return 
	 */
	public PFDraftsPage openDraft() {
		waitElementVisible(draft);
		draft.click();
		return this;
	}
}