package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
/**
 * Page Factory Page class (all other pages' superclass)
 * @author Valiantsin_Ivashynka
 *
 */
public class PFPage {
	WebDriver driver;
	public static final int WAIT_FOR_ELEMENT_TIME = 10;
	protected static final String URL = "https://accounts.google.com/signin/v2/identifier";
	protected static final String ADDRESSEE = "iv.selenium.test2@yopmail.com";
	protected static final String SUBJECT = "Test subject";
	protected static final String LETTER_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n\nKind regards,\nJohn Doe";
	protected static final String EXPECTED_LETTER_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	protected static final String EMAIL = "iv.selenium.test@gmail.com";
	protected static final String SENDER = "<iv.selenium.test@gmail.com>";
	protected static final String CORRECT_PASSWORD = "$T123456";
	protected static final String INCORRECT_PASSWORD = "wrong!";
	protected static final String SEARCH_QUERY = "subject";
	/**
	 * PFPage class constructor
	 * @param driver
	 */
	public PFPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	/**
	 * wait until web-element is visible on the page
	 * @param element
	 */
	public void waitElementVisible(WebElement element) {
		new WebDriverWait (driver, WAIT_FOR_ELEMENT_TIME).until(ExpectedConditions.visibilityOf(element));
	}
}