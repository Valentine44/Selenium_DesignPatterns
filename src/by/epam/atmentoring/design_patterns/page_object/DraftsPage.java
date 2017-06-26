package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Gmail drafts page class
 * @author Valiantsin_Ivashynka
 *
 */
public class DraftsPage extends GmailPage{
	
	private static final By DRAFT = By.xpath("//tr[1]/td[4]/div[2]/font[contains(text(), 'Draft')]");
	/**
	 * DraftsPage class constructor
	 * @param driver
	 */
	public DraftsPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open draft
	 * @return 
	 */
	public DraftsPage openDraft() {
		waitElementVisible(DRAFT);
		driver.findElement(DRAFT).click();
		return this;
	}
}