package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory LetterWindow class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFLetterWindow extends PFPage{
	@FindBy (xpath = "//div[@aria-label='Delete']/div/div")
	WebElement removeButton;
	
	@FindBy (xpath = "//div[@role='main']//h2")
	WebElement subjectArea;
	
	@FindBy (xpath = "//span[contains(text(), '@')]")
	WebElement senderArea;
	
	@FindBy (xpath = "//div[@dir='ltr']")
	WebElement letterTextArea;
	/**
	 * PFLetterWindow class constructor
	 * @param driver
	 */
	public PFLetterWindow(WebDriver driver) {
		super(driver);
	}
	/**
	 * get letter subject
	 * @return
	 */
	public String getSubject() {
		return (subjectArea.getText());
	}
	/**
	 * get letter sender's email
	 * @return
	 */
	public String getSender() {
		return (senderArea.getText());
	}
	/**
	 * get letter text
	 * @return
	 */
	public String getLetterText() {
		return (letterTextArea.getText());
	}
	/**
	 * remove letter
	 * @return
	 */
	public PFLetterWindow removeLetter() {
		waitElementVisible(removeButton);
		removeButton.click();
		return this;
	}
}