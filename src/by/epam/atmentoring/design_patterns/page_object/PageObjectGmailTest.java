package by.epam.atmentoring.design_patterns.page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
/**
 * Gmail critical path test scenario implemented using Page Object
 * @author Valiantsin_Ivashynka
 *
 */
public class PageObjectGmailTest {
	
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
		LogIn_EmailPage emailPage = new LogIn_EmailPage(driver).open();
		LogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		GoogleAccountPage googleAccount = passwordPage.inputPassword(Page.CORRECT_PASSWORD).submitPassword();
		InboxPage inbox = googleAccount.selectMail();
		inbox.clickCompose().inputAddressee(Page.ADDRESSEE).inputSubject().inputLetterText().closeAndSave();
		DraftsPage drafts = inbox.toDraftsPage();
		drafts.openDraft().sendDraft();
		SentMailPage sentMail = drafts.toSentMailPage();
		Assert.assertEquals(sentMail.getEmailTime(), GmailPage.sendTime);
		sentMail.logOut();
	}
	/**
	 * submit incorrect password, then submit correct password
	 */
	@Test (dependsOnMethods = { "Scenario1" })
	public void Scenario2() {
		LogIn_EmailPage emailPage = new LogIn_EmailPage(driver).open();
		LogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		passwordPage = passwordPage.inputPassword(Page.INCORRECT_PASSWORD).attemptSubmitPassword();
		Assert.assertTrue(driver.findElement(LogIn_PasswordPage.WRONG_PASSWORD_MESSAGE).isDisplayed());
		passwordPage.inputPassword(Page.CORRECT_PASSWORD);
		GoogleAccountPage googleAccount = passwordPage.submitPassword();
		googleAccount.selectMail();
		Assert.assertTrue(driver.getTitle().contains("Inbox"));
	}
	/**
	 * log in to Gmail, create letter, send to myself, open in neew window, remove
	 */
	@Test (dependsOnMethods = { "Scenario2" })
	public void Scenario3() {
		LogIn_EmailPage emailPage = new LogIn_EmailPage(driver).open();
		LogIn_PasswordPage passwordPage = emailPage.inputEmail().submitEmail();
		GoogleAccountPage googleAccount = passwordPage.inputPassword(Page.CORRECT_PASSWORD).submitPassword();
		GmailPage gmail = googleAccount.selectMail();
		gmail.clickCompose().inputAddressee(Page.EMAIL).inputSubject().inputLetterText().sendDraft();
		SentMailPage sentMail = gmail.toSentMailPage();
		sentMail.inputSearchQuery(Page.SEARCH_QUERY).clickSearch();
		LetterPage letter = sentMail.openSearchResult();
		LetterWindow letterInNewWindow = letter.inNewWindow();
		Assert.assertEquals(letterInNewWindow.getSubject(), Page.SUBJECT);
		Assert.assertEquals(letterInNewWindow.getSender(), Page.SENDER);
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