package by.epam.atmentoring.design_patterns.page_factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Page Factory Gmail critical path test scenario implemented using PageFactory
 * @author Valiantsin_Ivashynka
 *
 */
public class PageFactoryGmailTest {
	
	WebDriver driver;
	
	@BeforeMethod
	private void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/**
	 * log in to Gmail, create letter, save as draft, send the letter
	 */
	@Test
	public void Scenario1() {
		PFLogIn_EmailPage emailPage = new PFLogIn_EmailPage(driver).open();
		PFLogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		PFGoogleAccountPage googleAccount = passwordPage.inputPassword(PFPage.CORRECT_PASSWORD).submitPassword();
		PFInboxPage inbox = googleAccount.selectMail();
		inbox.clickCompose().inputAddressee(PFPage.ADDRESSEE).inputSubject().inputLetterText().closeAndSave();
		PFDraftsPage drafts = inbox.toDraftsPage();
		drafts.openDraft().sendDraft();
		PFSentMailPage sentMail = drafts.toSentMailPage();
		Assert.assertEquals(sentMail.getEmailTime(), PFGmailPage.sendTime);
		sentMail.logOut();
	}
	/**
	 * submit incorrect password, then submit correct password
	 */
	@Test (dependsOnMethods = { "Scenario1" })
	public void Scenario2() {
		PFLogIn_EmailPage emailPage = new PFLogIn_EmailPage(driver).open();
		PFLogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		passwordPage = passwordPage.inputPassword(PFPage.INCORRECT_PASSWORD).attemptSubmitPassword();
		Assert.assertTrue(driver.findElement(PFLogIn_PasswordPage.WRONG_PASSWORD_MESSAGE).isDisplayed());
		passwordPage.inputPassword(PFPage.CORRECT_PASSWORD);
		PFGoogleAccountPage googleAccount = passwordPage.submitPassword();
		googleAccount.selectMail();
		Assert.assertTrue(driver.getTitle().contains("Inbox"));
	}
	/**
	 * log in to Gmail, create letter, send to myself, open in neew window, remove
	 */
	@Test (dependsOnMethods = { "Scenario2" })
	public void Scenario3() {
		PFLogIn_EmailPage emailPage = new PFLogIn_EmailPage(driver).open();
		PFLogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		PFGoogleAccountPage googleAccount = passwordPage.inputPassword(PFPage.CORRECT_PASSWORD).submitPassword();
		PFGmailPage gmail = googleAccount.selectMail();
		gmail.clickCompose().inputAddressee(PFPage.EMAIL).inputSubject().inputLetterText().sendDraft();
		PFSentMailPage sentMail = gmail.toSentMailPage();
		sentMail.inputSearchQuery(PFPage.SEARCH_QUERY).clickSearch();
		PFLetterPage letter = sentMail.openSearchResult();
		PFLetterWindow letterInNewWindow = letter.inNewWindow();
		Assert.assertEquals(letterInNewWindow.getSubject(), PFPage.SUBJECT);
		Assert.assertEquals(letterInNewWindow.getSender(), PFPage.SENDER);
		//Assert.assertEquals(letterInNewWindow.getLetterText(), Page.LETTER_TEXT);
		letterInNewWindow.removeLetter();
		driver.close();
	}
	/**
	 * close browser
	 */
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}