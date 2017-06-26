package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * login - password page class
 * @author Valiantsin_Ivashynka
 *
 */
public class LogIn_PasswordPage extends Page{
	
	private static final By PASSWORD_TEXT_FIELD = By.xpath("//input[@name='password']");
	static final By SUBMIT_PASSWORD = By.xpath("//span[contains(text(), 'Next')]");
	static final By WRONG_PASSWORD_MESSAGE = By.xpath("//*[contains (text(), 'Wrong password. Try again.')]");
	/**
	 * LogIn_PasswordPage class constructor
	 * @param driver
	 */
	public LogIn_PasswordPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * input password
	 * @return
	 */
	public LogIn_PasswordPage inputPassword(String password) {
		waitElementVisible(PASSWORD_TEXT_FIELD);
		driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(password);
		return this;
	}
	/**
	 * submit password
	 * @return
	 */
	public GoogleAccountPage submitPassword() {
		waitElementVisible(SUBMIT_PASSWORD);
		driver.findElement(SUBMIT_PASSWORD).click();
		return new GoogleAccountPage(driver);
	}
	/**
	 * submit incorrect password (the user stays on page)
	 * @return
	 */
	public LogIn_PasswordPage attemptSubmitPassword() {
		waitElementVisible(SUBMIT_PASSWORD);
		driver.findElement(SUBMIT_PASSWORD).click();
		return this;
	}
}