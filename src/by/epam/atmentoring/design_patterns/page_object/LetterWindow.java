package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * class for letter opened in a new window
 * @author Valiantsin_Ivashynka
 *
 */
public class LetterWindow extends Page {
	
	private static final By REMOVE_BUTTON = By.xpath("//div[@aria-label='Delete']/div/div");
	private static final By SUBJECT_AREA = By.xpath("//div[@role='main']//h2");
	private static final By SENDER_AREA = By.xpath("//span[contains(text(), '@')]");
	private static final By LETTER_TEXT_AREA = By.xpath("//div[@dir='ltr']");
	/**
	 * LwtterWindow constructor
	 * @param driver
	 */
	public LetterWindow(WebDriver driver) {
		super(driver);
	}
	/**
	 * get letter subject
	 * @return
	 */
	public String getSubject() {
		return (driver.findElement(SUBJECT_AREA).getText());
	}
	/**
	 * get letter sender's email
	 * @return
	 */
	public String getSender() {
		return (driver.findElement(SENDER_AREA).getText());
	}
	/**
	 * get letter text
	 * @return
	 */
	public String getLetterText() {
		return (driver.findElement(LETTER_TEXT_AREA).getText());
	}
	/**
	 * remove letter
	 * @return
	 */
	public LetterWindow removeLetter() {
		waitElementPresent(REMOVE_BUTTON);
		driver.findElement(REMOVE_BUTTON).click();
		return this;
	}
}