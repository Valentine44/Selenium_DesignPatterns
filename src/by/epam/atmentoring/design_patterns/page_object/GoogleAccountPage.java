package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Google account page class
 * @author Valiantsin_Ivashynka
 *
 */
public class GoogleAccountPage extends Page{

	private static final By GMAIL = By.xpath("//a[@aria-label='Mail']");
	/**
	 * GoogleAccountPage class constructor
	 * @param driver
	 */
	public GoogleAccountPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * go to mail
	 * @return
	 */
	public InboxPage selectMail() {
		waitElementVisible(GMAIL);
		driver.findElement(GMAIL).click();
		return new InboxPage(driver);
	}
}