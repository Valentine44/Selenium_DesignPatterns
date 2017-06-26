package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory  login - password page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFLogIn_PasswordPage extends PFPage{
	
	@FindBy (xpath = "//input[@name='password']")
	WebElement passwordTextField;
	
	@FindBy (xpath = "//div[@id='passwordNext']/content/span")
	WebElement submitPassword;
	
	static final By WRONG_PASSWORD_MESSAGE = By.xpath("//*[contains (text(), 'Wrong password. Try again.')]");
	/**
	 * PFLogIn_PasswordPage class constructor
	 * @param driver
	 */
	public PFLogIn_PasswordPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * input password
	 * @return
	 */
	public PFLogIn_PasswordPage inputPassword(String password) {
		waitElementVisible(passwordTextField);
		passwordTextField.sendKeys(password);
		return this;
	}
	/**
	 * submit password
	 * @return
	 */
	public PFGoogleAccountPage submitPassword() {
		waitElementVisible(submitPassword);
		submitPassword.click();
		return new PFGoogleAccountPage(driver);
	}
	/**
	 * submit incorrect password (the user stays on page)
	 * @return
	 */
	public PFLogIn_PasswordPage attemptSubmitPassword() {
		waitElementVisible(submitPassword);
		submitPassword.click();
		return this;
	}
}