package by.epam.atmentoring.design_patterns.page_factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Login - email input page class
 * @author Valiantsin_Ivashynka
 *
 */
public class PFLogIn_EmailPage extends PFPage{
	
	@FindBy (xpath = "//input[@id='identifierId']")
	WebElement emailTextField;
	
	@FindBy (xpath = "//div[@id='identifierNext']/content/span")
	WebElement submitEmail;
	/**
	 * PFLogIn_EmailPage constructor
	 * @param driver
	 */
	public PFLogIn_EmailPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open login-email page
	 * @return
	 */
	public PFLogIn_EmailPage open() {
		driver.get(URL);
		return this;
	}
	/**
	 * input email
	 * @return
	 */
	public PFLogIn_EmailPage inputEmail() {
		waitElementVisible(emailTextField);
		emailTextField.sendKeys(EMAIL);
		return this;
	}
	/**
	 * submit email
	 * @return
	 */
	public PFLogIn_PasswordPage submitEmail() {
		waitElementVisible(submitEmail);
		submitEmail.click();
		return new PFLogIn_PasswordPage(driver);
	}
}